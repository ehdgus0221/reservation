package com.zerobase.reservation.store.mapper;

import com.zerobase.reservation.store.dto.TakeStoreDto;
import com.zerobase.reservation.store.model.TakeStoreParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TakeStoreMapper {

    long selectListCount(TakeStoreParam parameter);
    List<TakeStoreDto> selectList(TakeStoreParam parameter);

    List<TakeStoreDto> selectListMyStore(TakeStoreParam parameter);

}
