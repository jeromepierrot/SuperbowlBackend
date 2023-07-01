package fr.jpierrot.superbowlbackend.pojo.entities;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.jpierrot.superbowlbackend.mockup.MockMvcTest;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.ZonedDateTime;

public class BetTest extends MockMvcTest {

    @Override
    @Before
    public void init() {
        super.init();
    }

    @Test
    public void givenBidirectionRelation_whenSerializing_thenException() throws JsonProcessingException {
        final User user = User.builder()
                .lastname("Doe")
                .firstname("John")
                .build();
        final Bet bet = Bet.builder()
                .wager(100.0f)
                .finalOdds(1.5f)
                .match(null)
                .build();

        user.addBet(bet);

        String betJson = super.mapToJson(user);
        String userJson = super.mapToJson(user);
    }

    @Test
    public void givenBidirectionRelation_whenDeserializingUsingIdentity_thenCorrect() throws JsonProcessingException, IOException {
        final String json = "{\"id\":2,\"wager\":100.0,\"users\":[{\"id\":1,\"lastname\":\"Doe\",\"firstname\":\"John\"}]}";

//        Bet bet = super.mapFromJson(json, Bet.class);

        final Bet bet = new ObjectMapper().readerFor(Bet.class)
                .readValue(json);

        assertEquals(2L, (long) bet.getId());
        assertEquals(100.0f, bet.getWager(), 0.1);
        assertEquals("John", bet.getUsers().get(0).getFirstname());
    }

    @Test
    public void givenUserBetBidirectionRelation_whenUsingJsonIdentityInfo_thenCorrect() throws IOException {
        final User user = User.builder()
                .lastname("Doe")
                .firstname("John")
                .build();
        final Bet bet = Bet.builder()
                .wager(100.0f)
                .finalOdds(1.5f)
                .match(null)
                .build();

        user.addBet(bet);

        String betJson = super.mapToJson(bet);
        String userJson = super.mapToJson(user);

        assertThat(betJson, containsString("100.0"));
        assertThat(betJson, not(containsString("John")));

        assertThat(userJson, containsString("Doe"));
        assertThat(userJson, containsString("John"));
        assertThat(userJson, containsString("100.0"));
    }

    @Test
    public void givenUserBetMatch_whenAddingBetToUser_andMatchToBet_thenMatchIsAssociateToUser()
            throws IOException, JsonProcessingException {

        final User user = User.builder()
                .id(1L)
                .email("john@doe.fr")
                .password("1234")
                .lastname("Doe")
                .firstname("John")
                .build();
        final Bet bet = Bet.builder()
                .id(1L)
                .wager(100.0f)
                .finalOdds(1.5f)
                .match(null)
                .build();
        final Match match = Match.builder()
                .id(1L)
                .oddsA(1.2f)
                .oddsB(2.3f)
                .build();

        assertTrue(user.addBet(bet));
        assertTrue(bet.addMatch(match));

        String userJson = super.mapToJson(user);
        String betJson = super.mapToJson(bet);
        String matchJson = super.mapToJson(match);

        assertThat(matchJson, not(containsString("100.0")));
        assertThat(matchJson, not(containsString("John")));

        assertThat(betJson, containsString("100.0"));
        assertThat(betJson, containsString("1.2"));
        assertThat(betJson, not(containsString("John")));

        assertThat(userJson, containsString("Doe"));
        assertThat(userJson, containsString("John"));
        assertThat(userJson, containsString("100.0"));
        assertThat(userJson, containsString("1.2"));
        assertThat(userJson, containsString("2.3"));
    }

    @Test
    public void givenBetAlreadyAttachedToUser1_whenAddingBetToUser2OrToUser1_thenFalse()
            throws IOException, JsonProcessingException {

        final User user1 = User.builder()
                .id(1L)
                .email("john@doe.fr")
                .password("1234")
                .lastname("Doe")
                .firstname("John")
                .build();
        final User user2 = User.builder()
                .id(2L)
                .email("amelie@free.fr")
                .password("ametlitmetsleau")
                .lastname("Mello")
                .firstname("Amelie")
                .build();
        final Bet bet = Bet.builder()
                .id(1L)
                .wager(100.0f)
                .finalOdds(1.5f)
                .match(null)
                .build();


        assertTrue(user1.addBet(bet)); // bet is attached to User 1
        assertFalse(user2.addBet(bet)); // bet cannot be attached to User 2
        assertFalse(user1.addBet(bet)); // bet cannot be attached again to User 1
    }

    @Test
    public void givenMatch1AlreadyAttachedToBet_whenAddingMatch2ToBet_thenFalse()
            throws IOException, JsonProcessingException {

        final Match match1 = Match.builder()
                .id(1L)
                .oddsA(1.2f)
                .oddsB(2.3f)
                .startDate(ZonedDateTime.now())
                .build();
        final Match match2 = Match.builder()
                .id(2L)
                .oddsA(2.8f)
                .oddsB(1.1f)
                .build();
        final Bet bet = Bet.builder()
                .id(1L)
                .wager(100.0f)
                .finalOdds(1.5f)
                .match(match1)
                .build();

        assertEquals(match1, bet.getMatch());
        assertFalse(bet.addMatch(match2));
    }
}
