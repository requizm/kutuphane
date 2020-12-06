package com.example.kutuphane.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.kutuphane.config.BadRequestException;
import com.example.kutuphane.model.Ara;
import com.example.kutuphane.model.Kitap;
import com.example.kutuphane.service.KitapService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String aramaYap(@ModelAttribute("ara") Ara ara, Model model) {
        if (ara == null) {
            throw new BadRequestException();
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

    @GetMapping(path = "isbn/{str}")
    public String showIsbnAra(@PathVariable String str, Model model) {
        List<Kitap> kitaplar = kitapService.findByIsbn(str);
        model.addAttribute("kitaplar", kitaplar);
        return "kitaplar";
    }

    @GetMapping(path = "seri/{str}")
    public String showSeriAdiAra(@PathVariable String str, Model model) {
        List<Kitap> kitaplar = kitapService.findBySeriAdi(str);
        model.addAttribute("kitaplar", kitaplar);
        return "kitaplar";
    }

    @GetMapping(path = "kitap/{str}")
    public String showAdAra(@PathVariable String str, Model model) {
        List<Kitap> kitaplar = kitapService.findByAd(str);
        model.addAttribute("kitaplar", kitaplar);
        return "kitaplar";
    }

    @GetMapping(path = "yazar/{str}")
    public String showYazarAra(@PathVariable String str, Model model) {
        List<Kitap> kitaplar = kitapService.findByYazarAdi(str);
        model.addAttribute("kitaplar", kitaplar);
        return "kitaplar";
    }

}
