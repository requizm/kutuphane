package com.example.kutuphane.controller;

import com.example.kutuphane.model.KullaniciDTO;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "login")
@Controller
public class LoginController {

    @GetMapping(path = { "/", "" })
    public String showLoginPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            return "redirect:/";
        }

        model.addAttribute("kullanici", new KullaniciDTO());
        return "login";
    }
}
