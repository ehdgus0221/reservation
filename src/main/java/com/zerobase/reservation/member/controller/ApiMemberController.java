package com.zerobase.reservation.member.controller;

import com.zerobase.reservation.admin.dto.MemberDto;
import com.zerobase.reservation.common.model.ResponseResult;
import com.zerobase.reservation.member.model.MemberInput;
import com.zerobase.reservation.member.model.ResetPasswordInput;
import com.zerobase.reservation.member.service.MemberService;
import com.zerobase.reservation.store.dto.TakeStoreDto;
import com.zerobase.reservation.store.model.ServiceResult;
import com.zerobase.reservation.store.model.TakeStoreInput;
import com.zerobase.reservation.store.service.TakeStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ApiMemberController {

    private final TakeStoreService takeStoreService;


    @PostMapping("/api/member/store/cancel.api")
    public ResponseEntity<?> cancelStore(Model model
            , @RequestBody TakeStoreInput parameter
            , Principal principal){

        String userId = principal.getName();

        // 조작 방지를 위한 서버에서 본인 확인
        TakeStoreDto detail = takeStoreService.detail(parameter.getTakeStoreId());
        if (detail == null) {
            ResponseResult responseResult = new ResponseResult(false, "예약 신청 정보가 존재하지 않습니다. NULL" + parameter);
            return ResponseEntity.ok().body(responseResult);
        }

        if (userId == null || !userId.equals(detail.getUserId())) {
            // 내 예약 정보가 아닌 것
            ResponseResult responseResult = new ResponseResult(false, "본인의 예약 신청 정보만 취소할 수 있습니다.");
            return ResponseEntity.ok().body(responseResult);
        }

        ServiceResult result = takeStoreService.cancel(parameter.getTakeStoreId());
        if (!result.isResult()) {
            ResponseResult responseResult = new ResponseResult(false, result.getMessage());
            return ResponseEntity.ok().body(responseResult);
        }

        ResponseResult responseResult = new ResponseResult(true);
        return ResponseEntity.ok().body(responseResult);
    }

    @PostMapping("/api/member/store/complete.api")
    public ResponseEntity<?> visitStore(Model model
            , @RequestBody TakeStoreInput parameter
            , Principal principal){

        String userId = principal.getName();

        // 조작 방지를 위한 서버에서 본인 확인
        TakeStoreDto detail = takeStoreService.detail(parameter.getTakeStoreId());
        if (detail == null) {
            ResponseResult responseResult = new ResponseResult(false, "예약 신청 정보가 존재하지 않습니다. NULL" + parameter);
            return ResponseEntity.ok().body(responseResult);
        }

        if (userId == null || !userId.equals(detail.getUserId())) {
            // 내 예약 정보가 아닌 것
            ResponseResult responseResult = new ResponseResult(false, "본인의 예약 신청 정보만 설정할 수 있습니다.");
            return ResponseEntity.ok().body(responseResult);
        }

        ServiceResult result = takeStoreService.complete(parameter.getTakeStoreId());
        if (!result.isResult()) {
            ResponseResult responseResult = new ResponseResult(false, result.getMessage());
            return ResponseEntity.ok().body(responseResult);
        }

        ResponseResult responseResult = new ResponseResult(true);
        return ResponseEntity.ok().body(responseResult);
    }



}
