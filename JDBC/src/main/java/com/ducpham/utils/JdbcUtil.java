package com.ducpham.utils;

import oracle.jdbc.driver.OracleDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtil {
    private final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private final static String URL = "jdbc:oracle:thin:@//localhost:1521/pdb_learning";
    private final static String USER = "sys as sysdba";
    private final static String PASSWORD = "30122002";

    private static final Connection CONNECTION = buildConnection();

    private JdbcUtil() {}

    private static Connection buildConnection() {
        if (CONNECTION == null) {
            try {
                DriverManager.registerDriver(new OracleDriver());
                Connection connection =  DriverManager.getConnection(URL, USER, PASSWORD);
                connection.setAutoCommit(false);
                return connection;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return CONNECTION;
    }

    public static Connection getConnection() {
        return CONNECTION;
    }
}
