package agh.przemek.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * Created by zaratustra on 29/06/17.
 */

@Getter
@Setter
@NoArgsConstructor
public class Credentials {

    private String username;
    private String password;


}
