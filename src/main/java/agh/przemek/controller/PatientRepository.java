package agh.przemek.controller;

import org.springframework.data.repository.CrudRepository;

import agh.przemek.model.Patient;

public interface PatientRepository extends CrudRepository<Patient, Long> {

}
