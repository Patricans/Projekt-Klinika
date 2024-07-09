
package pl.coderslab.projektklinika.models;

        import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

        import java.util.Date;
import java.util.List;

        @Entity
@Table(name="e_receipt")
@Getter
public class EReceipt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

            @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @Setter
    private User patient;

            @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    @Setter
    private User doctor;

            @Column(name="date", columnDefinition = "DATETIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Setter
    private Date date;

            @ManyToOne
    @JoinColumn(name = "visit_id", nullable = false)
    @Setter
    private Visit visit;

            @OneToMany
    @JoinColumn(name="ereceipt_id")
    public List<ReceiptDrug> receiptDrugs;
    }
