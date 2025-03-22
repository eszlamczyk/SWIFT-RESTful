package p.ernest.swift.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HeadquarterDTO {

    public String address;
    public String bankName;
    public String countryISO2;
    public String countryName;
    @JsonProperty("isHeadquarter") //to remove jackson duplication of headquarter field
    public boolean isHeadquarter;
    @JsonProperty("swiftCode") //this is here just to be consistent with provided pattern
    public String swiftCode;

    @JsonProperty("branches") //this is here just to be consistent with provided pattern
    public List<CountryBankDTO> branches;


}
