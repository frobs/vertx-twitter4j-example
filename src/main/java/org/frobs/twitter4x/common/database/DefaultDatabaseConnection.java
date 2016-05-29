package org.frobs.twitter4x.common.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DefaultDatabaseConnection {

    static{
        try {
            Class.forName(DatabaseConfiguration.JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection get(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DatabaseConfiguration.DB_URL, DatabaseConfiguration.DB_USER, DatabaseConfiguration.DB_PASSWD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static Connection get(DatabaseConfiguration configuration){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(configuration.DB_URL, configuration.DB_USER, configuration.DB_PASSWD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void close(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(PreparedStatement statement){
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Connection connection, PreparedStatement statement){
        if(statement != null){
            close(statement);
        }
        if(connection != null){
                close(connection);
        }
    }

    public static Object executeStatement(Connection connection, PreparedStatementWrapper statementWrapper){
        return  statementWrapper.execute(connection);
    }
}
