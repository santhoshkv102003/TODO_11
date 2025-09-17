package com.todo.gui;

import javax.swing.*;
imort javax.swing.table.DefaultTableModel;
imprort com.todo.dao.TodoAppDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;
public class TodoAppGUI{
        private TodoAppDAO todoDAO;
        private JTable todoTable;
        private DefaultTableModel tableModel;
        private JTextField titleField;
        private JTextArea descriptionArea;
        private JCheckBox completedCheckBox;
        private JButton addButton;
        private JButton deleteButton;
        private JButton updateButton;
        private JButton refreshButton;
        private JComboBox<String> filterComboBox;

        public TodoAppGUI() {
            this.todoDAO = new TodoAppDAO();
            initializeUI();
            loadTodos();
        }
}