package p.ernest.swift.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import p.ernest.swift.database.entity.CountryIso2Code;
import p.ernest.swift.database.repository.CountryIso2CodeRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountryIso2CodeService {

    private final CountryIso2CodeRepository countryIso2CodeRepository;

    public CountryIso2Code createCountryIso2CodeIfNotExists(String countryIso2Code){
        return countryIso2CodeRepository.findByIso2Code(countryIso2Code)
                .orElseGet(() -> countryIso2CodeRepository.save(new CountryIso2Code(countryIso2Code)));
    }

    public Optional<CountryIso2Code> getCountryIso2CodeFromString(String countryIso2Code){
        return countryIso2CodeRepository.findByIso2Code(countryIso2Code);
    }

}
