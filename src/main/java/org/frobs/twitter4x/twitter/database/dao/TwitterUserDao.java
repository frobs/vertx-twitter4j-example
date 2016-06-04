package org.frobs.twitter4x.twitter.database.dao;



import org.frobs.twitter4x.twitter.database.TwitterConnection;
import org.frobs.twitter4x.twitter.database.models.TwitterUserModel;
import org.frobs.twitter4x.twitter.database.statements.TwitterUserStatements;
import org.frobs.twitter4x.twitter.types.TwitterCredentials;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TwitterUserDao {

    public static TwitterUserModel getByName(final String name) {
        return getByName(TwitterConnection.get(), name);
    }

    public static List<TwitterUserModel> getAll() {
        return getAll(TwitterConnection.get());
    }

    public static TwitterUserModel getByName(final Connection connection, final String name) {
        TwitterUserModel twitterUser = null;
        final PreparedStatement statement = TwitterUserStatements.getByNameStatement(connection, name);
        final ResultSet resultSet = getResultSet(connection, statement);
        try {
            while (resultSet.next()) {
                twitterUser = buildUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            TwitterConnection.close(connection,statement);
        }
        return (twitterUser != null) ? twitterUser : new TwitterUserModel();
    }

    public static List<TwitterUserModel> getAll(final Connection connection) {
        final List<TwitterUserModel> twitterUserList = new ArrayList<TwitterUserModel>();
        final PreparedStatement statement = TwitterUserStatements.getAllStatement(connection);
        final ResultSet resultSet = getResultSet(connection, statement);
        try {
            while (resultSet.next()) {
                twitterUserList.add(buildUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            TwitterConnection.close(connection,statement);
        }
        return twitterUserList;
    }

    public static boolean saveUser(final Connection connection, final TwitterUserModel user) {
        final PreparedStatement statement;
        if(user.getId() > 0){
            statement = TwitterUserStatements.updateUser(connection, user);
        }else{
            statement = TwitterUserStatements.createUser(connection, user);
        }

        return (Boolean) TwitterConnection.executeStatement(connection, (_connection) -> {
            int result = 0;
            try {
                result = statement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                TwitterConnection.close(connection, statement);
            }
            return result != 0;
        });
    }

    private static ResultSet getResultSet(final Connection connection, final PreparedStatement statement){
        return (ResultSet) TwitterConnection.executeStatement(connection, (_connection) -> {
            ResultSet result = null;
            try {
                result = statement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return result;
        });
    }

    private static TwitterUserModel buildUserFromResultSet(final ResultSet resultSet){
        final TwitterUserModel twitterUser = new TwitterUserModel();
        try {
            twitterUser.setId(resultSet.getInt("id"));
            twitterUser.setName(resultSet.getString("name"));
            twitterUser.setCredentials(new TwitterCredentials());
            twitterUser.getCredentials().setKey(resultSet.getString("key"));
            twitterUser.getCredentials().setSecret(resultSet.getString("secret"));
            twitterUser.getCredentials().setVerifier(resultSet.getString("verifier"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return twitterUser;
    }

    public static int userExist(String name){
        return TwitterUserDao.getByName(name).getId();
    }

}