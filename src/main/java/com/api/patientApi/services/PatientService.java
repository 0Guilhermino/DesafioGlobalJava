package com.api.patientApi.services;

import com.api.patientApi.models.PatientModel;
import com.api.patientApi.repositories.PatientRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientService {

    final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Transactional
    public PatientModel save(PatientModel patientModel) {
        return patientRepository.save(patientModel);
    }

    public boolean existsByHealthInsuranceCardId(String healthInsuranceCardId) {
        return patientRepository.existsByHealthInsuranceCardId(healthInsuranceCardId);
    }

    public List<PatientModel> findAll() {
        return patientRepository.findAll();
    }


    public Optional<PatientModel> findById(Integer id) {
        return patientRepository.findById(id);
    }

    @Transactional
    public void delete(PatientModel patientModel) {
        patientRepository.delete(patientModel);
    }
}
