package io.grafiksal.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long patientID;


    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private LocalDate birthDate;

    public Patient(String firstName, String lastName, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Patient) {
            Patient t = (Patient) obj;

            return Objects.equals(firstName, t.firstName) &&
                    Objects.equals(lastName, t.lastName) &&
                    Objects.equals(birthDate, t.birthDate);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName,lastName,birthDate);
    }


}
