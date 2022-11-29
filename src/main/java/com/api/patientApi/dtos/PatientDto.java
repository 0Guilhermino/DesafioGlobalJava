package com.api.patientApi.dtos;

import javax.validation.constraints.NotBlank;

public class PatientDto {

    @NotBlank
    private String patientName;
    @NotBlank
    private String address;
    @NotBlank
    private String healthInsuranceCardId;

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
}
