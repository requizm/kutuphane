package com.example.kutuphane.controller;

import java.util.List;

import com.example.kutuphane.model.Kitap;
import com.example.kutuphane.service.KitapService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "ara")
public class SearchController {

    @Autowired
    private KitapService kitapService;

    @GetMapping(path = { "/", "" })
    public String getKitaplar() {
        System.out.println("ara sayfasi");
        return "ara";
    }

    @GetMapping(path = "isbn/{str}")
    public String showIsbnAra(@PathVariable String str, Model model) {
        List<Kitap> kitaplar = kitapService.findByIsbn(str);
        model.addAttribute("kitaplar", kitaplar);
        return "kitaplar";
    }

    @GetMapping(path = "seriadi/{str}")
    public String showSeriAdiAra(@PathVariable String str, Model model) {
        List<Kitap> kitaplar = kitapService.findBySeriAdi(str);
        model.addAttribute("kitaplar", kitaplar);
        return "kitaplar";
    }

    @GetMapping(path = "ad/{str}")
    public String showAdAra(@PathVariable String str, Model model) {
        List<Kitap> kitaplar = kitapService.findByAd(str);
        model.addAttribute("kitaplar", kitaplar);
        return "kitaplar";
    }

    /*
     * @GetMapping(path = "isbn/{str}") public String showIsbnAra(@PathVariable
     * String str, Model model) { List<Kitap> kitaplar = kitapService.fin(str);
     * model.addAttribute("kitaplar", kitaplar); return "kitaplar"; }
     */
}
