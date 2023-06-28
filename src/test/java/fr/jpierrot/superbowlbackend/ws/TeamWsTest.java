package fr.jpierrot.superbowlbackend.ws;

import fr.jpierrot.superbowlbackend.mockup.MockMvcTest;
import fr.jpierrot.superbowlbackend.pojo.entities.Team;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TeamWsTest extends MockMvcTest {
    @Override
    @Before
    public void init() {
        super.init();
    }

    @Test
    public void getAllTeamsTest() throws Exception {
        String path = "/api/teams";

        MvcResult mvcResult =mvc.perform(MockMvcRequestBuilders
                        .get(path)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Team[] returnedTeamList = super.mapFromJson(content, Team[].class);
        assertTrue(returnedTeamList.length > 0);
    }

    @Test
    public void getTeamByIdTest() throws Exception {
        String path = "/api/teams/?id=2";

        MvcResult mvcResult =mvc.perform(MockMvcRequestBuilders
                        .get(path)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Team returnedTeam = super.mapFromJson(content, Team.class);
        assertEquals(2L, (long) returnedTeam.getId());
    }

    @Test
    public void getTeamByNameTest() throws Exception {
        String path = "/api/teams/name/team";

        MvcResult mvcResult =mvc.perform(MockMvcRequestBuilders
                        .get(path)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Team[] returnedTeam = super.mapFromJson(content, Team[].class);
        assertEquals(9L, (long) returnedTeam[0].getId());
    }

    @Test
    public void getTeamByCountryIdTest() throws Exception {
        String path = "/api/teams/country/id/2";

        MvcResult mvcResult =mvc.perform(MockMvcRequestBuilders
                        .get(path)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Team[] returnedTeam = super.mapFromJson(content, Team[].class);
        assertEquals(2L, (long) returnedTeam[0].getId());
    }

    @Test
    public void getTeamByCountryNameTest() throws Exception {
        String path = "/api/teams/country/test";

        MvcResult mvcResult =mvc.perform(MockMvcRequestBuilders
                        .get(path)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Team[] returnedTeam = super.mapFromJson(content, Team[].class);
        assertEquals(9L, (long) returnedTeam[0].getId());
    }

    @Test
    public void createTeamTest() throws Exception {
        String path = "/api/teams";
        Team teamToCreate = new Team();
        teamToCreate.setName("Nouvelle équipe test");
        String inputJson = super.mapToJson(teamToCreate);
        MvcResult mvcResult =mvc.perform(MockMvcRequestBuilders
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
    public void updateTeamByIdTest() throws Exception {
        String path = "/api/teams/10";
        Team teamToUpdate = new Team();
        teamToUpdate.setName("Updated team");
        String inputJson = super.mapToJson(teamToUpdate);

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
    public void updateTeamByNameTest() throws Exception {
        String path = "/api/teams/name/999";
        Team teamToUpdate = new Team();
        teamToUpdate.setName("Updated team");
        String inputJson = super.mapToJson(teamToUpdate);

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
}
