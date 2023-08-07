package com.zerobase.reservation.store.model;

import com.zerobase.reservation.admin.model.CommonParam;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TakeStoreParam extends CommonParam {

    long id;
    String status;
    String userId;
    long searchStoreId;
    String date;
    String time;
    String searchDate;


}
