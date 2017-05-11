package agh.przemek.controller;

import java.util.Collection;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import agh.przemek.model.Doctor;
import agh.przemek.model.Specialization;
import agh.przemek.model.TimeSlot;

@RestController
public class PrzemekRestController {

	@RequestMapping(method = RequestMethod.GET, path = "/specializations")
	public Collection<Specialization> getSpecializations() {
		// TODO
		throw new UnsupportedOperationException();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/specializations/{id}")
	public Specialization getSpecialization(@PathVariable(name = "id") long id) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/specializations/{specId}/doctors")
	public Specialization getDoctorsBySpecializaion(@PathVariable(name = "specId") long specId) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/doctors")
	public Collection<Doctor> getDoctors() {
		// TODO
		throw new UnsupportedOperationException();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/doctors/{id}")
	public Doctor getDoctor(@PathVariable(name = "id") long id) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/doctors/{docId}/slots")
	public Collection<TimeSlot> getTimeSlots(@PathVariable(name = "docId") long docId,
			@RequestParam(name = "type") String slotType) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/doctors/{docId}/slots/{slotId}")
	public TimeSlot getTimeSlot(@PathVariable(name = "docId") long docId, @PathVariable(name = "slotId") long slotId) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/doctors/{docId}/slots/{slotId}/taken_by")
	public void makeAppointment(@PathVariable(name = "docId") long docId, @PathVariable(name = "slotId") long slotId,
			@RequestBody long patientId) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/doctors/{docId}/slots/{slotId}/taken_by")
	public void cancelAppointment(@PathVariable(name = "docId") long docId,
			@PathVariable(name = "slotId") long slotId) {
		// TODO
		throw new UnsupportedOperationException();
	}
}
