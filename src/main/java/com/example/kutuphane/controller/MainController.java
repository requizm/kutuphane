package com.example.kutuphane.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping(path = { "/", "" })
    public String getKitaplar() {
        return "index";
    }
}
