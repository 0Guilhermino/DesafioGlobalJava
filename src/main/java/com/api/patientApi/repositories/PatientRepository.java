package com.api.patientApi.repositories;

import com.api.patientApi.models.PatientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<PatientModel, UUID> {
    boolean existsByHealthInsuranceCardId(String healthInsuranceCardId);
}
