package agh.przemek.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by zaratustra on 29/06/17.
 */

@Getter
@Setter
@NoArgsConstructor
public class User {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
