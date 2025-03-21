package p.ernest.swift.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bank_names")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "bank_name", nullable = false)
    private String bankName;

    public BankName(String bankName) {
        this.bankName = bankName;
    }
}