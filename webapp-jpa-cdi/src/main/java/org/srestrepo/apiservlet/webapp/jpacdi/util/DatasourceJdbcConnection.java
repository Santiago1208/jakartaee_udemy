package org.srestrepo.apiservlet.webapp.jpacdi.util;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

public class DatasourceJdbcConnection {

    public static Connection getConnection() {
        try {
            InitialContext cxt = new InitialContext();
            DataSource ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/javaCourseDB");

            if ( ds == null ) {
                throw new Exception("Data source not found!");
            }
            return ds.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
