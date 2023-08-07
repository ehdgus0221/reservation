package com.zerobase.reservation.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminMainController {

    @GetMapping("admin/main.do")
    public String main() {

        return "admin/main";
    }

    @GetMapping("admin/store/main.do")
    public String managerMain() {

        return "admin/store/main";
    }

}
