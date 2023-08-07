package com.zerobase.reservation.store.entity;

public interface TakeStoreCode {

    String STATUS_REQ = "REQ";    // 예약 신청
    String STATUS_COMPLETE = "COMPLETE";    // 예약 승인
    String STATUS_CANCEL = "CANCEL";    // 예약 취소

    String STATUS_REFUSE = "REFUSE";    // 예약 거절 (점주가 거절한 경우)
    String STATUS_VISIT_COMPLETE = "VISIT_COMPLETE";    // 방문 확인

}
