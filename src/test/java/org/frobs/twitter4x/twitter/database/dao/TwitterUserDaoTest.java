package org.frobs.twitter4x.twitter.database.dao;

import org.frobs.twitter4x.twitter.database.TwitterConnection;
import org.frobs.twitter4x.twitter.database.TwitterTestDatabase;
import org.frobs.twitter4x.twitter.database.models.TwitterUserModel;
import org.frobs.twitter4x.twitter.types.TwitterCredentials;


import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static junit.framework.TestCase.*;

public class TwitterUserDaoTest {

    @Before
    public void executedBeforeEach() {
        clean();
    }

    @Test
    public void createUser() throws Exception {
        TwitterCredentials credentials = new TwitterCredentials();
        credentials.setKey("key");
        credentials.setSecret("secret");
        credentials.setVerifier("verifier");

        TwitterUserModel model = new TwitterUserModel();
        model.setName("test_name");
        model.setCredentials(credentials);

        boolean result = TwitterUserDao.saveUser(TwitterTestDatabase.get(), model);
        assertTrue(result);
    }

    @Test
    public void updateUser() throws Exception {
        createUser();
        TwitterCredentials credentials = new TwitterCredentials();
        credentials.setKey("key");
        credentials.setSecret("secret_changed");
        credentials.setVerifier("verifier");

        TwitterUserModel model = new TwitterUserModel();
        model.setId(1);
        model.setName("test_name");
        model.setCredentials(credentials);

        boolean result = TwitterUserDao.saveUser(TwitterTestDatabase.get(), model);
        assertTrue(result);
    }

    @Test
    public void getAllUsers() throws Exception {
        createUser();
        TwitterCredentials credentials = new TwitterCredentials();
        credentials.setKey("second-key");
        credentials.setSecret("secret_changed");
        credentials.setVerifier("verifier");

        TwitterUserModel model = new TwitterUserModel();
        model.setName("test_name2");
        model.setCredentials(credentials);

        TwitterUserDao.saveUser(TwitterTestDatabase.get(), model);
        List<TwitterUserModel> result = TwitterUserDao.getAll(TwitterTestDatabase.get());
        assertEquals(2,result.size());
    }

    @Test
    public void getByName() throws Exception {
        createUser();
        TwitterUserModel user = TwitterUserDao.getByName(TwitterTestDatabase.get(), "test_name");
        assertEquals(1,user.getId());
    }

    private void clean() {
        Connection connection = TwitterTestDatabase.get();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("TRUNCATE twitter");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        TwitterConnection.close(connection,statement);
    }


}