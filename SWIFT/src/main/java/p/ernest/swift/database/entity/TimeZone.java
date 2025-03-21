package p.ernest.swift.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "time_zones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeZone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "time_zone", nullable = false, unique = true)
    private String timeZone;

    public TimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}