package com.example.kutuphane.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.example.kutuphane.model.Ara;
import com.example.kutuphane.model.Kitap;
import com.example.kutuphane.service.KitapService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "ara")
public class SearchController {

    @Autowired
    private KitapService kitapService;

    @GetMapping(path = { "/", "" })
    public String showAra(Model model) {
        Ara ara = new Ara();
        model.addAttribute("ara", ara);
        return "ara";
    }

    @PostMapping(path = "islem")
    public String aramaYap(@Valid @ModelAttribute("ara") Ara ara, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return "ara";
        }
        int i = ara.getI();
        String text = ara.getText();
        List<Kitap> kitaplar = new ArrayList<>();
        if (i == 0) {
            kitaplar = kitapService.findByAd(text);
        } else if (i == 1) {
            kitaplar = kitapService.findBySeriAdi(text);
        } else if (i == 2) {
            kitaplar = kitapService.findByYazarAdi(text);
        } else if (i == 3) {
            kitaplar = kitapService.findByIsbn(text);
        }
        model.addAttribute("kitaplar", kitaplar);
        return "kitaplar";
    }
}
