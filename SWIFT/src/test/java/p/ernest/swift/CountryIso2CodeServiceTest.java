package p.ernest.swift;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import p.ernest.swift.database.entity.CountryIso2Code;
import p.ernest.swift.database.repository.CountryIso2CodeRepository;
import p.ernest.swift.service.CountryIso2CodeService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CountryIso2CodeServiceTest {

    @Mock
    private CountryIso2CodeRepository countryIso2CodeRepository;

    @InjectMocks
    private CountryIso2CodeService countryIso2CodeService;

    @Test
    void testCreateCountryIso2CodeIfNotExists_NewCountryCode() {
        when(countryIso2CodeRepository.findByIso2Code("US")).thenReturn(Optional.empty());
        when(countryIso2CodeRepository.save(any(CountryIso2Code.class))).thenReturn(new CountryIso2Code("US"));

        CountryIso2Code result = countryIso2CodeService.createCountryIso2CodeIfNotExists("US");
        assertNotNull(result);
        assertEquals("US", result.getIso2Code());
    }


}
