package com.pp_3_1_5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

   @GetMapping
    public String getIndex() {
        return "index";
    }
}
