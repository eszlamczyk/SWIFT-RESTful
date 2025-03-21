package p.ernest.swift.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import p.ernest.swift.database.entity.TimeZone;
import p.ernest.swift.database.repository.TimeZoneRepository;

@Service
@RequiredArgsConstructor
public class TimeZoneService {

    private final TimeZoneRepository timeZoneRepository;


    public TimeZone createTimeZoneIfNotExists(String timeZone) {
        return timeZoneRepository.findByTimeZone(timeZone)
                .orElseGet(() -> timeZoneRepository.save(new TimeZone(timeZone)));
    }

}
