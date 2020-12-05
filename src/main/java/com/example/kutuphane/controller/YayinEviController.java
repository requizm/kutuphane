package com.example.kutuphane.controller;

import java.util.Optional;

import com.example.kutuphane.config.BadRequestException;
import com.example.kutuphane.model.Yayinevi;
import com.example.kutuphane.model.YayineviDTO;
import com.example.kutuphane.service.YayineviService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "yayinevi")
public class YayinEviController {

    @Autowired
    private YayineviService yayineviService;

    @GetMapping(path = { "/", "" })
    public String getYayinEvleri(Model model) {
        model.addAttribute("yayinevleri", yayineviService.getAll());
        return "yayinevleri";
    }

    @PostMapping(path = "yeni")
    public String addYayinEvi(@ModelAttribute("yayinevi") YayineviDTO yayinevi) {
        if (yayinevi == null) {
            throw new BadRequestException();
        }
        yayineviService.add(yayinevi.toYayinevi());
        return "redirect:/yayinevi/";
    }

    @RequestMapping(path = "ekle")
    public String showYayineviEkle(Model model) {
        YayineviDTO yayinEvi = new YayineviDTO();
        model.addAttribute("yayinevi", yayinEvi);
        return "yayinevi_ekle";
    }

    @GetMapping(path = "guncelle/{id}")
    public ModelAndView showYayinEviGuncelle(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("yayinevi_guncelle");
        Optional<Yayinevi> yayinEvi = yayineviService.get(id);
        if (!yayinEvi.isPresent()) {
            throw new BadRequestException();
        }
        mav.addObject("yayinevi", yayinEvi.get().toYayineviDTO());
        return mav;
    }

    @PostMapping(path = "degistir")
    public String updateYayinevi(@ModelAttribute("yayinevi") YayineviDTO yayinEvi) {
        if (yayinEvi == null) {
            throw new BadRequestException();
        }
        yayineviService.update(yayinEvi.toYayinevi());
        return "redirect:/yayinevi/";
    }

    @RequestMapping(path = "sil/{id}")
    public String deleteYayinevi(@PathVariable Integer id) {
        if (!yayineviService.get(id).isPresent()) {
            throw new BadRequestException();
        }
        yayineviService.delete(id);
        return "redirect:/yayinevi/";
    }
}
