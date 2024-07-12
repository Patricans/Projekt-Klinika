package pl.coderslab.projektklinika.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Getter
@Entity
@Table(name = "visits")
public class Visit {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @Setter
    private User patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    @Setter
    private User doctor;

    @Column(name = "start_date", columnDefinition = "DATETIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Setter
    private Date startDate;

    @Column(name = "duration", nullable = false)
    @Setter
    private int duration;

    @Column(name = "doctor_notes", nullable = true)
    @Setter
    private String doctorNotes;

    @Column(name = "cancel", nullable = true, columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    @Setter
    private Date cancelDate;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "visit_id")
    List<EReceipt> eReceipts;

    public int getReceiptDrugCount() {
        int count = 0;
        for (EReceipt receipt : eReceipts) {
            count += receipt.receiptDrugs.size();
        }
        return count;
    }

    public Date getEndDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.MINUTE, duration);
        return calendar.getTime();
    }
}
