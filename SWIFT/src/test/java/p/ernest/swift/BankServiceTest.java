package p.ernest.swift;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import p.ernest.swift.database.entity.*;
import p.ernest.swift.database.repository.BankRepository;
import p.ernest.swift.service.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BankServiceTest {

    @Mock
    private BankRepository bankRepository;

    @Mock
    private BankNameService bankNameService;

    @Mock
    private TimeZoneService timeZoneService;

    @Mock
    private CodeTypeService codeTypeService;

    @Mock
    private AddressService addressService;

    @Mock
    private CountryIso2CodeService countryIso2CodeService;

    @InjectMocks
    private BankService bankService;

    private final String swiftCode = "ABCDUS33XXX";
    private final String countryIso2 = "US";
    private final String codeType = "BIC";
    private final String bankName = "Test Bank";
    private final String address = "123 Test Street";
    private final String town = "Test Town";
    private final String country = "USA";
    private final String timeZone = "UTC";

    @BeforeEach
    void setup() {
        reset(bankRepository);
    }

    @Test
    void testAddNewBank_Success() {
        when(bankRepository.existsBySwiftCode(swiftCode)).thenReturn(false);
        when(timeZoneService.createTimeZoneIfNotExists(timeZone)).thenReturn(new TimeZone(timeZone));
        when(addressService.createAddressIfNotExists(any(), any(), any(), any())).thenReturn(new Address());
        when(countryIso2CodeService.createCountryIso2CodeIfNotExists(any())).thenReturn(new CountryIso2Code());
        when(codeTypeService.createCodeTypeIfNotExists(any())).thenReturn(new CodeType());
        when(bankNameService.createBankNameIfNotExists(any())).thenReturn(new BankName());
        when(bankRepository.save(any())).thenReturn(new Bank());

        Bank createdBank = bankService.addNewBank(swiftCode, countryIso2, codeType, bankName, address, town, country, timeZone);

        assertNotNull(createdBank);
        verify(bankRepository, times(1)).save(any());
    }

    @Test
    void testAddNewBank_ThrowsExceptionWhenBankExists() {
        when(bankRepository.existsBySwiftCode(swiftCode)).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> bankService.addNewBank(swiftCode, countryIso2, codeType, bankName, address, town, country, timeZone));
    }

    @Test
    void testIsHeadquarters_True() {
        assertTrue(bankService.isHeadquarters("ABCDUS33XXX"));
        assertTrue(bankService.isHeadquarters("XXX"));
    }

    @Test
    void testIsHeadquarters_False() {
        assertFalse(bankService.isHeadquarters("ABCDUS33YYY"));
    }

    @Test
    void testGetAmountOfBanks() {
        when(bankRepository.findAll()).thenReturn(List.of(new Bank(), new Bank()));

        assertEquals(2, bankService.getAmountOfBanks());
    }

    @Test
    void testRemoveBank_Success() {
        when(bankRepository.findBankBySwiftCode(swiftCode)).thenReturn(Optional.of(new Bank()));

        boolean result = bankService.removeBank(swiftCode);

        assertTrue(result);
        verify(bankRepository, times(1)).removeBankBySwiftCode(swiftCode);
    }

    @Test
    void testRemoveBank_NotFound() {
        when(bankRepository.findBankBySwiftCode(swiftCode)).thenReturn(Optional.empty());

        boolean result = bankService.removeBank(swiftCode);

        assertFalse(result);
        verify(bankRepository, never()).removeBankBySwiftCode(swiftCode);
    }

    @Test
    void testGetBankBySwiftCode_Found() {
        Bank bank = new Bank();
        when(bankRepository.getBankBySwiftCode(swiftCode)).thenReturn(Optional.of(bank));

        Optional<Bank> result = bankService.getBankBySwiftCode(swiftCode);

        assertTrue(result.isPresent());
        assertEquals(bank, result.get());
    }

    @Test
    void testGetBankBySwiftCode_NotFound() {
        when(bankRepository.getBankBySwiftCode(swiftCode)).thenReturn(Optional.empty());

        Optional<Bank> result = bankService.getBankBySwiftCode(swiftCode);

        assertFalse(result.isPresent());
    }
}
