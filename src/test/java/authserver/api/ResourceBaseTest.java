package authserver.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class ResourceBaseTest {

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected EntityManager entityManager;
    @Autowired
    protected WebClient webClient;

    protected List<Object> persistedData = new ArrayList<>();

    protected String asJsonString(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    protected <T> T fromJsonString(String json, Class<T> valueType) throws JsonProcessingException {
        return objectMapper.readValue(json, valueType);
    }

    protected <T> T fromJsonString(String json, TypeReference<T> type) throws JsonProcessingException {
        return objectMapper.readValue(json, type);
    }

    protected void persist(Object object) {
        entityManager.persist(object);
        persistedData.add(object);
        entityManager.flush();
    }

    protected void removeAllPersisted() {
        persistedData.forEach(entityManager::remove);
        persistedData.clear();
        entityManager.flush();
        entityManager.clear();
    }

    protected  <P extends Page> P signUp(HtmlPage page, String username, String password) throws IOException {
        HtmlInput usernameInput = page.querySelector("input[name=\"username\"]");
        HtmlInput passwordInput = page.querySelector("input[name=\"password\"]");
        HtmlInput signUpButton = page.querySelector("input[class=\"button\"]");

        usernameInput.type(username);
        passwordInput.type(password);

        return signUpButton.click();
    }

}
