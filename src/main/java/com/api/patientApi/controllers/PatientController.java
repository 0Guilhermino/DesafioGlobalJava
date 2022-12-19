package com.api.patientApi.controllers;

import com.api.patientApi.dtos.PatientDto;
import com.api.patientApi.models.PatientModel;
import com.api.patientApi.services.PatientService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/patient")
public class PatientController {

    final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<Object> savePatient(@RequestBody @Valid PatientDto patientDto){
        if(patientService.existsByHealthInsuranceCardId(patientDto.getHealthInsuranceCardId())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: HealthInsuranceCardId is already in use");
        }

        PatientModel patientModel = new PatientModel();
        BeanUtils.copyProperties(patientDto, patientModel);
        patientModel.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.save(patientModel));
    }

    @GetMapping
    public ResponseEntity<List<PatientModel>> getAllPatients(){
        return ResponseEntity.status(HttpStatus.OK).body(patientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOnePatient(@PathVariable(value = "id") UUID id){
        Optional<PatientModel> patientModelOptional = patientService.findById(id);
        if(!patientModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(patientModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePatient(@PathVariable(value = "id") UUID id){
        Optional<PatientModel> patientModelOptional = patientService.findById(id);
        if(!patientModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not Found.");
        }
        patientService.delete(patientModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Patient Deleted sucessfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePatient(@PathVariable(value = "id") UUID id, @RequestBody @Valid PatientDto patientDto){
        Optional<PatientModel> patientModelOptional = patientService.findById(id);
        if(!patientModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found.");
        }
        PatientModel patientModel = new PatientModel();
        BeanUtils.copyProperties(patientDto,patientModel);
        patientModel.setId(patientModelOptional.get().getId());
        patientModel.setCreatedAt(patientModelOptional.get().getCreatedAt());
        return ResponseEntity.status(HttpStatus.OK).body(patientService.save(patientModel));
    }


}

