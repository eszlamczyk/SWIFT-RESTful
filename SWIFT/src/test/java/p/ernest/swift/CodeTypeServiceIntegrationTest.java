package p.ernest.swift;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import p.ernest.swift.database.entity.CodeType;
import p.ernest.swift.database.repository.CodeTypeRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CodeTypeServiceIntegrationTest {

    @Autowired
    private CodeTypeRepository codeTypeRepository;

    @Test
    void testCreateCodeTypeIfNotExists_Integration() {
        CodeType codeType = codeTypeRepository.save(new CodeType("BIC"));
        Optional<CodeType> result = codeTypeRepository.findByCodeType("BIC");

        assertEquals("BIC", codeType.getCodeType());
        assertTrue(result.isPresent());
        assertEquals("BIC", result.get().getCodeType());
    }


}
