package com.todo.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.todo.dao.TodoAppDAO;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class TodoAppGUI extends JFrame {
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
        initializeComponents();
        setupLayout();

    }

    private void initializeComponents() {
        setTitle("Todo Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        String[] columnNames = {"ID", "Title", "Description", "Completed", "Created At", "Updated At"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        todoTable = new JTable(tableModel);
        todoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        todoTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && todoTable.getSelectedRow() != -1) {
                int selectedRow = todoTable.getSelectedRow();
                titleField.setText((String) tableModel.getValueAt(selectedRow, 1));
                descriptionArea.setText((String) tableModel.getValueAt(selectedRow, 2));
                completedCheckBox.setSelected((Boolean) tableModel.getValueAt(selectedRow, 3));
            }
        });

        titleField = new JTextField(20);
        descriptionArea = new JTextArea(5, 20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);

        completedCheckBox = new JCheckBox("Completed");

        addButton = new JButton("Add todo");
        deleteButton = new JButton("Delete todo");
        updateButton = new JButton("Update todo");
        refreshButton = new JButton("Refresh todo");

        String[] filterOptions = {"All", "Completed", "Pending"};
        filterComboBox = new JComboBox<>(filterOptions);
        filterComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              
            }
        });
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Title:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(titleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Description:"), gbc);

        gbc.gridx = 1;
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);
        inputPanel.add(descriptionScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(completedCheckBox, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(filterComboBox);

        JScrollPane tableScrollPane = new JScrollPane(todoTable);

        add(inputPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TodoAppGUI app = new TodoAppGUI();
            app.setVisible(true);
        });
    }
}
