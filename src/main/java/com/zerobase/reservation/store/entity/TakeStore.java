package com.zerobase.reservation.store.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class TakeStore implements TakeStoreCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    long storeId;
    String userId;

    String status;  // 예약 현황 (예약 중, 예약 완료, 예약 취소)

    LocalDateTime regDt;    // 예약 신청일
     // 예약 일자 및 시간
    String date;
    String time;

    @Lob
    String review;      // 매장 리뷰

    // 리뷰는 수정 못하도록 설계
    LocalDateTime reviewRegDt;    // 리뷰 등록일(추가날짜)

    Double starRating;  // 매장 별점

}
