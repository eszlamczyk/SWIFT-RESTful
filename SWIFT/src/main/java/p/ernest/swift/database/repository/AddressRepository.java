package p.ernest.swift.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import p.ernest.swift.database.entity.Address;
import p.ernest.swift.database.entity.TimeZone;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    Optional<Address> findByAddressAndTownNameAndCountryNameAndTimeZone(String address,
                                                                        String townName,
                                                                        String countryName,
                                                                        TimeZone timeZone);
}
