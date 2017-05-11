package agh.przemek.model;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Specialization {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false)
	private String name;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Doctor> doctors;

	public Specialization(String name, Set<Doctor> doctors) {
		this.name = name;
		this.doctors = doctors;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Specialization) {
			Specialization s = (Specialization) obj;
			
			return Objects.equals(name, s.name);
		}
		
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
