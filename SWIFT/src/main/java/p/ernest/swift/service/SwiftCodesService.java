package p.ernest.swift.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import p.ernest.swift.database.entity.Bank;
import p.ernest.swift.database.entity.CountryIso2Code;
import p.ernest.swift.service.dto.BankDTO;
import p.ernest.swift.service.dto.CountryBankDTO;
import p.ernest.swift.service.dto.CountryISO2DTO;
import p.ernest.swift.service.dto.HeadquarterDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SwiftCodesService {

    private final CountryIso2CodeService iso2Service;

    private final BankService bankService;

    public Optional<CountryISO2DTO> getSwiftCodesByCountry(String countryISO2Code){
        Optional<CountryIso2Code> optionalIso2Code = iso2Service.getCountryIso2CodeFromString(countryISO2Code);

        if (optionalIso2Code.isEmpty()){
            return Optional.empty();
        }
        CountryIso2Code iso2Code = optionalIso2Code.get();

        List<Bank> allSwiftCodes = bankService.getAllBankWithCountryISO2(iso2Code);

        CountryISO2DTO resultDTO = new CountryISO2DTO();
        resultDTO.setCountryISO2(iso2Code.getIso2Code());
        resultDTO.setCountryName(allSwiftCodes.getFirst().getAddress().getCountryName());

        if (allSwiftCodes.isEmpty()){
            resultDTO.setSwiftCodes(new ArrayList<>());
            return Optional.of(resultDTO);
        }

        List<CountryBankDTO> resultBanksDTOList = new ArrayList<>();

        for (Bank bank :allSwiftCodes){
            resultBanksDTOList.add(
                    new CountryBankDTO(bank.getAddress().getAddress(), bank.getBankName().getBankName(),
                            iso2Code.getIso2Code(), bank.getIsHeadquarter(), bank.getSwiftCode())
            );
        }

        resultDTO.setSwiftCodes(resultBanksDTOList);

        return Optional.of(resultDTO);
    }

    public Optional<HeadquarterDTO> getHeadquarterDTO(String swiftCode){
        Optional<Bank> optionalHeadquarter = bankService.getBankBySwiftCode(swiftCode);

        if (optionalHeadquarter.isEmpty()){
            return  Optional.empty();
        }

        Bank headquarter = optionalHeadquarter.get();

        List<Bank> branches = bankService.getAllBankWithHeadquarterBank(headquarter);

        HeadquarterDTO resultDTO = new HeadquarterDTO();
        resultDTO.setAddress(headquarter.getAddress().getAddress());
        resultDTO.setBankName(headquarter.getBankName().getBankName());
        resultDTO.setCountryISO2(headquarter.getCountryIso2Code().getIso2Code());
        resultDTO.setCountryName(headquarter.getAddress().getCountryName());
        resultDTO.setSwiftCode(headquarter.getSwiftCode());

        List<CountryBankDTO> branchesDTOs = new ArrayList<>();

        for(Bank branch: branches){
            branchesDTOs.add(new CountryBankDTO(
                    branch.getAddress().getAddress(),
                    branch.getBankName().getBankName(),
                    branch.getCountryIso2Code().getIso2Code(),
                    branch.getIsHeadquarter(),
                    branch.getSwiftCode()
            ));
        }

        resultDTO.setBranches(branchesDTOs);

        return Optional.of(resultDTO);
    }

    public Optional<BankDTO> getBranchDTO(String swiftCode){
        Optional<Bank> optionalBranch = bankService.getBankBySwiftCode(swiftCode);

        if (optionalBranch.isEmpty()){
            return  Optional.empty();
        }

        Bank branch = optionalBranch.get();

        return Optional.of(
                new BankDTO(
                        branch.getAddress().getAddress(),
                        branch.getBankName().getBankName(),
                        branch.getCountryIso2Code().getIso2Code(),
                        branch.getAddress().getCountryName(),
                        branch.getIsHeadquarter(),
                        branch.getSwiftCode()));

    }

}
