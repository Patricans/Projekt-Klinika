package pl.coderslab.projektklinika.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.projektklinika.components.DrugCart;
import pl.coderslab.projektklinika.models.EReceipt;
import pl.coderslab.projektklinika.models.User;
import pl.coderslab.projektklinika.repositories.DrugRepository;
import pl.coderslab.projektklinika.repositories.ReceiptDrugRepository;
import pl.coderslab.projektklinika.repositories.ReceiptRepository;
import pl.coderslab.projektklinika.repositories.VisitRepository;
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

    @GetMapping(value = "/pacjent/recepty")
    public String getPatientReceipts(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User patient = userService.findByEmail(userDetails.getUsername());
        List<EReceipt> eReceiptList = receiptRepository.findByPatient(patient);
        model.addAttribute("eReceiptList", eReceiptList);
        return "patient-receipts";
    }
}