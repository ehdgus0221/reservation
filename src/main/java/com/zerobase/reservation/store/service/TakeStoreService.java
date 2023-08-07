package com.zerobase.reservation.store.service;

import com.zerobase.reservation.store.dto.StoreDto;
import com.zerobase.reservation.store.dto.TakeStoreDto;
import com.zerobase.reservation.store.model.ServiceResult;
import com.zerobase.reservation.store.model.TakeStoreInput;
import com.zerobase.reservation.store.model.TakeStoreParam;

import java.util.List;

public interface TakeStoreService {



    /**
     * 매니저 - 예약 목록
     */

    List<TakeStoreDto> list(TakeStoreParam parameter);

    /**
     * 예약 상세 정보
     */
    TakeStoreDto detail(long id);

    /**
     * 예약 내용 상태 변경
     */
    ServiceResult updateStatus(long id, String status);


    /**
     * 내 매장예약 목록
     */
    List<TakeStoreDto> myStore(String userId);


    /**
     * 예약신청 취소 처리
     */
    ServiceResult cancel(long id);

    /**
     * 예약신청 방문 확인 처리
     */
    ServiceResult complete(long id);

    /**
     * 예약 목록
     */
    TakeStoreDto getById(long id);



    /**
     * 리뷰 등록
     */
    boolean add(TakeStoreInput parameter);
}
