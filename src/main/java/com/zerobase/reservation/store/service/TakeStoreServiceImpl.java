package com.zerobase.reservation.store.service;

import com.zerobase.reservation.store.dto.StoreDto;
import com.zerobase.reservation.store.dto.TakeStoreDto;
import com.zerobase.reservation.store.entity.TakeStore;
import com.zerobase.reservation.store.entity.TakeStoreCode;
import com.zerobase.reservation.store.mapper.StoreMapper;
import com.zerobase.reservation.store.mapper.TakeStoreMapper;
import com.zerobase.reservation.store.model.ServiceResult;
import com.zerobase.reservation.store.model.TakeStoreInput;
import com.zerobase.reservation.store.model.TakeStoreParam;
import com.zerobase.reservation.store.repository.StoreRepository;
import com.zerobase.reservation.store.repository.TakeStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TakeStoreServiceImpl implements TakeStoreService{

    private final TakeStoreMapper takeStoreMapper;
    private final TakeStoreRepository takeStoreRepository;

    @Override
    public List<TakeStoreDto> list(TakeStoreParam parameter) {

        long totalCount = takeStoreMapper.selectListCount(parameter);

        List<TakeStoreDto> list = takeStoreMapper.selectList(parameter);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for (TakeStoreDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }

        return list;
    }

    @Override
    public TakeStoreDto detail(long id) {

        Optional<TakeStore> optionalTakeStore = takeStoreRepository.findById(id);
        if (optionalTakeStore.isPresent()) {
            return TakeStoreDto.of(optionalTakeStore.get());
        }
        return null;
    }

    @Override
    public ServiceResult updateStatus(long id, String status) {

        Optional<TakeStore> optionalTakeStore = takeStoreRepository.findById(id);
        if (!optionalTakeStore.isPresent()) {
            return new ServiceResult(false, "예약 정보가 존재하지 않습니다.");
        }

        TakeStore takeStore = optionalTakeStore.get();

        takeStore.setStatus(status);
        takeStoreRepository.save(takeStore);

        return new ServiceResult(true);
    }

    @Override
    public List<TakeStoreDto> myStore(String userId) {

        TakeStoreParam parameter = new TakeStoreParam();
        parameter.setUserId(userId);
        List<TakeStoreDto> list = takeStoreMapper.selectListMyStore(parameter);

        return list;
    }

    @Override
    public ServiceResult cancel(long id) {

        Optional<TakeStore> optionalTakeStore = takeStoreRepository.findById(id);
        if (!optionalTakeStore.isPresent()) {
            return new ServiceResult(false, "예약 정보가 존재하지 않습니다.");
        }

        TakeStore takeStore = optionalTakeStore.get();

        takeStore.setStatus(TakeStoreCode.STATUS_CANCEL);
        takeStoreRepository.save(takeStore);

        return new ServiceResult();
    }

    @Override
    public ServiceResult complete(long id) {
        Optional<TakeStore> optionalTakeStore = takeStoreRepository.findById(id);
        if (!optionalTakeStore.isPresent()) {
            return new ServiceResult(false, "예약 정보가 존재하지 않습니다.");
        }

        TakeStore takeStore = optionalTakeStore.get();

        takeStore.setStatus(TakeStoreCode.STATUS_VISIT_COMPLETE);
        takeStoreRepository.save(takeStore);

        return new ServiceResult();
    }

    @Override
    public TakeStoreDto getById(long id) {

        return takeStoreRepository.findById(id).map(TakeStoreDto::of).orElse(null);

    }

    @Override
    public boolean add(TakeStoreInput parameter) {

        Optional<TakeStore> optionalTakeStore = takeStoreRepository.findById(parameter.getId());
        System.out.println("예약 번호 : " + parameter.getId());
        System.out.println("유저 아이디 : " + parameter.getUserId());
        System.out.println("내용 : " + parameter.getReview());
        System.out.println("스토어 번호 : " + parameter.getStoreId());
        if (!optionalTakeStore.isPresent()) {
            return false;
        }
        TakeStore takeStore = optionalTakeStore.get();
        takeStore.setReview(parameter.getReview());
        takeStore.setStarRating(parameter.getStarRating());
        takeStore.setReviewRegDt(LocalDateTime.now());
        takeStoreRepository.save(takeStore);

        return true;
    }

}
