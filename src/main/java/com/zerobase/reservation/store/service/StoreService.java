package com.zerobase.reservation.store.service;

import com.zerobase.reservation.store.dto.StoreDto;
import com.zerobase.reservation.store.model.ServiceResult;
import com.zerobase.reservation.store.model.StoreInput;
import com.zerobase.reservation.store.model.StoreParam;
import com.zerobase.reservation.store.model.TakeStoreInput;

import java.util.List;

public interface StoreService {

    /**
     *  매장 등록
     */
    boolean add(StoreInput parameter);


    /**
     * 매니저 - 매장 목록
     */

    List<StoreDto> list(StoreParam parameter);

    /**
     * 매장 상세 목록
     */
    StoreDto getById(long id);

    /**
     * 매장 정보 수정
     */
    boolean set(StoreInput parameter);


    /**
     * 사용자 - 매장 목록
     */

    List<StoreDto> frontList(StoreParam parameter);

    /**
     * 사용자 - 매장 상세 정보
     */
    StoreDto frontDetail(long id);

    /**
     * 매장 예약
     */
    ServiceResult req(TakeStoreInput parameter);

    /**
     * 전체 매장 목록
     */
    List<StoreDto> listAll();



}
