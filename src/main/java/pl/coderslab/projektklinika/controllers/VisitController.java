
package pl.coderslab.projektklinika.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.projektklinika.models.User;
import pl.coderslab.projektklinika.models.Visit;
import pl.coderslab.projektklinika.models.VisitComment;
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
    @GetMapping(value="/doktor/wizyta/{id}")
    public String displayDoctorVisit(Model model,@AuthenticationPrincipal UserDetails userDetails, @PathVariable int id) {
        User doctor = userService.findByEmail(userDetails.getUsername());
        Visit visit = visitRepository.getVisitById(id,doctor);
        if(visit == null) {
            throw new RuntimeException("Nie ma takiej wizyty w bazie danych");
        }
        model.addAttribute("title", "Szczegóły wizyty");
        model.addAttribute("visit", visit);
        List<Visit> visits = visitRepository.getPatientVisits(visit.getPatient());
        model.addAttribute("visits", visits);
        VisitComment visitComment = new VisitComment();
        if (visit.getDoctorNotes() !=null){
            visitComment.setComment(visit.getDoctorNotes());
        }
        model.addAttribute("visitComment", visitComment);
        return "visits-details";
    }
    @PostMapping(value ="/doktor/wizyta/{id}")
    public  String updateDoctorNotes(Model model, @AuthenticationPrincipal UserDetails userDetails, @PathVariable int id, VisitComment visitComment, RedirectAttributes redirectAttributes) {
        User doctor = userService.findByEmail(userDetails.getUsername());
        Visit visit = visitRepository.getVisitById(id,doctor);
        if(visit == null) {
            throw new RuntimeException("Nie ma takiej wizytywyty");
        }
        visit.setDoctorNotes(visitComment.getComment());
        visitRepository.save(visit);
        redirectAttributes.addFlashAttribute("flashClass", "alert-success");
        redirectAttributes.addFlashAttribute("flashMessage", "Zapisano notatki");
        return String.format("redirect:/doktor/wizyta/%d", visit.getId());
    }
}
