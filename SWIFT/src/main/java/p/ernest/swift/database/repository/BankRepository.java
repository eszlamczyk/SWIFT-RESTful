package p.ernest.swift.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import p.ernest.swift.database.entity.Bank;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> {

    List<Bank> findBySwiftCodeStartingWith(String swiftCodePrefix);

    boolean existsBySwiftCode(String swiftCode);
}
