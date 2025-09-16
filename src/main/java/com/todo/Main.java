package main.java.com.todo;
import java.sql.Connection;
import java.sql.SQLException;


import com.todo.util.DatabaseConnection;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection db_Connection = new DatabaseConnection();
        try {
            Connection cn = db_Connection.getDBConnection();
            System.out.println("Database connection is successfully.");
        } catch (SQLException e) {
            System.err.println("database connection is failed");
        }
    }
    
}
