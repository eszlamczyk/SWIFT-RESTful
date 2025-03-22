package p.ernest.swift.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "time_zones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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