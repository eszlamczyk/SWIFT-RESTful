package p.ernest.swift;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import p.ernest.swift.database.entity.BankName;
import p.ernest.swift.database.repository.BankNameRepository;
import p.ernest.swift.service.BankNameService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BankNameTest {

    @Mock
    private BankNameRepository bankNameRepository;

    @InjectMocks
    private BankNameService bankNameService;

    @Test
    void testCreateBankNameIfNotExists_NewBankName() {
        when(bankNameRepository.findByBankName("TEST BANK")).thenReturn(Optional.empty());
        when(bankNameRepository.save(any(BankName.class))).thenReturn(new BankName("TEST BANK"));

        BankName result = bankNameService.createBankNameIfNotExists("Test Bank");
        assertNotNull(result);
        assertEquals("TEST BANK", result.getBankName());
    }


}
