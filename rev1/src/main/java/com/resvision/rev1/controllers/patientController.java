package com.resvision.rev1.controllers;

import com.resvision.rev1.models.Patient;
import com.resvision.rev1.repositories.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("API/patients")

public class patientController {
    @Autowired
    private PatientRepo patientRepo ;
    @GetMapping
    public ResponseEntity<List<Patient>> getPatients() {
        List<Patient> patients = patientRepo.findAll();
        if(patients.isEmpty()){
            return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(patients, HttpStatus.OK);
        }


    }
    @GetMapping("/{id}")
    public ResponseEntity<Patient>GetPatient(@PathVariable Long id) {
        Optional<Patient> patient=patientRepo.findById(id);
        if (patient.isPresent()) {
            return new ResponseEntity<>(patient.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/add")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        try {
            Patient savedPatient = patientRepo.save(patient);
            return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient updatedPatient) {
        return patientRepo.findById(id)
                .map(existingPatient -> {
                    existingPatient.setName(updatedPatient.getName());
                    existingPatient.setAge(updatedPatient.getAge());
                    existingPatient.setGender(updatedPatient.getGender());
                    existingPatient.setAddress(updatedPatient.getAddress());
                    existingPatient.setPhone(updatedPatient.getPhone());
                    existingPatient.setEmail(updatedPatient.getEmail());
                    Patient savedPatient = patientRepo.save(existingPatient);
                    return ResponseEntity.ok(savedPatient);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable("id") Long id) {
        try {
            if (patientRepo.existsById(id)) {
                patientRepo.deleteById(id);
                return new ResponseEntity<>("Patient deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Patient not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred while deleting the patient", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
