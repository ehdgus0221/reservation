package com.zerobase.reservation.store.controller;

import com.zerobase.reservation.admin.service.CategoryService;
import com.zerobase.reservation.store.dto.StoreDto;
import com.zerobase.reservation.store.model.StoreInput;
import com.zerobase.reservation.store.model.StoreParam;
import com.zerobase.reservation.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ManagerStoreController extends BaseController {

    private final StoreService storeService;
    private final CategoryService categoryService;

    @GetMapping("/admin/store/list.do")
    public String list(Model model, StoreParam parameter) {

        parameter.init();
        List<StoreDto> storeList = storeService.list(parameter);

        long totalCount = 0;

        if (!CollectionUtils.isEmpty(storeList)) {
            totalCount = storeList.get(0).getTotalCount();
        }
        String queryString = parameter.getQueryString();
        String pagerHtml = getPagerHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);


        model.addAttribute("list", storeList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);

        return "admin/store/list";
    }

    @GetMapping(value = {"/admin/store/add.do", "/admin/store/edit.do"})
    public String add(Model model, HttpServletRequest request
            , StoreInput parameter) {

        model.addAttribute("category", categoryService.list());

        boolean editMode = request.getRequestURI().contains("/edit.do");
        StoreDto detail = new StoreDto();


        if (editMode) {
            long id = parameter.getId();

            StoreDto existStore = storeService.getById(id);
            if (existStore == null) {
                // error 처리
                model.addAttribute("message", "매장정보가 존재하지 않습니다.");
                return "common/error";
            }

            detail = existStore;

        }

        model.addAttribute("detail", detail);
        model.addAttribute("editMode", editMode);

        return "admin/store/add";
    }

    @PostMapping(value = {"/admin/store/add.do", "/admin/store/edit.do"})
    public String addSubmit(Model model, HttpServletRequest request
            , StoreInput parameter) {

        boolean editMode = request.getRequestURI().contains("/edit.do");

        if (editMode) {
            long id = parameter.getId();

            StoreDto existStore = storeService.getById(id);
            if (existStore == null) {
                // error 처리
                model.addAttribute("message", "매장정보가 존재하지 않습니다.");
                return "common/error";
            }
            boolean result = storeService.set(parameter);
        } else {
            boolean result = storeService.add(parameter);
        }



        return "redirect:/admin/store/list.do";
    }

}
