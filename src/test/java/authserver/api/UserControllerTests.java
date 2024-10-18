package authserver.api;

//import authserver.api.users.dto.RegisterUserRequest;
//import authserver.entity.SecurityUser;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTests extends ResourceBaseTest{

    public static final String TEST_USERNAME_NEW_USER = "User1";

    public static final String TEST_USERNAME_EXISTING_USER = "User";

    public static final String TEST_PASSWORD = "password";

    public static final String REGISTER_PATH = "/register";

    @BeforeEach
    public void setUp() {
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(true);
        webClient.getOptions().setRedirectEnabled(true);
        webClient.getCookieManager().clearCookies();
    }

    @Test
    void canSignUpNewUser() throws Exception {
        HtmlPage page = this.webClient.getPage(REGISTER_PATH);

        this.webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        this.webClient.getOptions().setRedirectEnabled(false);
        signUp(page, TEST_USERNAME_NEW_USER, TEST_PASSWORD).getWebResponse();

//        assertThat(userRepository.findByUsername(TEST_USERNAME_NEW_USER)).isNotNull();
    }

    @Test
    void canNotSignUpExistingUser() throws Exception {

        HtmlPage page = this.webClient.getPage(REGISTER_PATH);

        this.webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        this.webClient.getOptions().setRedirectEnabled(false);
        signUp(page, TEST_USERNAME_EXISTING_USER, TEST_PASSWORD).getWebResponse();

//        assertThat(userRepository.findByUsername(TEST_USERNAME_EXISTING_USER).size()).isEqualTo(1);
    }


}
