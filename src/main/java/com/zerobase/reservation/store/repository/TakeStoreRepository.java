package com.zerobase.reservation.store.repository;

import com.zerobase.reservation.store.entity.TakeStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface TakeStoreRepository extends JpaRepository<TakeStore, Long> {

    // 예약 신청할때 선택한 시간 및 날짜도 넣어주기
    long countByStoreIdAndUserIdAndDateAndStatusIn(long storeId, String userId, String date, Collection<String> statusList);

}
