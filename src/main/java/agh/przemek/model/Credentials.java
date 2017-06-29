package agh.przemek.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by zaratustra on 29/06/17.
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Credentials {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
    private String username;
	
	@Column(nullable = false)
    private String password;

	public Credentials(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Credentials) {
            Credentials cr = (Credentials) obj;

            return Objects.equals(this.username, cr.username);
        }

        return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(username);
	}
}
