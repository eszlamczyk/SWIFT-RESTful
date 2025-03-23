package p.ernest.swift;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import p.ernest.swift.database.entity.TimeZone;
import p.ernest.swift.database.repository.TimeZoneRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TimeZoneServiceIntegrationTest {


    @Autowired
    private TimeZoneRepository timeZoneRepository;


    @Test
    void testCreateTimeZoneIfNotExists_Integration() {
        TimeZone timeZone = timeZoneRepository.save(new TimeZone("UTC"));
        Optional<TimeZone> result = timeZoneRepository.findByTimeZone("UTC");

        assertEquals("UTC", timeZone.getTimeZone());
        assertTrue(result.isPresent());
        assertEquals("UTC", result.get().getTimeZone());
    }

}
