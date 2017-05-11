package agh.przemek;

import java.time.LocalDateTime;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import agh.przemek.controller.DoctorRepository;
import agh.przemek.controller.PatientRepository;
import agh.przemek.controller.SpecializationRepository;
import agh.przemek.controller.TimeSlotRepository;
import agh.przemek.model.Doctor;
import agh.przemek.model.Patient;
import agh.przemek.model.Specialization;
import agh.przemek.model.TimeSlot;

@SpringBootApplication
public class PrzemekApplication {

	public static void main(String[] args) {
		System.out.println("Siemka");
		SpringApplication.run(PrzemekApplication.class, args);
	}

	@Bean
	public ApplicationRunner initializer() {
		return new ApplicationRunner() {

			@Autowired
			private DoctorRepository doctorRepository;
			@Autowired
			private PatientRepository patientRepository;
			@Autowired
			private SpecializationRepository specializationRepository;
			@Autowired
			private TimeSlotRepository timeSlotRepository;

			@Override
			public void run(ApplicationArguments args) throws Exception {
				Specialization spec1 = new Specialization("pediatrician");
				specializationRepository.save(spec1);

				Specialization spec2 = new Specialization("oncologist");
				specializationRepository.save(spec2);
				
				Doctor doc1 = new Doctor("Abab", "Babacki", new HashSet<>());
				doc1.getSpecializations().add(spec1);
				doctorRepository.save(doc1);

				Doctor doc2 = new Doctor("Babab", "Cadacki", new HashSet<>());
				doc2.getSpecializations().add(spec2);
				doctorRepository.save(doc2);

				timeSlotRepository
						.save(new TimeSlot(LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), doc1, null));
				timeSlotRepository.save(new TimeSlot(LocalDateTime.now().plusDays(1),
						LocalDateTime.now().plusDays(1).plusMinutes(15), doc1, null));
				timeSlotRepository.save(new TimeSlot(LocalDateTime.now().plusDays(2),
						LocalDateTime.now().plusDays(2).plusMinutes(15), doc2, null));

				patientRepository.save(new Patient("Test", "Subject", "xxx"));
			}
		};
	}
}
