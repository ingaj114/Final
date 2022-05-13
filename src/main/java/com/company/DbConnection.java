package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    public static Connection getConnection() {
        Connection connection = null;
        try {
//            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/final", "postgres", "123");
            connection = DriverManager.getConnection("jdbc:postgresql://tyke.db.elephantsql.com/opeaozkw", "opeaozkw", "ykTbJYmiSmJnBZLAML0q2sPJhiYCeClj");
//            if (connection != null) System.out.println("Connection successful!");
        } catch (SQLException e) {
            System.out.println("There was a problem connecting to database!");
            e.printStackTrace();
        }
        return connection;
    }
}
