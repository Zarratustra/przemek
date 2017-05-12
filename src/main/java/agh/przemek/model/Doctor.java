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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Specialization> specializations;

	public Doctor(String firstName, String lastName, Set<Specialization> specializations) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.specializations = specializations;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (obj instanceof Doctor) {
            Doctor t = (Doctor) obj;

            return Objects.equals(firstName, t.firstName) &&
                    Objects.equals(lastName, t.lastName);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName,lastName);
    }
}
