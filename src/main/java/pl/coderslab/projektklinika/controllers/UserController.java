package pl.coderslab.projektklinika.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.projektklinika.models.PasswordChangeForm;
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
        model.addAttribute("title", "Rejestracja");
        return "rejestracja";
    }

    @RequestMapping(value = "/rejestracja", method = RequestMethod.POST)
    public String rejestracja(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("isFormResend", true);
            model.addAttribute("title", "Rejestracja");
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
    @RequestMapping(value="/wyloguj", method = RequestMethod.GET)
    public String logout(Model model) {
        SecurityContextHolder.getContext().setAuthentication(null); // wyloguj uzytkownika jestli jestes zalogowany
        return "redirect:/";
    }
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        model.addAttribute("userForm", user);
        model.addAttribute("passwordChange", new PasswordChangeForm());
        model.addAttribute("title", "Twój Profil");
        return "profile";
    }
    @RequestMapping(value = "/zmien_haslo", method = RequestMethod.POST)
    public String changePassword(@ModelAttribute("passwordChange") PasswordChangeForm form,
                                 Model model, @AuthenticationPrincipal UserDetails userDetails,
                                 RedirectAttributes redirectAttributes, BindingResult bindingResult) {

        User user = userService.findByEmail(userDetails.getUsername());
        model.addAttribute("userForm", user);

        if(!form.getNewPassword().equals(form.getNewPassword2())) {
            bindingResult.rejectValue("newPassword2", "PasswordConfirm.invalid");
        }

        else if (form.getNewPassword().length() < 8){
            bindingResult.rejectValue("newPassword2", "Password.tooShort");
        }
        if(bindingResult.hasErrors()) {
            return "profile";
        }
        try {
            userService.changePassword(userDetails.getUsername(), form.getOldPassword(), form.getNewPassword());
            redirectAttributes.addFlashAttribute("flashClass", "alert-success");
            redirectAttributes.addFlashAttribute("flashMessage", "Hasło zostało zmienione");
            return "redirect:/profile";
        }
        catch (BadCredentialsException e) {
            bindingResult.rejectValue("oldPassword","Password.invalid");
            form.setOldPassword(null);
            form.setNewPassword(null);
            form.setNewPassword2(null);
            return "profile";
        }
    }

}
