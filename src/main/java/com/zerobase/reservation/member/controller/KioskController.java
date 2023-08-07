package com.zerobase.reservation.member.controller;

import com.zerobase.reservation.member.service.MemberService;
import com.zerobase.reservation.store.controller.BaseController;
import com.zerobase.reservation.store.dto.StoreDto;
import com.zerobase.reservation.store.dto.TakeStoreDto;
import com.zerobase.reservation.store.model.ServiceResult;
import com.zerobase.reservation.store.model.TakeStoreParam;
import com.zerobase.reservation.store.service.StoreService;
import com.zerobase.reservation.store.service.TakeStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class KioskController extends BaseController {

    /***
     * @Autowired 사용보다 생성자를 통해 주입하는 추세
     */

    private final TakeStoreService takeStoreService;
    private final StoreService storeService;

    @GetMapping("/kiosk")
    public String register() {
        return "kiosk/index";
    }

    @GetMapping("/kiosk/takestore_list")
    public String list(Model model, TakeStoreParam parameter
            , Principal principal) {

        String userId = principal.getName();

        List<TakeStoreDto> list = takeStoreService.myStore(userId);

        long totalCount = 0;

        if (!CollectionUtils.isEmpty(list)) {
            totalCount = list.get(0).getTotalCount();
        }


        model.addAttribute("list", list);
        model.addAttribute("totalCount", totalCount);


        // 예약된 날짜
        HashSet<String> dateList = new HashSet<>();
        for ( TakeStoreDto dto : list) {
            dateList.add(dto.getDate());
        }

        List<StoreDto> storeList = storeService.listAll();
        model.addAttribute("dateList", dateList);
        model.addAttribute("storeList", storeList);

        return "kiosk/takestore_list";
    }

    @PostMapping("/kiosk/takestore_list/status.do")
    public String status(Model model, TakeStoreParam parameter) {

        ServiceResult result = takeStoreService.updateStatus(parameter.getId(), parameter.getStatus());
        if (!result.isResult()) {
            model.addAttribute("message", result.getMessage());
            return "common/error";
        }


        return "redirect:/admin/takestore/list.do";
    }



}
