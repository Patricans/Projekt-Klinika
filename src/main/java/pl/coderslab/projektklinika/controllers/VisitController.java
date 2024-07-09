
package pl.coderslab.projektklinika.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.projektklinika.models.User;
import pl.coderslab.projektklinika.models.Visit;
import pl.coderslab.projektklinika.repositories.ScheduleRepository;
import pl.coderslab.projektklinika.repositories.VisitRepository;
import pl.coderslab.projektklinika.services.SecurityService;
import pl.coderslab.projektklinika.services.UserService;

import java.util.List;

@Controller
public class VisitController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private VisitRepository visitRepository;

    @GetMapping(value = "/doktor/wizyty")
    public String displayDoctorVisits(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User doctor = userService.findByEmail(userDetails.getUsername());
        model.addAttribute("title", "Wizyty");
        List<Visit> visits = visitRepository.getDoctorVisits(doctor);
        model.addAttribute("visits", visits);
        return "visits";
    }
}
