package com.zerobase.reservation.store.dto;

import com.zerobase.reservation.admin.dto.CategoryDto;
import com.zerobase.reservation.store.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StoreDto {


    Long id;
    long categoryId;
    String keyword;     // 키워드
    String subject;     // 매장명
    Double xLocation;   // 매장 위치(x좌표)
    Double yLocation;   // 매장 위치(y좌표)

    String summary;     // 매장 요약 설명
    String contents;    // 매장 상세 설명

    String reserveStatus;  // 예약 현황 (예약 중, 승인, 거절)
    LocalDateTime reserveDt;  // 매장 예약 일정 (일자, 시간)


    LocalDateTime regDt;    // 매장 등록일(추가날짜)
    LocalDateTime udtDt;    // 매장 수정일(수정날짜)


    long totalCount;
    long seq;

    String date;
    String time;

    String zipcode;
    String addr;
    String addrDetail;


    public static StoreDto of(Store store) {

        return StoreDto.builder()
                .id(store.getId())
                .categoryId(store.getCategoryId())
                .keyword(store.getKeyword())
                .subject(store.getSubject())
                .xLocation(store.getXLocation())
                .yLocation(store.getYLocation())
                .summary(store.getSummary())
                .contents(store.getContents())
                .regDt(store.getRegDt())
                .udtDt(store.getUdtDt())
                .zipcode(store.getZipcode())
                .addr(store.getAddr())
                .addrDetail(store.getAddrDetail())
                .build();

    }

    public static List<StoreDto> of(List<Store> stores) {

        if (stores == null) {
            return null;
        }

        List<StoreDto> storeList = new ArrayList<>();
        for (Store x : stores) {
            storeList.add(StoreDto.of(x));
        }
        return storeList;

    }
}
