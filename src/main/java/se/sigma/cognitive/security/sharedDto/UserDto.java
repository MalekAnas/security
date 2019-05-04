package se.sigma.cognitive.security.sharedDto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 *
 */

@Getter
@Setter
public class UserDto implements Serializable {


    //to check an object's state between before and after its serialization

    private static final long serialVersionUID = -5392435647046303882L;


    private long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String encryptedPassword;
    private String emailVerificationToken;
    private Boolean emailVerificationStatus = false;




}