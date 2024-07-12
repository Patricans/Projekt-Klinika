package pl.coderslab.projektklinika.controllers;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.projektklinika.components.AvailableTimeslot;
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
import pl.coderslab.projektklinika.forms.SelectTimeslot;
import pl.coderslab.projektklinika.models.*;
import pl.coderslab.projektklinika.repositories.*;
import pl.coderslab.projektklinika.services.SecurityService;
import pl.coderslab.projektklinika.services.UserService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private ScheduleRepository scheduleRepository;

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

    @PostMapping(value="/pacjent/umow_wizyte/2")
    public String scheduleVisit(Model model, @AuthenticationPrincipal UserDetails userDetails, SelectDoctor selectDoctor) {
        List<AvailableTimeslot> availableTimeslots = new ArrayList<>();
        User doctor = selectDoctor.getDoctor();
        List<Schedule> schedules = scheduleRepository.findByUser(doctor);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, 2); // rezerwacja minimum 2 godziny przed terminem
        Date minStartDate = calendar.getTime();
        final int timeSlotMinutes = 30;
        final int maxTimeslots = 200;
        for (Schedule schedule : schedules) {
            Date startDate = schedule.getStartDate();
            calendar.setTime(schedule.getEndDate());
            calendar.add(Calendar.MINUTE, -timeSlotMinutes);
            Date lastTimeSlot = calendar.getTime(); // ostatni mozliwy termin w tym przedziale, -30 minut od końca

            if(startDate.before(minStartDate)) {
                startDate = (Date) minStartDate.clone();
            }

            // zaokrąglenie początku przedziału do 30 minutowych przedziałów
            // zmiana na inną wartość przedziału wymaga poważniejszego kodu
            if(startDate.getMinutes() % timeSlotMinutes != 0) {
                if(startDate.getMinutes() < 30) {
                    startDate.setMinutes(30);
                } else {
                    startDate.setMinutes(0);
                    calendar.setTime(startDate);
                    calendar.add(Calendar.HOUR_OF_DAY, 1);
                }
            }

            while(startDate.before(lastTimeSlot) && availableTimeslots.size() < maxTimeslots) {
                calendar.setTime(startDate);
                calendar.add(Calendar.MINUTE, timeSlotMinutes);
                Date endDate = calendar.getTime();
                Visit v = visitRepository.getVisitInTimeSlot(doctor, startDate, endDate);
                if(v == null) {
                    AvailableTimeslot av = new AvailableTimeslot();
                    av.setStart(startDate);
                    av.setEnd(endDate);
                    availableTimeslots.add(av);
                }
                startDate = endDate;
            }
        }
        model.addAttribute("availableTimeslots", availableTimeslots);
        model.addAttribute("scheduleStep", 3);
        model.addAttribute("doctor", doctor);
        model.addAttribute("selectedTimeslot", new SelectTimeslot());
        return "patient-schedule-visit";
    }

    @PostMapping(value="/pacjent/umow_wizyte/3")
    public String scheduleVisit(Model model, @AuthenticationPrincipal UserDetails userDetails, SelectTimeslot selectTimeslot, RedirectAttributes redirectAttributes) {
        User patient = userService.findByEmail(userDetails.getUsername());
        User doctor = selectTimeslot.getDoctor();
        Visit visit = new Visit();
        visit.setPatient(patient);
        visit.setDoctor(doctor);
        visit.setStartDate(selectTimeslot.getSelectedTimeslot().getStart());
        long durationInMillis = selectTimeslot.getSelectedTimeslot().getEnd().getTime() - selectTimeslot.getSelectedTimeslot().getStart().getTime();
        visit.setDuration((int) TimeUnit.MINUTES.convert(durationInMillis, TimeUnit.MILLISECONDS));
        visitRepository.save(visit);
        redirectAttributes.addAttribute("flashClass", "alert-success");
        redirectAttributes.addAttribute("flashMessage", "Zapisano wizytę");
        return "redirect:/pacjent/wizyty/aktualne";
    }
}
