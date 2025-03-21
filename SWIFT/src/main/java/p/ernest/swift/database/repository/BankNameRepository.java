package p.ernest.swift.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import p.ernest.swift.database.entity.BankName;

import java.util.Optional;

@Repository
public interface BankNameRepository extends JpaRepository<BankName, Integer> {

    Optional<BankName> findByBankName(String bankName);
}
