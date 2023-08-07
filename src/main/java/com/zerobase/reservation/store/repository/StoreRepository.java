package com.zerobase.reservation.store.repository;

import com.zerobase.reservation.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Optional<List<Store>> findByCategoryId(long categoryId);

}
