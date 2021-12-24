/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author dell
 */
public class SQLServerConnection {
    public static final String HOSTNAME = "localhost";
    public static final String PORT = "1433";
    public static final String DBNAME = "Wine";
    public static final String USERNAME = "sa";
    public static final String PASSWORD = "123";

    /**
     * Get connection to MSSQL Server
     * @return Connection
     */
    public static Connection getConnection() {
        
        // Create a variable for the connection string.
        String connectionUrl = "jdbc:sqlserver://"+HOSTNAME+":"+PORT+";"
                             + "databaseName="+DBNAME;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(connectionUrl, USERNAME, PASSWORD);
        }
        // Handle any errors that may have occurred.
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
}
