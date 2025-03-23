package p.ernest.swift;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import p.ernest.swift.database.entity.Address;
import p.ernest.swift.database.entity.TimeZone;
import p.ernest.swift.database.repository.AddressRepository;
import p.ernest.swift.database.repository.TimeZoneRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AddressServiceIntegrationTest {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private TimeZoneRepository timeZoneRepository;


    @Test
    void testCreateAddressIfNotExists_Integration() {
        TimeZone timeZone = timeZoneRepository.save(new TimeZone("UTC"));
        Address newAddress = new Address("123 Test Street", "Test Town", "Test Country", timeZone);

        addressRepository.save(newAddress);
        Optional<Address> result = addressRepository.findByAddressAndTownNameAndCountryNameAndTimeZone(
                "123 Test Street", "Test Town", "Test Country", timeZone);

        assertTrue(result.isPresent());
        assertEquals("123 Test Street", result.get().getAddress());
        assertEquals("Test Town", result.get().getTownName());
        assertEquals("Test Country", result.get().getCountryName());
    }

}
