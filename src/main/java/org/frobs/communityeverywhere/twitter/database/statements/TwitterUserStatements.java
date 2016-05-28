package org.frobs.communityeverywhere.twitter.database.statements;

import org.frobs.communityeverywhere.twitter.database.models.TwitterUserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class TwitterUserStatements {

    public static PreparedStatement getByNameStatement(final Connection connection, final String name){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("select * from twitter where name = ?");
            statement.setString(1, name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    public static PreparedStatement getAllStatement(final Connection connection){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("select * from twitter");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    public static PreparedStatement createUser(final Connection connection, TwitterUserModel user){
        String insertUserQuery = "INSERT INTO `twitter` "
                + "(`name`, `key`, `secret`, `verifier`) VALUES"
                + "(?,?,?,?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insertUserQuery);
            statement.setString(1, user.getName());
            statement.setString(2, user.getCredentials().getKey());
            statement.setString(3, user.getCredentials().getSecret());
            statement.setString(4, user.getCredentials().getVerifier());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    public static PreparedStatement updateUser(final Connection connection, TwitterUserModel user){
        String insertUserQuery = "UPDATE twitter "
                + "SET `name`= ?, `key` = ?, `secret` = ?, `verifier` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insertUserQuery);
            statement.setString(1, user.getName());
            statement.setString(2, user.getCredentials().getKey());
            statement.setString(3, user.getCredentials().getSecret());
            statement.setString(4, user.getCredentials().getVerifier());
            statement.setInt(5, user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

}
