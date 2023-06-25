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

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
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
    public void getUserByIdTest() throws Exception {
        String path = "/api/users/2";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .get(path)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        User returnedUser = super.mapFromJson(content, User.class);
        assertEquals(2L, (long) returnedUser.getId());
    }

    @Test
    public void createUserTest() throws Exception {
        String path = "/api/users";
        User userToCreate = new User();
        userToCreate.setId(6L);
        userToCreate.setName("Doe");
        userToCreate.setFirstname("John");
        userToCreate.setEmail("john.doe@doedoedoe.fr");
        userToCreate.setPassword("1234");
        userToCreate.setIsEnabled(true);
        userToCreate.setIsPwdChecked(true);

        String inputJson = super.mapToJson(userToCreate);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post(path)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);

        Map<String, String> expectedContent = new HashMap<>();
        expectedContent.put("body", "Data is created successfully");

        String expectedContentJson = super.mapToJson(expectedContent);

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(expectedContentJson, content);

    }

    @Test
    public void updateUserByIdTest() throws Exception {
        String path = "/api/users/6";
        User userToUpdate = new User();
        userToUpdate.setName("Dae");
        userToUpdate.setFirstname("Jane");

        String inputJson = super.mapToJson(userToUpdate);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .put(path)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);

        Map<String, String> expectedContent = new HashMap<>();
        expectedContent.put("body", "Data is updated successfully");

        String expectedContentJson = super.mapToJson(expectedContent);

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(expectedContentJson, content);
    }

    @Test
    public void deleteUserByIdTest() throws Exception {
        String path = "/api/users/9";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .delete(path)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        Map<String, String> expectedContent = new HashMap<>();
        expectedContent.put("body", "Data is deleted successfully");

        String expectedContentJson = super.mapToJson(expectedContent);

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(expectedContentJson, content);
    }
}
