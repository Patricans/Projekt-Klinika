package pl.coderslab.projektklinika.models;

import jakarta.persistence.*;

@Entity
@Table(name = "drugs")
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "vendor", nullable = false)
    public String vendor;

    @Column(name = "stock", nullable = false)
    public int count;

}
