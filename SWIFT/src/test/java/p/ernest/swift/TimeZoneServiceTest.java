package p.ernest.swift;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import p.ernest.swift.database.entity.TimeZone;
import p.ernest.swift.database.repository.TimeZoneRepository;
import p.ernest.swift.service.TimeZoneService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TimeZoneServiceTest {

    @Mock
    private TimeZoneRepository timeZoneRepository;

    @InjectMocks
    private TimeZoneService timeZoneService;


    @Test
    void testCreateTimeZoneIfNotExists_NewTimeZone() {
        when(timeZoneRepository.findByTimeZone("UTC")).thenReturn(Optional.empty());
        when(timeZoneRepository.save(any(TimeZone.class))).thenReturn(new TimeZone("UTC"));

        TimeZone result = timeZoneService.createTimeZoneIfNotExists("UTC");
        assertNotNull(result);
        assertEquals("UTC", result.getTimeZone());
    }

}
