package p.ernest.swift.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import p.ernest.swift.database.entity.Bank;
import p.ernest.swift.database.entity.CountryIso2Code;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> {

    List<Bank> findBySwiftCodeStartingWith(String swiftCodePrefix);

    boolean existsBySwiftCode(String swiftCode);

    List<Bank> findAllByCountryIso2Code(CountryIso2Code countryIso2Code);

    Optional<Bank> findBankBySwiftCode(String swiftCode);

    void removeBankBySwiftCode(String swiftCode);

    List<Bank> findAllByHeadquarter(Bank headquarter);

    Optional<Bank> getBankBySwiftCode(String swiftCode);
}
