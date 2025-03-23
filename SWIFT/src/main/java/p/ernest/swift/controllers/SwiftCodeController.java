package p.ernest.swift.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import p.ernest.swift.database.entity.Bank;
import p.ernest.swift.service.AddressService;
import p.ernest.swift.service.BankService;
import p.ernest.swift.service.SwiftCodesService;
import p.ernest.swift.service.dto.CountryISO2DTO;
import p.ernest.swift.service.dto.BankDTO;
import p.ernest.swift.service.dto.HeadquarterDTO;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/v1/swift-codes")
@RequiredArgsConstructor
public class SwiftCodeController {

    private final SwiftCodesService swiftCodesService;
    private final BankService bankService;
    private final AddressService addressService;

    @GetMapping("{swift-code}")
    public ResponseEntity<?> getBank(@PathVariable("swift-code") String swiftCode){
        if (bankService.isHeadquarters(swiftCode)){
            Optional<HeadquarterDTO> optionalHeadquarterDTO = swiftCodesService.getHeadquarterDTO(swiftCode);

            if (optionalHeadquarterDTO.isEmpty()){
                return ResponseEntity.status(404).body(
                        Map.of(
                                "error", "Bank with swift code not found",
                                "status", 404));
            }

            return ResponseEntity.ok(optionalHeadquarterDTO.get());
        }

        Optional<BankDTO> optionalBranchDTO = swiftCodesService.getBranchDTO(swiftCode);

        if(optionalBranchDTO.isEmpty()){
            return ResponseEntity.status(404).body(
                Map.of(
                    "error", "Bank with swift code not found",
                    "status", 404));
        }

        return ResponseEntity.ok(optionalBranchDTO.get());

    }


    @GetMapping("/country/{countryISO2code}")
    public ResponseEntity<?> getSwiftCodesByCountry(@PathVariable String countryISO2code) {
        Optional<CountryISO2DTO> optionalResultDTO = swiftCodesService.getSwiftCodesByCountry(countryISO2code);

        if (optionalResultDTO.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of(
                    "error", "Swift codes not found",
                    "status", 404
            ));
        }

        return ResponseEntity.ok(optionalResultDTO.get());
    }

    @PostMapping
    public ResponseEntity<?> addNewBank(@RequestBody BankDTO bankDTO){
        Bank bank;
        try{
        bank = bankService.addNewBank(
                bankDTO.swiftCode.toUpperCase(),
                bankDTO.countryISO2.toUpperCase(),
                "BIC11",
                bankDTO.bankName.toUpperCase(),
                bankDTO.address.toUpperCase(),
                "",
                bankDTO.countryName.toUpperCase(),
                addressService.getTimeZoneFromCountryName(
                        bankDTO.countryName));}
        catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
        return ResponseEntity.ok().body(
                Map.of("message","Successfully created new Bank with SWIFT code " + bank.getSwiftCode()));
    }

    @DeleteMapping("/{swift-code}")
    public ResponseEntity<?> deleteBank(@PathVariable("swift-code") String swiftCode){
        if (bankService.removeBank(swiftCode)){
            return ResponseEntity.ok().body(Map.of("message", "Successfully removed the bank"));
        }
        return ResponseEntity.badRequest().body(Map.of("message", "Bank with provided SWIFT Code does not exist"));
    }


}
