package agh.przemek.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_")
@Getter
@Setter
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false)
    private String firstName;
	
	@Column(nullable = false)
    private String lastName;
	
	@Column(nullable = false, unique = true)
    private String username;
	
	@Column(nullable = false)
    private String password;

	public User(String firstName, String lastName, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User) {
            User cr = (User) obj;

            return Objects.equals(this.username, cr.username);
        }

        return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(username);
	}
	
	public Credentials getCredentials() {
		return new Credentials(getUsername(), getPassword());
	}
}
