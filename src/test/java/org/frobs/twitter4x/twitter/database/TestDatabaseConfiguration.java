package org.frobs.twitter4x.twitter.database;

import org.frobs.twitter4x.common.database.DatabaseConfiguration;

public class TestDatabaseConfiguration implements DatabaseConfiguration {
    public static final String DB_URL = "jdbc:mysql://localhost/twitter4x-test?useLegacyDatetimeCode="+JDBC_LEGACY_DATETIME+"&serverTimezone=" + JDBC_TIMEZONE + "&useSSL=" + JDBC_USESSL;
    public static final String DB_USER = "root";
    public static final String DB_PASSWD = "1221";
}
