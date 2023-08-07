package com.zerobase.reservation.store.controller;

import com.zerobase.reservation.admin.dto.CategoryDto;
import com.zerobase.reservation.admin.service.CategoryService;
import com.zerobase.reservation.store.dto.StoreDto;
import com.zerobase.reservation.store.dto.TakeStoreDto;
import com.zerobase.reservation.store.model.StoreParam;
import com.zerobase.reservation.store.model.TakeStoreParam;
import com.zerobase.reservation.store.service.StoreService;
import com.zerobase.reservation.store.service.TakeStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class StoreController extends BaseController {

    private final StoreService storeService;
    private final CategoryService categoryService;
    private final TakeStoreService takeStoreService;


    @GetMapping("/store")
    public String store(Model model
            , StoreParam parameter, TakeStoreParam param) {

        parameter.init();
        List<StoreDto> list = storeService.frontList(parameter);
        model.addAttribute("list", list);

        List<TakeStoreDto> takeStoreDtoList = takeStoreService.list(param);

       //null인 경우는 아직 리뷰를 작성하기 전 상태이므로 별점 평균에 관여 x
        // 별점이 있는 경우에 한해서 별점을 누적하여 저장
// 별점 평균을 저장하는 Map 초기화
        Map<Long, Double> averageRatingMap = new HashMap<>();
        Map<Long, Integer> ratingCountMap = new HashMap<>();

// 별점이 있는 경우에 한해서 별점을 누적하고 개수를 증가시킴
        for (TakeStoreDto dto : takeStoreDtoList) {
            if (dto.getStarRating() != null) {
                long storeId = dto.getStoreId();
                double starRating = dto.getStarRating();

                if (!averageRatingMap.containsKey(storeId)) {
                    averageRatingMap.put(storeId, starRating);
                    ratingCountMap.put(storeId, 1);
                } else {
                    double currentTotal = averageRatingMap.get(storeId) * ratingCountMap.get(storeId);
                    int currentCount = ratingCountMap.get(storeId);

                    currentTotal += starRating;
                    currentCount++;

                    averageRatingMap.put(storeId, currentTotal / currentCount);
                    ratingCountMap.put(storeId, currentCount);
                }
            }
        }

        model.addAttribute("averageRatingMap", averageRatingMap);


        long storeTotalCount = 0;

        List<CategoryDto> categoryList = categoryService.frontList(CategoryDto.builder().build());
        if (categoryList != null) {
            for (CategoryDto x : categoryList) {
                storeTotalCount += x.getStoreCount();
            }

        }
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("storeTotalCount", storeTotalCount);


        String queryString = parameter.getQueryString();
        String pagerHtml = getPagerHtml(storeTotalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);
        model.addAttribute("pager", pagerHtml);


        return "store/index";
    }

    @GetMapping("/store/{id}")
    public String storeDetail(Model model, StoreParam parameter, TakeStoreParam param) {

        StoreDto detail = storeService.frontDetail(parameter.getId());
        HashMap<Long, List<String>> reviewMap = new HashMap<>();

        List<TakeStoreDto> takeStoreDtoList = takeStoreService.list(param);
        for (TakeStoreDto dto : takeStoreDtoList) {
            if (dto.getReview() != null) {
                List<String> reviewList = reviewMap.getOrDefault(dto.getStoreId(), new ArrayList<>());
                reviewList.add(dto.getReview());
                reviewMap.put(dto.getStoreId(), reviewList);
            }
        }
        System.out.println(reviewMap.toString());

        model.addAttribute("reviewMap", reviewMap);

        model.addAttribute("detail", detail);

        return "store/detail";
    }


}
