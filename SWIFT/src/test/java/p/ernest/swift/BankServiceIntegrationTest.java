package p.ernest.swift;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import p.ernest.swift.database.entity.*;
import p.ernest.swift.database.repository.BankRepository;
import p.ernest.swift.service.BankService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@Transactional
class BankServiceIntegrationTest {

    @Autowired
    private BankService bankService;

    @Autowired
    private BankRepository bankRepository;

    @Test
    void testAddNewBank_Integration() {
        String swiftCode = "ABCDUS33XXX";
        String countryIso2 = "US";
        String codeType = "BIC";
        String bankName = "Test Bank";
        String address = "123 Test Street";
        String town = "Test Town";
        String country = "USA";
        String timeZone = "UTC";

        Bank savedBank = bankService.addNewBank(swiftCode, countryIso2, codeType, bankName, address, town, country, timeZone);
        assertNotNull(savedBank);
        assertEquals(swiftCode, savedBank.getSwiftCode());
    }

    @Test
    void testRemoveBank_Integration() {
        String swiftCode = "XYZ12345XXX";
        bankService.addNewBank(swiftCode, "US", "BIC", "Sample Bank", "456 Street", "Sample Town", "USA", "UTC");

        boolean removed = bankService.removeBank(swiftCode);
        assertTrue(removed);

        Optional<Bank> deletedBank = bankRepository.getBankBySwiftCode(swiftCode);
        assertFalse(deletedBank.isPresent());
    }
}
