package com.zerobase.reservation.store.mapper;

import com.zerobase.reservation.store.dto.StoreDto;
import com.zerobase.reservation.store.model.StoreParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreMapper {

    long selectListCount(StoreParam parameter);
    List<StoreDto> selectList(StoreParam parameter);

}
