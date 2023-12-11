package com.nexus.api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IndividualRepository extends JpaRepository<Individual, Long> {
    // You can add custom query methods here if needed
    Optional<Individual> findByEmailAndPasswordHash(String email, String passwordHash);
}
