package p.ernest.swift.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "country_iso2_codes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryIso2Code {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "iso2_code", nullable = false, unique = true)
    private String iso2Code;


    public CountryIso2Code(String iso2Code) {
        this.iso2Code = iso2Code;
    }
}