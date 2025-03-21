package p.ernest.swift.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "code_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code_type", nullable = false, unique = true)
    private String codeType;

    public CodeType(String codeType) {
        this.codeType = codeType;
    }
}