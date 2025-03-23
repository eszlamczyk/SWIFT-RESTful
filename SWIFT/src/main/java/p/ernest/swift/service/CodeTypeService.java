package p.ernest.swift.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import p.ernest.swift.database.entity.CodeType;
import p.ernest.swift.database.repository.CodeTypeRepository;

@Service
@RequiredArgsConstructor
public class CodeTypeService {
    private final CodeTypeRepository codeTypeRepository;

    public CodeType createCodeTypeIfNotExists(String codeType){
        String finalCodeType = codeType.toUpperCase();
        return codeTypeRepository.findByCodeType(finalCodeType)
                .orElseGet(() -> codeTypeRepository.save(new CodeType(finalCodeType)));
    }
}
