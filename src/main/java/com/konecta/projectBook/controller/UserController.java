package com.konecta.projectBook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.konecta.projectBook.model.User;
import com.konecta.projectBook.service.UserService;

@Controller
public class UserController {

	@Autowired
    private UserService userService;
   
    @GetMapping({"/"})
    public String index() {
        return "login";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/accessdenied")
    public String accessDenied() {
        return "login";
    }
    
    @GetMapping("/registration")
    public String registrationForm(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }
       
    @PostMapping("/registration")
    public String registration(@ModelAttribute User userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect: /registration";
        }
        userService.save(userForm);
        return "redirect:/login";
    } 
}
