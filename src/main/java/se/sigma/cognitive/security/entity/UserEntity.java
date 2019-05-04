package se.sigma.cognitive.security.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;


@Getter
@Setter
@Entity(name = "users")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = -2892605918932587188L;


    @Id
    @GeneratedValue
    private long id;


    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, length = 50)
    private String firstName;
    @Column(nullable = false, length = 50)
    private String lastName;
    @Column(nullable = false, length = 150, unique = true)
    private String email;
    @Column(nullable = false)
    private String encryptedPassword;

    private String emailVerificationToken;
    @Column(nullable = true , columnDefinition = "boolean default false")
    private Boolean emailVerificationStatus;
}
