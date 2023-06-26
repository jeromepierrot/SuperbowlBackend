package fr.jpierrot.superbowlbackend.pojo.compositeKey;

import fr.jpierrot.superbowlbackend.pojo.entities.UsersBets;
import fr.jpierrot.superbowlbackend.pojo.entities.compositeKey.UsersBetsKey;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class UsersBetsKeyTest {
    @Test
    public void whenSaveCompositeKey_thenOk() {
        UsersBetsKey compositeKey = new UsersBetsKey();
        compositeKey.setUserId(1L);
        compositeKey.setBetId(25L);

        UsersBets usersBetsEntry = new UsersBets();
        usersBetsEntry.setId(compositeKey);
/*        session.save(usersBetsEntry);*/

        assertEquals(1L, (long) usersBetsEntry.getId().getUserId());
        assertEquals(25L, (long) usersBetsEntry.getId().getBetId());
}}
