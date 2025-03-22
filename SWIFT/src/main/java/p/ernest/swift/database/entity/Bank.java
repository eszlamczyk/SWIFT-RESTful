package p.ernest.swift.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "banks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "swift_code", nullable = false, unique = true)
    private String swiftCode;

    @ManyToOne
    @JoinColumn(name = "code_type", nullable = false)
    private CodeType codeType;

    @ManyToOne
    @JoinColumn(name = "bank_name", nullable = false)
    private BankName bankName;

    @ManyToOne
    @JoinColumn(name = "country_iso2_code", nullable = false)
    private CountryIso2Code countryIso2Code;

    @ManyToOne
    @JoinColumn(name = "address", nullable = false)
    private Address address;

    @Column(name = "is_headquarter", nullable = false)
    private Boolean isHeadquarter;

    @ManyToOne
    @JoinColumn(name = "headquarter")
    private Bank headquarter;

    public Bank(String swiftCode, CodeType codeType, BankName bankName, CountryIso2Code countryIso2Code,
                Address address, boolean isHeadquarter){
        this.swiftCode = swiftCode;
        this.codeType = codeType;
        this.bankName = bankName;
        this.countryIso2Code = countryIso2Code;
        this.address = address;
        this.isHeadquarter = isHeadquarter;
    }

    public Bank(String swiftCode, CodeType codeType, BankName bankName, CountryIso2Code countryIso2Code,
                Address address, boolean isHeadquarter, Bank headquarter){
        this.swiftCode = swiftCode;
        this.codeType = codeType;
        this.bankName = bankName;
        this.countryIso2Code = countryIso2Code;
        this.address = address;
        this.isHeadquarter = isHeadquarter;
        this.headquarter = headquarter;
    }
}