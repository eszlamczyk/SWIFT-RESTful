package p.ernest.swift.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import p.ernest.swift.database.entity.BankName;
import p.ernest.swift.database.repository.BankNameRepository;

@Service
@RequiredArgsConstructor
public class BankNameService {

    private final BankNameRepository bankNameRepository;

    public BankName createBankNameIfNotExists(String bankName) {
        String finalBankName = bankName.toUpperCase();
        return bankNameRepository.findByBankName(finalBankName)
                .orElseGet(() -> bankNameRepository.save(new BankName(finalBankName)));
    }
}
