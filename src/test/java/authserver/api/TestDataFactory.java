package authserver.api;


import authserver.api.users.dto.RegisterUserRequest;

public class TestDataFactory {

    public static RegisterUserRequest aRegisterUserRequest(String username, String password) {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername(username);
        registerUserRequest.setPassword(password);

        return registerUserRequest;
    }


}
