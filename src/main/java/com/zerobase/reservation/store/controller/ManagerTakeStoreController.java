package com.zerobase.reservation.store.controller;

import com.zerobase.reservation.admin.service.CategoryService;
import com.zerobase.reservation.store.dto.StoreDto;
import com.zerobase.reservation.store.dto.TakeStoreDto;
import com.zerobase.reservation.store.model.*;
import com.zerobase.reservation.store.service.StoreService;
import com.zerobase.reservation.store.service.TakeStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ManagerTakeStoreController extends BaseController {

    private final TakeStoreService takeStoreService;
    private final StoreService storeService;
    private final CategoryService categoryService;

    @GetMapping("/admin/takestore/list.do")
    public String list(Model model, TakeStoreParam parameter
        , BindingResult bindingResult) {

        parameter.init();
        List<TakeStoreDto> takeStoreDtoList = takeStoreService.list(parameter);

        long totalCount = 0;

        if (!CollectionUtils.isEmpty(takeStoreDtoList)) {
            totalCount = takeStoreDtoList.get(0).getTotalCount();
        }

        String queryString = parameter.getQueryString();
        String pagerHtml = getPagerHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);


        model.addAttribute("list", takeStoreDtoList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);

        // 예약된 날짜
        HashSet<String> dateList = new HashSet<>();
        for ( TakeStoreDto dto : takeStoreDtoList) {
            dateList.add(dto.getDate());
        }

        List<StoreDto> storeList = storeService.listAll();
        model.addAttribute("dateList", dateList);
        model.addAttribute("storeList", storeList);

        return "admin/takestore/list";
    }

    @PostMapping("/admin/takestore/status.do")
    public String status(Model model, TakeStoreParam parameter) {

        ServiceResult result = takeStoreService.updateStatus(parameter.getId(), parameter.getStatus());
        if (!result.isResult()) {
            model.addAttribute("message", result.getMessage());
            return "common/error";
        }


        return "redirect:/admin/takestore/list.do";
    }

    @GetMapping("/member/store_review")
    public String storeReview(Model model
            , HttpServletRequest request
            , TakeStoreInput parameter) {

        long id = parameter.getId();

        TakeStoreDto existTakeStore = takeStoreService.getById(id);
        if (existTakeStore == null) {
            model.addAttribute("message", "매장정보가 존재하지 않습니다. " + id);
            return "common/error";
        }
        TakeStoreDto review = existTakeStore;
        model.addAttribute("review", review);

        return "member/store_review";
    }

    @PostMapping("/member/store_review")
    public String storeReviewSubmit(Model model, HttpServletRequest request
            , TakeStoreInput parameter) {

        long id = parameter.getId();

        System.out.println(parameter.toString());

        TakeStoreDto existStore = takeStoreService.getById(id);
        if (existStore == null) {
            // error 처리
            model.addAttribute("message", "매장정보가 존재하지 않습니다.");
            return "common/error";
        }
        boolean result = takeStoreService.add(parameter);

        return "redirect:/member/takestore";
    }

}
