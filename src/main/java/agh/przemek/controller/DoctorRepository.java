package agh.przemek.controller;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import agh.przemek.model.Doctor;
import agh.przemek.model.Specialization;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {

	List<Doctor> findBySpecializations(Specialization specialization);
}
