package p.ernest.swift.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import p.ernest.swift.database.entity.CountryIso2Code;

import java.util.Optional;

@Repository
public interface CountryIso2CodeRepository extends JpaRepository<CountryIso2Code, Integer> {

    Optional<CountryIso2Code> findByIso2Code(String iso2Code);
}
