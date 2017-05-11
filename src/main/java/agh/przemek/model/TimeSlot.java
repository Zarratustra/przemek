package agh.przemek.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TimeSlot {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false)
	private LocalDateTime startTime;
	
	@Column(nullable = false)
	private LocalDateTime endTime;
	
	@ManyToOne(optional = false)
	private Doctor doctor;
	
	@ManyToOne(optional = true)
	private Patient takenBy;

	public TimeSlot(LocalDateTime startTime, LocalDateTime endTime, Doctor doctor, Patient takenBy) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.doctor = doctor;
		this.takenBy = takenBy;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TimeSlot) {
			TimeSlot t = (TimeSlot) obj;
			
			return Objects.equals(startTime, t.startTime) &&
					Objects.equals(endTime, t.endTime) &&
					Objects.equals(doctor, t.doctor);
		}
		
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(startTime, endTime, doctor);
	}
}
