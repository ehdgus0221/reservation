package com.zerobase.reservation.admin.mapper;

import com.zerobase.reservation.admin.dto.MemberDto;
import com.zerobase.reservation.admin.model.MemberParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    long selectListCount(MemberParam parameter);
    List<MemberDto> selectList(MemberParam parameter);

}
