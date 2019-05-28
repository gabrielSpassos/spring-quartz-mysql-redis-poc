package com.gabrielspassos.poc.repositories.mysql;

import com.gabrielspassos.poc.entities.mysql.BankStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankStatusRepository extends JpaRepository<BankStatusEntity, Long> {

    BankStatusEntity findTopByOrderByIdDesc();
}
