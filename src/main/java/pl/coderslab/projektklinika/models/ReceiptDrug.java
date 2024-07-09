package pl.coderslab.projektklinika.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Entity
@Table(name = "receipt_drugs")
public class ReceiptDrug {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "drug_id", nullable = false)
    @Setter
    private Drug drug;

    @ManyToOne
    @JoinColumn(name = "ereceipt_id", nullable = false)
    @Setter
    private EReceipt receipt;

    @Column(name = "amount", nullable = false)
    @Setter
    private double amount;

    @Column(name = "expiration_date", nullable = true, columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    @Setter
    private Date expirationDate;

    @Column(name = "last_purchased", nullable = true, columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    @Setter
    private Date lastPurchased;
}

