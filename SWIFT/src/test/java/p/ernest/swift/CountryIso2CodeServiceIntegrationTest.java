package p.ernest.swift;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import p.ernest.swift.database.entity.CountryIso2Code;
import p.ernest.swift.database.repository.CountryIso2CodeRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CountryIso2CodeServiceIntegrationTest {


    @Autowired
    private CountryIso2CodeRepository countryIso2CodeRepository;

    @Test
    void testCreateCountryIso2CodeIfNotExists_Integration() {
        CountryIso2Code countryIso2Code = countryIso2CodeRepository.save(new CountryIso2Code("US"));
        Optional<CountryIso2Code> result = countryIso2CodeRepository.findByIso2Code("US");


        assertEquals("US", countryIso2Code.getIso2Code());
        assertTrue(result.isPresent());
        assertEquals("US", result.get().getIso2Code());
    }

}
