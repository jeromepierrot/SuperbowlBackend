package fr.jpierrot.SuperbowlBackend.ws;

import fr.jpierrot.SuperbowlBackend.mockup.MockMvcTest;
import fr.jpierrot.SuperbowlBackend.pojo.entities.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserWsTest extends MockMvcTest {

    @Override
    @Before
    public void init() {
        super.init();
    }

    @Test
    public void getAllUsersTest() throws Exception {
        String path = "/api/users";

        MvcResult mvcResult =mvc.perform(MockMvcRequestBuilders
                .get(path)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        User[] returnedUserList = super.mapFromJson(content, User[].class);
        assertTrue(returnedUserList.length > 0);
    }

    @Test
    public void createUserTest() throws Exception {
        String path = "/api/users";
        User user = new User();
        user.setName("John");
        user.setFirstname("Doe");
        user.setEmail("john.doe@test.fr");
        user.setPassword("1234");

        String inputJson = super.mapToJson(user);
        MvcResult mvcResult =mvc.perform(MockMvcRequestBuilders
                .post(path)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);

        Map<String, String> expectedContent = new HashMap<>();
        expectedContent.put("body", "User is created successfully");

        String expectedContentJson = super.mapToJson(expectedContent);

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(expectedContentJson, content);
    }
}
