package com.example.kutuphane.controller;

import javax.validation.Valid;

import com.example.kutuphane.model.HesapTurleri;
import com.example.kutuphane.model.KullaniciDTO;
import com.example.kutuphane.model.Roller;
import com.example.kutuphane.service.KullaniciService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "register")
@Controller
public class RegisterController {

    @Autowired
    private KullaniciService kullaniciService;

    @GetMapping(path = { "/", "" })
    public String showRegisterPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return "redirect:/";
        }

        model.addAttribute("kullanici", new KullaniciDTO());
        return "register";
    }

    @PostMapping(path = { "/", "" })
    public String register(@Valid @ModelAttribute("kullanici") KullaniciDTO kullanici, BindingResult br) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return "redirect:/";
        }

        if (br.hasErrors()) {
            return "register";
        }
        if (!kullaniciService.kullaniciMevcutMu(kullanici.toKullanici())) {
            kullaniciService.addForUser(kullanici.toKullanici());
            return "redirect:/login";
        }
        return "register";
    }
}
