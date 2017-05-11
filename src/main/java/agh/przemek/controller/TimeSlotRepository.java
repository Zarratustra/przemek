package agh.przemek.controller;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import agh.przemek.model.Doctor;
import agh.przemek.model.TimeSlot;

public interface TimeSlotRepository extends CrudRepository<TimeSlot, Long> {

	List<TimeSlot> findByDoctor(Doctor doctor);

	@Query("select t from TimeSlot t join t.doctor where t.takenBy is null and t.doctor = :doctor")
	List<TimeSlot> findAvailableByDoctor(@Param("doctor") Doctor doctor);

	@Query("select t from TimeSlot t join t.doctor where t.takenBy is not null and t.doctor = :doctor")
	List<TimeSlot> findTakenByDoctor(@Param("doctor") Doctor doctor);
}
