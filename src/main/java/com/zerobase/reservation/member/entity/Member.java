package com.zerobase.reservation.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
public class Member implements MemberCode{
    @Id
    private String userId;

    private String userName;
    private String phone;
    private String password;
    /* 유저 타입에 따라 다른 접근 권한
    *  일반 사용자, 점장, 관리자
    * */
    private String userType;
    private LocalDateTime regDt;
    private LocalDateTime udtDt;

    private boolean emailAuthYn;
    private LocalDateTime emailAuthDt;
    private String emailAuthKey;

    private String resetPasswordKey;
    private LocalDateTime resetPasswordLimitDt;

    private String userStatus;
}
