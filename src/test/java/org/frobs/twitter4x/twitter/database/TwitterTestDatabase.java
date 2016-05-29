package org.frobs.twitter4x.twitter.database;


import org.frobs.twitter4x.common.database.DefaultDatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TwitterTestDatabase extends DefaultDatabaseConnection {
    public static Connection get(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(TestDatabaseConfiguration.DB_URL, TestDatabaseConfiguration.DB_USER, TestDatabaseConfiguration.DB_PASSWD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
