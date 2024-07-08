package pl.coderslab.projektklinika.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping(value="/")
    public String index(){
        return "redirect:/logowanie";
    }
    @RequestMapping(value="/home")
    public String home(Model model){
        model.addAttribute("title", "Strona główna");
        return "home";
    }
    @RequestMapping(value="/lokalizacja")
    public String lokalizacja(Model model){
        model.addAttribute("title", "Lokalizacja kliniki");
        return "lokalizacja";
    }
}
