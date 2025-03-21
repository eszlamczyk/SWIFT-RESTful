package p.ernest.swift.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import p.ernest.swift.database.entity.CodeType;

import java.util.Optional;

@Repository
public interface CodeTypeRepository extends JpaRepository<CodeType, Integer> {

    Optional<CodeType> findByCodeType(String codeType);

}
