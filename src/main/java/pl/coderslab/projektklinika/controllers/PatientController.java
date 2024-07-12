package pl.coderslab.projektklinika.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.projektklinika.components.DrugCart;
import pl.coderslab.projektklinika.forms.SelectDoctor;
import pl.coderslab.projektklinika.forms.SelectDoctorSpeciality;
import pl.coderslab.projektklinika.models.Drug;
import pl.coderslab.projektklinika.models.EReceipt;
import pl.coderslab.projektklinika.models.User;
import pl.coderslab.projektklinika.repositories.*;
import pl.coderslab.projektklinika.services.SecurityService;
import pl.coderslab.projektklinika.services.UserService;

import java.util.List;

@Controller
public class PatientController {
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

    @Autowired
    private DoctorSpecialitiesRepository doctorSpecialitiesRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/pacjent/recepty")
    public String getPatientReceipts(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User patient = userService.findByEmail(userDetails.getUsername());
        List<EReceipt> eReceiptList = receiptRepository.findByPatient(patient);
        model.addAttribute("eReceiptList", eReceiptList);
        return "patient-receipts";
    }

    @GetMapping(value = "/apteka")
    public String getPharmacy(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        List<Drug> drugs = drugRepository.getAllDrugs();
        model.addAttribute("drugs", drugs);
        return "pharmacy";
    }

    @GetMapping(value = "/pacjent/umow_wizyte")
    public String scheduleVisit(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User patient = userService.findByEmail(userDetails.getUsername());
        model.addAttribute("scheduleStep",1);
        model.addAttribute("doctorSpecialities", doctorSpecialitiesRepository.getAllSorted());
        model.addAttribute("selectSpeciality", new SelectDoctorSpeciality());
        return "patient-schedule-visit";
    }

    @PostMapping(value = "/pacjent/umow_wizyte/1")
    public String scheduleVisit(Model model, @AuthenticationPrincipal UserDetails userDetails, SelectDoctorSpeciality selectSpeciality) {
        User patient = userService.findByEmail(userDetails.getUsername());
        model.addAttribute("scheduleStep", 2);
        model.addAttribute("specialityId", selectSpeciality.getSpeciality().getId());
        model.addAttribute("doctors", userRepository.findBySpeciality(selectSpeciality.getSpeciality().getId()));
        model.addAttribute("selectDoctor", new SelectDoctor());
        return "patient-schedule-visit";
    }

}