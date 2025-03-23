package p.ernest.swift;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import p.ernest.swift.database.entity.Address;
import p.ernest.swift.database.entity.TimeZone;
import p.ernest.swift.database.repository.AddressRepository;
import p.ernest.swift.service.AddressService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    @Test
    void testCreateAddressIfNotExists_NewAddress() {
        TimeZone timeZone = new TimeZone("UTC");
        Address newAddress = new Address("123 TEST STREET", "TEST TOWN", "TEST COUNTRY", timeZone);

        when(addressRepository.findByAddressAndTownNameAndCountryNameAndTimeZone(
                anyString(), anyString(), anyString(), any(TimeZone.class)))
                .thenReturn(Optional.empty());
        when(addressRepository.save(any(Address.class))).thenReturn(newAddress);

        Address result = addressService.createAddressIfNotExists("123 Test Street", "Test Town",
                "Test Country", timeZone);
        assertNotNull(result);
        assertEquals("123 TEST STREET", result.getAddress());
        assertEquals("TEST TOWN", result.getTownName());
        assertEquals("TEST COUNTRY", result.getCountryName());
    }

}
