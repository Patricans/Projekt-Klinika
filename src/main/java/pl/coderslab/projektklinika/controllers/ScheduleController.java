package pl.coderslab.projektklinika.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.projektklinika.models.Schedule;
import pl.coderslab.projektklinika.models.User;
import pl.coderslab.projektklinika.repositories.ScheduleRepository;
import pl.coderslab.projektklinika.services.SecurityService;
import pl.coderslab.projektklinika.services.UserService;

import java.util.List;

@Controller
public class ScheduleController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @GetMapping(value="/dyzury")
    public String displaySchedule(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        List<Schedule> schedules = scheduleRepository.findByUser(user);
        model.addAttribute("schedules", schedules);
        model.addAttribute("user", user);
        model.addAttribute("scheduleCount", schedules.size());
        return "schedule";
    }
}
