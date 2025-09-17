package com.todo;

import java.sql.Connection;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.todo.util.DatabaseConnection;
import com.todo.gui.TodoAppGUI;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection db_Connection = new DatabaseConnection();
        try {
            Connection cn = db_Connection.getDBConnection();
            System.out.println("Database connection is successful.");
        } catch (Exception e) {
            System.err.println("Database connection failed");
            System.exit(1);   
        }

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | 
                 IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.err.println("Failed to set look and feel: " + e.getMessage());
        }

        SwingUtilities.invokeLater(() -> {
            try {
                new TodoAppGUI().setVisible(true);  
            } catch (Exception e) {
                System.err.println("Failed to launch GUI: " + e.getLocalizedMessage());
            }
        });
    }
}

