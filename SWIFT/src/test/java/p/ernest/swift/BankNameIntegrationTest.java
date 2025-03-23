package p.ernest.swift;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import p.ernest.swift.database.entity.BankName;
import p.ernest.swift.database.repository.BankNameRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BankNameIntegrationTest {

    @Autowired
    private BankNameRepository bankNameRepository;


    @Test
    void testCreateBankNameIfNotExists_Integration() {
        BankName bankName = bankNameRepository.save(new BankName("Test Bank"));
        Optional<BankName> result = bankNameRepository.findByBankName("Test Bank");

        assertEquals("Test Bank", bankName.getBankName());
        assertTrue(result.isPresent());
        assertEquals("Test Bank", result.get().getBankName());
    }

}
