package authserver.api.users.dto;

import lombok.Data;

@Data
public class RegisterUserRequest {

    private String username;
    private String password;

}
