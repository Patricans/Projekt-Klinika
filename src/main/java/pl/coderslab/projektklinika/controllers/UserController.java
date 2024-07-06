package pl.coderslab.projektklinika.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.projektklinika.models.User;
import pl.coderslab.projektklinika.services.SecurityService;
import pl.coderslab.projektklinika.services.UserService;
import pl.coderslab.projektklinika.validator.UserValidator;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/rejestracja", method = RequestMethod.GET)
    public String rejestracja(Model model) {
        model.addAttribute("userForm", new User());
        return "rejestracja";
    }

    @RequestMapping(value = "/rejestracja", method = RequestMethod.POST)
    public String rejestracja(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("isFormResend", true);
            return "rejestracja";

        }
        userService.save(userForm);
        securityService.autologin(userForm.getEmail(), userForm.getPasswordConfirm());
        return "redirect:/";
    }

    @RequestMapping(value = "/logowanie", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Niepoprawne dane logowania");
        }
        if (logout != null) {
            model.addAttribute("message", "Wylogowano z systemu");
        }
        model.addAttribute("title", "Logowanie do systemu");

        return "logowanie";
    }

}
