package org.frobs.twitter4x.common.database;

import java.sql.Connection;


@FunctionalInterface
public interface PreparedStatementWrapper {
    public Object execute(Connection connection);
}
