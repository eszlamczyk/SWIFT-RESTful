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
        String finalAddress = address.toUpperCase();
        String finalTownName = townName.toUpperCase();
        String finalCountryName = countryName.toUpperCase();
        return addressRepository.findByAddressAndTownNameAndCountryNameAndTimeZone(
                finalAddress, finalTownName, finalCountryName, timeZone)
                .orElseGet(() -> addressRepository.save(new Address(
                        finalAddress, finalTownName, finalCountryName, timeZone)));
    }

    public String getTimeZoneFromCountryName(String countryName){
        return addressRepository.getAddressByCountryName(countryName).getFirst().getTimeZone().getTimeZone();
    }
}
