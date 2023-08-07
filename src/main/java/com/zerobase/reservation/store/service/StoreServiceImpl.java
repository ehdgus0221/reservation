package com.zerobase.reservation.store.service;

import com.zerobase.reservation.store.dto.StoreDto;
import com.zerobase.reservation.store.entity.Store;
import com.zerobase.reservation.store.entity.TakeStore;
import com.zerobase.reservation.store.entity.TakeStoreCode;
import com.zerobase.reservation.store.mapper.StoreMapper;
import com.zerobase.reservation.store.model.ServiceResult;
import com.zerobase.reservation.store.model.StoreInput;
import com.zerobase.reservation.store.model.StoreParam;
import com.zerobase.reservation.store.model.TakeStoreInput;
import com.zerobase.reservation.store.repository.StoreRepository;
import com.zerobase.reservation.store.repository.TakeStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StoreServiceImpl implements StoreService{

    private final StoreRepository storeRepository;
    private final TakeStoreRepository takeStoreRepository;
    private final StoreMapper storeMapper;

    @Override
    public boolean add(StoreInput parameter) {

        Store store = Store.builder()
                .categoryId(parameter.getCategoryId())
                .subject(parameter.getSubject())
                .keyword(parameter.getKeyword())
                .xLocation(parameter.getXLocation())
                .yLocation(parameter.getYLocation())
                .regDt(LocalDateTime.now())
                .summary(parameter.getSummary())
                .contents(parameter.getContents())
                .zipcode(parameter.getZipcode())
                .addr(parameter.getAddr())
                .addrDetail(parameter.getAddrDetail())
                .build();
        storeRepository.save(store);



        return true;
    }

    @Override
    public boolean set(StoreInput parameter) {

        Optional<Store> optionalStore = storeRepository.findById(parameter.getId());
        if (!optionalStore.isPresent()) {
            return false;
        }

        Store store = optionalStore.get();
        store.setCategoryId(parameter.getCategoryId());
        store.setSubject(parameter.getSubject());
        store.setKeyword(parameter.getKeyword());
        store.setXLocation(parameter.getXLocation());
        store.setYLocation(parameter.getYLocation());
        store.setSummary(parameter.getSummary());
        store.setContents(parameter.getContents());
        store.setUdtDt(LocalDateTime.now());
        store.setZipcode(parameter.getZipcode());
        store.setAddr(parameter.getAddr());
        store.setAddrDetail(parameter.getAddrDetail());
        storeRepository.save(store);

        return true;
    }

    @Override
    public List<StoreDto> frontList(StoreParam parameter) {

        if (parameter.getCategoryId() < 1) {
            List<Store> storeList = storeRepository.findAll();
            return StoreDto.of(storeList);
        }

        Optional<List<Store>> optionalStores = storeRepository.findByCategoryId(parameter.getCategoryId());
        if (optionalStores.isPresent()) {
            return StoreDto.of(optionalStores.get());
        }

        return null;
    }


    @Override
    public StoreDto frontDetail(long id) {

        Optional<Store> optionalStore = storeRepository.findById(id);
        if (optionalStore.isPresent()) {
            return StoreDto.of(optionalStore.get());
        }
        return null;
    }

    @Override
    public ServiceResult req(TakeStoreInput parameter) {

        ServiceResult result = new ServiceResult();

        // 매장 정보가 존재하는지 확인
        Optional<Store> optionalStore = storeRepository.findById(parameter.getStoreId());
        if (!optionalStore.isPresent()) {
            result.setResult(false);
            result.setMessage("매장 정보가 존재하지 않습니다.");
            return result;
        }

        Store store = optionalStore.get();

        // 이미 신청정보가 있는지 확인
        // 동일한 날짜에는 예약 한번만 할 수 있도록 진행
        String[] statusList = {TakeStore.STATUS_REQ, TakeStore.STATUS_COMPLETE, TakeStore.STATUS_VISIT_COMPLETE};

        long count = takeStoreRepository.countByStoreIdAndUserIdAndDateAndStatusIn(store.getId()
                , parameter.getUserId()
                , parameter.getDate()
                ,Arrays.asList(statusList));

        if (count > 0) {
            result.setResult(false);
            result.setMessage("이미 신청한 예약 정보가 존재합니다. 동일한 날에는 한 번만 예약 가능합니다.");
            return result;
        }
        // 다른 사람이 예약했는지

        TakeStore takeStore = TakeStore.builder()
                .storeId(store.getId())
                .userId(parameter.getUserId())
                .status(TakeStoreCode.STATUS_REQ)
                .regDt(LocalDateTime.now())
                .date(parameter.getDate())
                .time(parameter.getTime())
                .build();
        takeStoreRepository.save(takeStore);

        result.setResult(true);
        result.setMessage("");
        return result;
    }

    @Override
    public List<StoreDto> listAll() {

        List<Store> storeList = storeRepository.findAll();

        return StoreDto.of(storeList);
    }



    @Override
    public List<StoreDto> list(StoreParam parameter) {

        long totalCount = storeMapper.selectListCount(parameter);


        List<StoreDto> list = storeMapper.selectList(parameter);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for (StoreDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }

        return list;
    }

    @Override
    public StoreDto getById(long id) {

        return storeRepository.findById(id).map(StoreDto::of).orElse(null);

    }

}
