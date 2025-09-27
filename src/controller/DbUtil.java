package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtil {
    private static final String URL = "jdbc:sqlite:appdatabase.db"; 

    static {
        initializeDatabase();
    }

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    private static void initializeDatabase() {
        String sql_cat = "CREATE TABLE IF NOT EXISTS categories ("
                + "	catID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "	cat_name TEXT NOT NULL"
                + ");";
        String sql_loc = "CREATE TABLE IF NOT EXISTS locations ("
                + "	locID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "	loc_name TEXT NOT NULL,"
                + "	loc_desc TEXT"
                + ");";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql_cat);
            stmt.execute(sql_loc);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
