package com.zerobase.reservation.store.model;

import lombok.Data;

@Data
public class StoreInput {

    long id;
    long categoryId;
    String subject;
    String keyword;
    String location;
    double xLocation;
    double yLocation;
    String summary;
    String contents;
    long price;
    long salePrice;
    String saleEndDtText;
    
    //삭제를 위한
    String idList;

    String zipcode;
    String addr;
    String addrDetail;
}
