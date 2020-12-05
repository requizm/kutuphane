package com.example.kutuphane.controller;

import java.util.Optional;

import com.example.kutuphane.config.ResourceNotFoundException;
import com.example.kutuphane.model.Kitap;
import com.example.kutuphane.model.KitapDTO;
import com.example.kutuphane.service.KitapService;
import com.example.kutuphane.service.YayineviService;
import com.example.kutuphane.service.YazarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.HttpStatus;

@Controller
@RequestMapping("kitap")
public class KitapController {

    @Autowired
    private KitapService kitapService;

    @Autowired
    private YayineviService yayineviService;

    @Autowired
    private YazarService yazarService;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String getKitaplar(Model model) {
        model.addAttribute("kitaplar", kitapService.getAll());
        return "kitaplar";
    }

    @PostMapping("yeni")
    public String addKitap(@ModelAttribute("kitap") KitapDTO kitap) {
        if (kitap == null || kitap.getYazar() == null || kitap.getYayinevi() == null) {
            throw new ResourceNotFoundException();
        }
        kitapService.add(kitap.toKitap());
        return "redirect:/kitap/";

    }

    @RequestMapping("ekle")
    public String showKitapEkle(Model model) {
        KitapDTO kitap = new KitapDTO();
        model.addAttribute("yayinevleri", yayineviService.getAll());
        model.addAttribute("yazarlar", yazarService.getAll());
        model.addAttribute("kitap", kitap);
        return "kitap_ekle";
    }

    @GetMapping("guncelle/{id}")
    public ModelAndView showKitapGuncelle(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("kitap_guncelle");
        Optional<Kitap> kitap = kitapService.get(id);
        if (!kitap.isPresent()) {
            throw new ResourceNotFoundException();
        }
        mav.addObject("kitap", kitap.get().toKitapDTO());
        mav.addObject("yayinevleri", yayineviService.getAll());
        mav.addObject("yazarlar", yazarService.getAll());
        return mav;
    }

    @PostMapping("degistir")
    public String updateKitap(@ModelAttribute("kitap") KitapDTO kitap) {
        if (kitap == null || kitap.getYazar() == null || kitap.getYayinevi() == null) {
            throw new ResourceNotFoundException();
        }
        kitapService.update(kitap.toKitap());
        return "redirect:/kitap/";
    }

    @RequestMapping(path = "sil/{id}")
    public String deleteKitap(@PathVariable Integer id) {
        if (!kitapService.get(id).isPresent()) {
            throw new ResourceNotFoundException();
        }
        kitapService.delete(id);
        return "redirect:/kitap/";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFoundException() {
        return "/hata";
    }
}
