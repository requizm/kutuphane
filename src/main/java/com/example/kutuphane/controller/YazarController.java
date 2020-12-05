package com.example.kutuphane.controller;

import java.util.Optional;

import com.example.kutuphane.config.ResourceNotFoundException;
import com.example.kutuphane.model.Yazar;
import com.example.kutuphane.model.YazarDTO;
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
@RequestMapping("yazar")
public class YazarController {

    @Autowired
    private YazarService yazarService;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String getYazarlar(Model model) {
        model.addAttribute("yazarlar", yazarService.getAll());
        return "yazarlar";
    }

    @PostMapping("yeni")
    public String addYazar(@ModelAttribute("yazar") YazarDTO yazar) {
        if (yazar == null) {

        }
        yazarService.add(yazar.toYazar());
        return "redirect:/yazar/";
    }

    @RequestMapping("ekle")
    public String showYazarEkle(Model model) {
        YazarDTO yazar = new YazarDTO();
        model.addAttribute("yazar", yazar);
        return "yazar_ekle";
    }

    @GetMapping("guncelle/{id}")
    public ModelAndView showYazarGuncelle(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("yazar_guncelle");

        Optional<Yazar> yazar = yazarService.get(id);
        if (!yazar.isPresent()) {
            throw new ResourceNotFoundException();
        }
        mav.addObject("yazar", yazar.get().toYazarDTO());
        return mav;
    }

    @PostMapping("degistir")
    public String updateYazar(@ModelAttribute("yazar") YazarDTO yazar) {
        if (yazar == null) {

        }
        yazarService.update(yazar.toYazar());
        return "redirect:/yazar/";
    }

    @RequestMapping(path = "sil/{id}")
    public String deleteYazar(@PathVariable Integer id) {
        if (!yazarService.get(id).isPresent()) {
            
        }
        yazarService.delete(id);
        return "redirect:/yazar/";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFoundException() {
        return "/hata";
    }
}
