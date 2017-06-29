package agh.przemek.model;

import java.util.Base64;
import java.util.Objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Credentials {

    private String username;
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
	
	public static Credentials fromBase64(String base64) {
		Base64.Decoder decoder = Base64.getDecoder();
		String[] creds = new String(decoder.decode(base64)).split(":");
		if (creds.length != 2) {
			throw new IllegalArgumentException("Malformed credentials");
		}
		
		return new Credentials(creds[0], creds[1]);
	}

	@Override
	public String toString() {
		return getUsername() + ":" + getPassword();
	}
}
