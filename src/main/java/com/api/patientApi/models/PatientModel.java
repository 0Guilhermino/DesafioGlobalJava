package com.api.patientApi.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_PATIENT")
public class PatientModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "varbinary(36)")
    private UUID id;
    @Column(nullable = false, length = 130)
    private String patientName;
    @Column(nullable = false, length = 130)
    private String address;
    @Column(nullable = false, unique = true, length = 10)
    private String healthInsuranceCardId;
    @Column(nullable = false)
    private LocalDateTime createdAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHealthInsuranceCardId() {
        return healthInsuranceCardId;
    }

    public void setHealthInsuranceCardId(String healthInsuranceCardId) {
        this.healthInsuranceCardId = healthInsuranceCardId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
