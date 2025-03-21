package p.ernest.swift.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import p.ernest.swift.database.entity.Address;
import p.ernest.swift.database.entity.TimeZone;
import p.ernest.swift.database.repository.AddressRepository;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;


    public Address createAddressIfNotExists(String address, String townName, String countryName, TimeZone timeZone) {
        return addressRepository.findByAddressAndTownNameAndCountryNameAndTimeZone(address, townName, countryName, timeZone)
                .orElseGet(() -> addressRepository.save(new Address(address, townName, countryName, timeZone)));
    }



}
