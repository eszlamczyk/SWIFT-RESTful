package p.ernest.swift.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import p.ernest.swift.database.entity.TimeZone;

import java.util.Optional;

@Repository
public interface TimeZoneRepository extends JpaRepository<TimeZone, Integer> {
    Optional<TimeZone> findByTimeZone(String timeZone);
}
