package com.zerobase.reservation.store.dto;

import com.zerobase.reservation.store.entity.Store;
import com.zerobase.reservation.store.entity.TakeStore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TakeStoreDto {

    Long id;

    long storeId;
    String userId;

    String status;  // 예약 현황 (예약 중, 예약 완료, 예약 취소)

    LocalDateTime regDt;    // 예약 신청일

    // JOIN
    String userName;
    String phone;
    String subject;

    long totalCount;
    long seq;

    String summary;

    String date;
    String time;

    String review;      // 매장 리뷰
    LocalDateTime reviewRegDt;    // 리뷰 등록일(추가날짜)
    Double starRating;  // 매장 별점

    public static TakeStoreDto of(TakeStore x) {

        return TakeStoreDto.builder()
                .id(x.getId())
                .storeId(x.getStoreId())
                .userId(x.getUserId())
                .regDt(x.getRegDt())
                .status(x.getStatus())
                .date(x.getDate())
                .time(x.getTime())
                .review(x.getReview())
                .reviewRegDt(x.getReviewRegDt())
                .starRating(x.getStarRating())
                .build();

    }



    public String getRegDtText() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return regDt != null ? regDt.format(formatter) : "";

    }


}
