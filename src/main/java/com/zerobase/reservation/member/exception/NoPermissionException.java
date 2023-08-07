package com.zerobase.reservation.member.exception;

//        회원정보 보기 버튼 누를 때 사용하기
//        if (!member.getUserType().equals("admin")) {
//            throw new NoPermissionException("관리자만 접근할 수 있습니다.");
//        }


public class NoPermissionException extends RuntimeException {
    public NoPermissionException(String error) {
        super(error);
    }
}
