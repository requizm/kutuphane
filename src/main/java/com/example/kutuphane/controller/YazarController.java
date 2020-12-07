package com.example.kutuphane.controller;

import java.util.Optional;

import javax.validation.Valid;

import com.example.kutuphane.config.BadRequestException;
import com.example.kutuphane.model.Yazar;
import com.example.kutuphane.model.YazarDTO;
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
@RequestMapping(path = "yazar")
public class YazarController {

    @Autowired
    private YazarService yazarService;

    @GetMapping(path = { "/", "" })
    public String getYazarlar(Model model) {
        model.addAttribute("yazarlar", yazarService.getAll());
        return "yazarlar";
    }

    @GetMapping(path = "ekle")
    public String showYazarEkle(Model model) {
        YazarDTO yazar = new YazarDTO();
        model.addAttribute("yazar", yazar);
        return "yazar_ekle";
    }

    @PostMapping(path = "ekle")
    public String addYazar(@Valid @ModelAttribute("yazar") YazarDTO yazar, BindingResult br) {
        if (br.hasErrors()) {
            return "yazar_ekle";
        }
        yazarService.add(yazar.toYazar());
        return "redirect:/yazar/";
    }

    @GetMapping(path = "guncelle/{id}")
    public ModelAndView showYazarGuncelle(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("yazar_guncelle");

        Optional<Yazar> yazar = yazarService.get(id);
        if (!yazar.isPresent()) {
            throw new BadRequestException();
        }
        mav.addObject("yazar", yazar.get().toYazarDTO());
        return mav;
    }

    @PostMapping(path = "guncelle")
    public String updateYazar(@Valid @ModelAttribute("yazar") YazarDTO yazar, BindingResult br) {
        if (br.hasErrors()) {
            return "yazar_guncelle";
        }
        yazarService.update(yazar.toYazar());
        return "redirect:/yazar/";
    }

    @RequestMapping(path = "sil/{id}")
    public String deleteYazar(@PathVariable Integer id) {
        if (!yazarService.get(id).isPresent()) {
            throw new BadRequestException();
        }
        yazarService.delete(id);
        return "redirect:/yazar/";
    }
}
