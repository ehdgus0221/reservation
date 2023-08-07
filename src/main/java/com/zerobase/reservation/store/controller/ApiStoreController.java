package com.zerobase.reservation.store.controller;

import com.zerobase.reservation.admin.service.CategoryService;
import com.zerobase.reservation.common.model.ResponseResult;
import com.zerobase.reservation.store.entity.TakeStore;
import com.zerobase.reservation.store.model.ServiceResult;
import com.zerobase.reservation.store.model.TakeStoreInput;
import com.zerobase.reservation.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

// @Controller 어노테이션은 view 형태 리턴
// @RestController 어노테이션을 사용하면 json body 형태로 데이터 리턴
@RequiredArgsConstructor
@RestController
public class ApiStoreController extends BaseController {

    private final StoreService storeService;
    private final CategoryService categoryService;

    @PostMapping("/api/store/req.api")
    public ResponseEntity<?> storeReq(Model model, @RequestBody TakeStoreInput parameter
            , Principal principal) {

        parameter.setUserId(principal.getName());

        ServiceResult result = storeService.req(parameter);
        if (!result.isResult()) {
            ResponseResult responseResult = new ResponseResult(false, result.getMessage());
            return ResponseEntity.ok().body(responseResult);
         }
        ResponseResult responseResult = new ResponseResult(true);
        return ResponseEntity.ok().body(responseResult);
    }


}
