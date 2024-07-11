
package pl.coderslab.projektklinika.controllers;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.projektklinika.components.DrugCart;
import pl.coderslab.projektklinika.forms.AddDrugToCartForm;
import pl.coderslab.projektklinika.models.*;
import pl.coderslab.projektklinika.repositories.DrugRepository;
import pl.coderslab.projektklinika.repositories.ReceiptDrugRepository;
import pl.coderslab.projektklinika.repositories.ReceiptRepository;
import pl.coderslab.projektklinika.repositories.VisitRepository;
import pl.coderslab.projektklinika.services.SecurityService;
import pl.coderslab.projektklinika.services.UserService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class VisitController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private DrugCart drugCart;

    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private ReceiptDrugRepository receiptDrugRepository;

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
        model.addAttribute("addDrugToCardForm", new AddDrugToCartForm());
        model.addAttribute("drugCart", drugCart);
        model.addAttribute("drugs", drugRepository.getAllDrugs());
        return "visits-details";
    }
    @PostMapping(value ="/doktor/wizyta/notatka/{id}")
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

    @PostMapping(value = "/doktor/wizyta/{id}/dodaj_do_recepty")
    public  String addItemToReceipt(Model model, @AuthenticationPrincipal UserDetails userDetails, @PathVariable int id, AddDrugToCartForm addDrugToCartForm, RedirectAttributes redirectAttributes) {
        ReceiptDrug rd = new ReceiptDrug();
        rd.setDrug(addDrugToCartForm.getDrug());
        rd.setAmount(addDrugToCartForm.getQuantity());
        drugCart.getDrugs().add(rd);
        redirectAttributes.addFlashAttribute("flashClass", "alert-success");
        redirectAttributes.addFlashAttribute("flashMessage", "Dodano do recepty: %s - %d".formatted(addDrugToCartForm.getDrug().getName(), addDrugToCartForm.getQuantity()));
        return "redirect:/doktor/wizyta/" + id;
    }

    @PostMapping(value="/doktor/wizyta/{id}/wystaw_recepte")
    @Transactional
    public String issueReceipt(Model model, @AuthenticationPrincipal UserDetails userDetails, @PathVariable int id, RedirectAttributes redirectAttributes) {
        User doctor = userService.findByEmail(userDetails.getUsername());
        Visit visit = visitRepository.getVisitById(id,doctor);
        if(visit == null) {
            throw new RuntimeException("Nie ma takiej wizytywyty");
        }
        EReceipt eReceipt = new EReceipt();
        eReceipt.setVisit(visit);
        eReceipt.setDate(new Date());
        eReceipt.setDoctor(doctor);
        eReceipt.setPatient(visit.getPatient());
        receiptRepository.save(eReceipt);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR, 30*24);
        for(ReceiptDrug rd : drugCart.getDrugs()) {
            rd.setReceipt(eReceipt);
            rd.setExpirationDate(calendar.getTime());
            receiptDrugRepository.save(rd);
        }
        drugCart.getDrugs().clear();
        redirectAttributes.addFlashAttribute("flashClass", "alert-success");
        redirectAttributes.addFlashAttribute("flashMessage", "Wystawiono receptę");
        return "redirect:/doktor/wizyta/" + id;

    }
}