package p.ernest.swift.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "addresses",
        uniqueConstraints = @UniqueConstraint(columnNames = {"address", "town_name", "country_name", "time_zone"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "town_name", nullable = false)
    private String townName;

    @Column(name = "country_name", nullable = false)
    private String countryName;

    @ManyToOne
    @JoinColumn(name = "time_zone", nullable = false)
    private TimeZone timeZone;

    public Address(String address, String townName, String countryName, TimeZone timeZone) {
        this.address = address;
        this.townName = townName;
        this.countryName = countryName;
        this.timeZone = timeZone;
    }
}