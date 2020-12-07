package com.example.kutuphane.controller;

import java.util.Optional;

import javax.validation.Valid;

import com.example.kutuphane.config.BadRequestException;
import com.example.kutuphane.model.Kitap;
import com.example.kutuphane.model.KitapDTO;
import com.example.kutuphane.service.KitapService;
import com.example.kutuphane.service.YayineviService;
import com.example.kutuphane.service.YazarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "kitap")
public class KitapController {

    @Autowired
    private KitapService kitapService;

    @Autowired
    private YayineviService yayineviService;

    @Autowired
    private YazarService yazarService;

    @GetMapping(path = { "/", "" })
    public String getKitaplar(Model model) {
        model.addAttribute("kitaplar", kitapService.getAll());
        return "kitaplar";
    }

    @PostMapping(path = "yeni")
    public String addKitap(@ModelAttribute("kitap") KitapDTO kitap) {
        if (kitap == null || kitap.getYazar() == null || kitap.getYayinevi() == null) {
            throw new BadRequestException();
        }
        kitapService.add(kitap.toKitap());
        return "redirect:/kitap/";

    }

    @RequestMapping(path = "ekle")
    public String showKitapEkle(Model model) {
        KitapDTO kitap = new KitapDTO();
        model.addAttribute("yayinevleri", yayineviService.getAll());
        model.addAttribute("yazarlar", yazarService.getAll());
        model.addAttribute("kitap", kitap);
        return "kitap_ekle";
    }

    @PostMapping(path = "ekle")
    public String addKitap(@Valid @ModelAttribute("kitap") KitapDTO kitap, BindingResult br) {
        if (br.hasErrors()) {
            return "kitap_ekle";
        }
        kitapService.add(kitap.toKitap());
        return "redirect:/kitap/";

    }

    @GetMapping(path = "guncelle/{id}")
    public ModelAndView showKitapGuncelle(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("kitap_guncelle");
        Optional<Kitap> kitap = kitapService.get(id);
        if (!kitap.isPresent()) {
            throw new BadRequestException();
        }
        mav.addObject("kitap", kitap.get().toKitapDTO());
        mav.addObject("yayinevleri", yayineviService.getAll());
        mav.addObject("yazarlar", yazarService.getAll());
        return mav;
    }

    @PostMapping(path = "guncelle")
    public String updateKitap(@Valid @ModelAttribute("kitap") KitapDTO kitap, BindingResult br) {
        if (br.hasErrors()) {
            return "kitap_guncelle";
        }
        kitapService.update(kitap.toKitap());
        return "redirect:/kitap/";
    }

    @RequestMapping(path = "sil/{id}")
    public String deleteKitap(@PathVariable Integer id) {
        if (!kitapService.get(id).isPresent()) {
            throw new BadRequestException();
        }
        kitapService.delete(id);
        return "redirect:/kitap/";
    }
}
