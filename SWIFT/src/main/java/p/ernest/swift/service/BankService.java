package p.ernest.swift.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import p.ernest.swift.database.entity.*;
import p.ernest.swift.database.repository.BankRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankService {

    private final BankRepository bankRepository;
    private final BankNameService bankNameService;
    private final TimeZoneService timeZoneService;
    private final CodeTypeService codeTypeService;
    private final AddressService addressService;
    private final CountryIso2CodeService countryIso2CodeService;


    public Bank addNewBank(String swiftCode, String countryIso2Code, String codeType, String name,
                                     String address, String townName, String countryName, String timeZone){

        if (bankRepository.existsBySwiftCode(swiftCode)){
            throw new IllegalArgumentException(String.format("Bank with specified SWIFT code (%s) already exists",
                    swiftCode));
        }

        TimeZone resultTimeZone = timeZoneService.createTimeZoneIfNotExists(timeZone);

        Address resultAddress = addressService.createAddressIfNotExists(address, townName, countryName, resultTimeZone);

        CountryIso2Code resultCountryIso2Code = countryIso2CodeService.createCountryIso2CodeIfNotExists(countryIso2Code);

        CodeType resultCodeType = codeTypeService.createCodeTypeIfNotExists(codeType);

        BankName resultBankName = bankNameService.createBankNameIfNotExists(name);

        if (isHeadquarters(swiftCode)){
            return bankRepository.save(new Bank(swiftCode ,resultCodeType, resultBankName,
                    resultCountryIso2Code, resultAddress, true));
        }

        Optional<Bank> optionalHeadquarters = getHeadquartersFromPrefix(swiftCode);

        return optionalHeadquarters.map(bank -> bankRepository.save(new Bank(swiftCode, resultCodeType, resultBankName,
                    resultCountryIso2Code, resultAddress, false, bank)))
                .orElseGet(() -> bankRepository.save(new Bank(swiftCode, resultCodeType, resultBankName,
                    resultCountryIso2Code, resultAddress, false, null)));

    }

    private boolean isHeadquarters(String swiftCode){
        if (swiftCode.length() == 3){
            return swiftCode.equals("XXX");
        } else if (swiftCode.length() > 3) {
            return swiftCode.endsWith("XXX");
        }else {
            throw new IllegalArgumentException("Swift code has fewer than 3 characters!");
        }
    }

    private Optional<Bank> getHeadquartersFromPrefix(String swiftCode){
        String swiftCodePrefix = swiftCode.substring(0,8);
        List<Bank> potentialHeadquarter = bankRepository.findBySwiftCodeStartingWith(swiftCodePrefix);

        for (Bank bank: potentialHeadquarter){
            if (isHeadquarters(bank.getSwiftCode())){
                return Optional.of(bank);
            }
        }
        return Optional.empty();
    }

    public int getAmountOfBanks(){
        return bankRepository.findAll().size();
    }

}
