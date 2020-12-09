package com.example.kutuphane.controller;

import javax.validation.Valid;

import com.example.kutuphane.exception.BadRequestException;
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
import org.springframework.validation.BindingResult;
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

    @GetMapping(path = "ekle")
    public String showYayineviEkle(Model model) {
        YayineviDTO yayinEvi = new YayineviDTO();
        model.addAttribute("yayinevi", yayinEvi);
        return "yayinevi_ekle";
    }

    @PostMapping(path = "ekle")
    public String addYayinevi(@Valid @ModelAttribute("yayinevi") YayineviDTO yayinevi, BindingResult br) {
        if (br.hasErrors()) {
            return "yayinevi_ekle";
        }
        yayineviService.add(yayinevi.toYayinevi());
        return "redirect:/yayinevi/";
    }

    @GetMapping(path = "guncelle/{id}")
    public ModelAndView showYayinEviGuncelle(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("yayinevi_guncelle");
        if (!yayineviService.yayineviMevcutMu(id)) {
            throw new BadRequestException();
        }
        Yayinevi yayinEvi = yayineviService.get(id);
        mav.addObject("yayinevi", yayinEvi.toYayineviDTO());
        return mav;
    }

    @PostMapping(path = "guncelle")
    public String updateYayinevi(@Valid @ModelAttribute("yayinevi") YayineviDTO yayinEvi, BindingResult br) {
        if (br.hasErrors()) {
            return "yayinevi_guncelle";
        }
        yayineviService.update(yayinEvi.toYayinevi());
        return "redirect:/yayinevi/";
    }

    @GetMapping(path = "sil/{id}")
    public String deleteYayinevi(@PathVariable Integer id) {
        if (!yayineviService.yayineviMevcutMu(id)) {
            throw new BadRequestException();
        }
        yayineviService.delete(id);
        return "redirect:/yayinevi/";
    }
}
