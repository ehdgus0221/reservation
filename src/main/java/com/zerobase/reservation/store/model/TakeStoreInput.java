package com.zerobase.reservation.store.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TakeStoreInput {

    long id;
    long takeStoreId;
    long storeId;
    String userId;
    String date;
    String time;
    String review;
    Double starRating;
    LocalDateTime regDt;

}
