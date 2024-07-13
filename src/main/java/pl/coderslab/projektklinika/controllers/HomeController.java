package pl.coderslab.projektklinika.controllers;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.projektklinika.forms.RateDoctor;
import pl.coderslab.projektklinika.models.User;
import pl.coderslab.projektklinika.models.UserScore;
import pl.coderslab.projektklinika.repositories.UserRepository;
import pl.coderslab.projektklinika.repositories.UserScoreRepository;
import pl.coderslab.projektklinika.services.UserService;
import pl.coderslab.projektklinika.services.UserServiceImpl;

@Controller
public class HomeController {
    @Autowired
    private UserScoreRepository scoreRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserScoreRepository userScoreRepository;

    @RequestMapping(value = "/")
    public String index() {
        return "redirect:/home";
    }

    @RequestMapping(value = "/home")
    public String home(Model model) {
        model.addAttribute("title", "Strona główna");
        return "home";
    }

    @RequestMapping(value = "/lokalizacja")
    public String lokalizacja(Model model) {
        model.addAttribute("title", "Lokalizacja kliniki");
        return "lokalizacja";
    }

    @GetMapping(value="/personel")
    public String displayPersonel(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = null;
        if(userDetails != null && userDetails.getUsername() != null) {
            user = userService.findByEmail(userDetails.getUsername());
        }
        model.addAttribute("user", user);
        model.addAttribute("title", "Lista personelu");
        model.addAttribute("doctors", userRepository.findAllActiveDoctors());
        model.addAttribute("nurses", userRepository.findAllActiveNurses());
        model.addAttribute("userScoreRepository", scoreRepository);
        model.addAttribute("rateDoctorForm", new RateDoctor());
        return "personel";
    }

    @PostMapping(value="/personel/ocena")
    public String addPersonnelScore(Model model, @AuthenticationPrincipal UserDetails userDetails, RateDoctor rateDoctor, RedirectAttributes redirectAttributes) {
        User issuer = userService.findByEmail(userDetails.getUsername());
        UserScore userScore = new UserScore();
        userScore.setUser(userRepository.findById(rateDoctor.getPersonnel_id()).get());
        userScore.setScore(rateDoctor.getScore());
        userScore.setIssuer(issuer);
        UserScore prev = userScoreRepository.getUserScore(issuer, userScore.getUser());
        if(prev != null) {
            prev.setScore(rateDoctor.getScore());
            userScoreRepository.save(prev);
        } else {
            userScoreRepository.save(userScore);
        }
        redirectAttributes.addFlashAttribute("flashMessage", "Zapisano ocenę");
        redirectAttributes.addFlashAttribute("flashClass", "alert-success");
        return "redirect:/personel";
    }
}
