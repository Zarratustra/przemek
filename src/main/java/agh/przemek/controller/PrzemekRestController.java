package agh.przemek.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import agh.przemek.model.Doctor;
import agh.przemek.model.Patient;
import agh.przemek.model.Specialization;
import agh.przemek.model.TimeSlot;

@RestController
public class PrzemekRestController {

	@Autowired
	private SpecializationRepository specializationRepository;
	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private TimeSlotRepository timeSlotRepository;
	@Autowired
	private PatientRepository patientRepository;

	@RequestMapping(method = RequestMethod.GET, path = "rest/specializations")
	public Collection<Specialization> getSpecializations() {
		List<Specialization> list = new ArrayList<>();

		specializationRepository.findAll().forEach(list::add);

		return list;
	}

	@RequestMapping(method = RequestMethod.GET, path = "rest/specializations/{id}")
	public Specialization getSpecialization(@PathVariable(name = "id") long id) {
		Specialization spec = specializationRepository.findOne(id);
		if (spec == null) {
			throw new ResourceNotFoundException("No specialization with id: " + id);
		}

		return spec;
	}

	@RequestMapping(method = RequestMethod.GET, path = "rest/specializations/{specId}/doctors")
	public Collection<Doctor> getDoctorsBySpecializaion(@PathVariable(name = "specId") long specId) {
		Specialization spec = specializationRepository.findOne(specId);
		if (spec == null) {
			throw new ResourceNotFoundException("No specialization with id: " + specId);
		}

		return doctorRepository.findBySpecializations(spec);
	}

	@RequestMapping(method = RequestMethod.GET, path = "rest/doctors")
	public Collection<Doctor> getDoctors() {
		List<Doctor> list = new ArrayList<>();

		doctorRepository.findAll().forEach(list::add);

		return list;
	}

	@RequestMapping(method = RequestMethod.GET, path = "rest/doctors/{id}")
	public Doctor getDoctor(@PathVariable(name = "id") long id) {
		Doctor doc = doctorRepository.findOne(id);
		if (doc == null) {
			throw new ResourceNotFoundException("No doctor with id: " + id);
		}

		return doc;
	}

	@RequestMapping(method = RequestMethod.GET, path = "rest/doctors/{docId}/slots")
	public Collection<TimeSlot> getTimeSlots(@PathVariable(name = "docId") long docId,
			@RequestParam(name = "type", defaultValue = "all") String slotType) {
		Doctor doc = doctorRepository.findOne(docId);
		if (doc == null) {
			throw new ResourceNotFoundException("No doctor with id: " + docId);
		}

		List<TimeSlot> slots;
		if (slotType.equals("available")) {
			slots = timeSlotRepository.findAvailableByDoctor(doc);
		} else if (slotType.equals("taken")) {
			slots = timeSlotRepository.findTakenByDoctor(doc);
		} else {
			slots = timeSlotRepository.findByDoctor(doc);
		}

		return slots;
	}

	@RequestMapping(method = RequestMethod.GET, path = "rest/doctors/{docId}/slots/{slotId}")
	public TimeSlot getTimeSlot(@PathVariable(name = "docId") long docId, @PathVariable(name = "slotId") long slotId) {
		Doctor doc = doctorRepository.findOne(docId);
		if (doc == null) {
			throw new ResourceNotFoundException("No doctor with id: " + docId);
		}

		TimeSlot slot = timeSlotRepository.findOne(slotId);
		if (slot == null) {
			throw new ResourceNotFoundException("No time slot with id: " + slotId);
		}

		return slot;
	}

	@RequestMapping(method = RequestMethod.PUT, path = "rest/doctors/{docId}/slots/{slotId}/taken_by")
	public void makeAppointment(@PathVariable(name = "docId") long docId, @PathVariable(name = "slotId") long slotId,
			@RequestBody long patientId) {
		Doctor doc = doctorRepository.findOne(docId);
		if (doc == null) {
			throw new ResourceNotFoundException("No doctor with id: " + docId);
		}

		TimeSlot slot = timeSlotRepository.findOne(slotId);
		if (slot == null) {
			throw new ResourceNotFoundException("No time slot with id: " + slotId);
		}

		Patient patient = patientRepository.findOne(patientId);
		if (patient == null) {
			throw new BadRequestException("No patient with id: " + patientId);
		}

		slot.setTakenBy(patient);
		timeSlotRepository.save(slot);
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "rest/doctors/{docId}/slots/{slotId}/taken_by")
	public void cancelAppointment(@PathVariable(name = "docId") long docId,
			@PathVariable(name = "slotId") long slotId) {
		Doctor doc = doctorRepository.findOne(docId);
		if (doc == null) {
			throw new ResourceNotFoundException("No doctor with id: " + docId);
		}

		TimeSlot slot = timeSlotRepository.findOne(slotId);
		if (slot == null) {
			throw new ResourceNotFoundException("No time slot with id: " + slotId);
		}

		slot.setTakenBy(null);
		timeSlotRepository.save(slot);
	}
}
