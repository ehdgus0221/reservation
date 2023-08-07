package com.zerobase.reservation.store.model;

import com.zerobase.reservation.admin.model.CommonParam;
import lombok.Data;

@Data
public class StoreParam extends CommonParam {

    long id; // store.id (storeDetail 에서 사용)
    long categoryId;

}
