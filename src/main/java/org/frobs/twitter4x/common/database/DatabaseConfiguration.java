package org.frobs.twitter4x.common.database;


public interface DatabaseConfiguration {
    public static final String JDBC_TIMEZONE = "Europe/Madrid";
    public static final String JDBC_LEGACY_DATETIME = "false";
    public static final String JDBC_USESSL = "false";
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost/twitter4x?useLegacyDatetimeCode="+JDBC_LEGACY_DATETIME+"&serverTimezone=" + JDBC_TIMEZONE + "&useSSL=" + JDBC_USESSL;
    public static final String DB_USER = System.getenv("COMMUNITY_DB_USER");
    public static final String DB_PASSWD = System.getenv("COMMUNITY_DB_PASSWD");
}
