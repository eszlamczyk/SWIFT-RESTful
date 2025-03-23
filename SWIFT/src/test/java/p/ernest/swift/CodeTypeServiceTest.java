package p.ernest.swift;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import p.ernest.swift.database.entity.CodeType;
import p.ernest.swift.database.repository.CodeTypeRepository;
import p.ernest.swift.service.CodeTypeService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CodeTypeServiceTest {


    @Mock
    private CodeTypeRepository codeTypeRepository;


    @InjectMocks
    private CodeTypeService codeTypeService;

    @Test
    void testCreateCodeTypeIfNotExists_NewCodeType() {
        when(codeTypeRepository.findByCodeType("BIC")).thenReturn(Optional.empty());
        when(codeTypeRepository.save(any(CodeType.class))).thenReturn(new CodeType("BIC"));

        CodeType result = codeTypeService.createCodeTypeIfNotExists("BIC");
        assertNotNull(result);
        assertEquals("BIC", result.getCodeType());
    }

}
