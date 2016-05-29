package org.frobs.twitter4x.twitter.database.dao;

import org.frobs.twitter4x.twitter.database.TwitterTestDatabase;
import org.frobs.twitter4x.twitter.database.models.TwitterUserModel;
import org.frobs.twitter4x.twitter.types.TwitterCredentials;

import org.junit.Test;

import static junit.framework.TestCase.*;

public class TwitterUserDaoTest {

    @Test
    public void updateUser() throws Exception {
        TwitterCredentials credentials = new TwitterCredentials();
        credentials.setKey("key");
        credentials.setSecret("secret");
        credentials.setVerifier("verifier");

        TwitterUserModel model = new TwitterUserModel();
        model.setName("test_name");
        model.setCredentials(credentials);

        boolean result = TwitterUserDao.updateUser(TwitterTestDatabase.get(), model);
        assertTrue(result);
    }

}