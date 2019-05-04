package se.sigma.cognitive.security.requestModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLogInRequestModel {


    private String email;
    private String password;
}
