package p.ernest.swift.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CountryISO2DTO {

    public String countryISO2;
    public String countryName;

    public List<CountryBankDTO> swiftCodes;

}
