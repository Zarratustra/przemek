package agh.przemek.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    // User ID from the authentication provider
    @Column(nullable = false)
    private String uid;

    public Patient(String firstName, String lastName, String uid) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.uid = uid;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Patient) {
            Patient t = (Patient) obj;

            return Objects.equals(firstName, t.firstName) &&
                    Objects.equals(lastName, t.lastName) &&
                    Objects.equals(uid, t.uid);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName,lastName,uid);
    }
}
