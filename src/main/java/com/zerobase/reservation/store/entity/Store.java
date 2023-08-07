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
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    long categoryId;

    String keyword;     // 키워드
    String subject;     // 매장명
    String zipcode;
    String addr;        // 매장 위치(주소)
    String addrDetail;
    Double xLocation;   // 매장 위치(x좌표)
    Double yLocation;   // 매장 위치(y좌표)

    @Column(length = 1000)
    String summary;     // 매장 요약 설명

    @Lob
    String contents;    // 매장 상세 설명


/*    String reserveStatus;  // 예약 현황 (예약 중, 승인, 거절)
    LocalDateTime reserveDt;  // 매장 예약 일정 (일자, 시간)*/


    LocalDateTime regDt;    // 매장 등록일(추가날짜)
    LocalDateTime udtDt;    // 매장 수정일(수정날짜)




}
