package com.zerobase.reservation.util;

public class PageUtilTest {


    public static void main(String[] args) {

        PageUtil pageUtil = new PageUtil(27, 10, 3, "");
        String htmlPager = pageUtil.pager();

        System.out.println(htmlPager);


    }





}
