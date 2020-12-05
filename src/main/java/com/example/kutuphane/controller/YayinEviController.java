package com.example.kutuphane.controller;

import java.util.Optional;

import com.example.kutuphane.config.ResourceNotFoundException;
import com.example.kutuphane.model.Yayinevi;
import com.example.kutuphane.model.YayineviDTO;
import com.example.kutuphane.service.YayineviService;

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
@RequestMapping("yayinevi")
public class YayinEviController {

    @Autowired
    private YayineviService yayineviService;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String getYayinEvleri(Model model) {
        model.addAttribute("yayinevleri", yayineviService.getAll());
        return "yayinevleri";
    }

    @PostMapping("yeni")
    public String addYayinEvi(@ModelAttribute("yayinevi") YayineviDTO yayinevi) {
        if (yayinevi == null) {

        }
        yayineviService.add(yayinevi.toYayinevi());
        return "redirect:/yayinevi/";
    }

    @RequestMapping("ekle")
    public String showYayineviEkle(Model model) {
        YayineviDTO yayinEvi = new YayineviDTO();
        model.addAttribute("yayinevi", yayinEvi);
        return "yayinevi_ekle";
    }

    @GetMapping("guncelle/{id}")
    public ModelAndView showYayinEviGuncelle(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("yayinevi_guncelle");
        Optional<Yayinevi> yayinEvi = yayineviService.get(id);
        if (!yayinEvi.isPresent()) {
            throw new ResourceNotFoundException();
        }
        mav.addObject("yayinevi", yayinEvi.get().toYayineviDTO());
        return mav;
    }

    @PostMapping("degistir")
    public String updateYayinevi(@ModelAttribute("yayinevi") YayineviDTO yayinEvi) {
        if (yayinEvi == null) {

        }
        yayineviService.update(yayinEvi.toYayinevi());
        return "redirect:/yayinevi/";
    }

    @RequestMapping(path = "sil/{id}")
    public String deleteYayinevi(@PathVariable Integer id) {
        if (!yayineviService.get(id).isPresent()) {

        }
        yayineviService.delete(id);
        return "redirect:/yayinevi/";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFoundException() {
        return "/hata";
    }
}
