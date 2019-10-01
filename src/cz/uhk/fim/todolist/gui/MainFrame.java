package cz.uhk.fim.todolist.gui;

import com.sun.deploy.panel.JavaPanel;
import cz.uhk.fim.todolist.model.TodoItem;
import cz.uhk.fim.todolist.model.TodoList;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private JTable table;
    private TodoList todoList;
    private TodoTableModel model;

    public MainFrame() {
        init();
    }

    private void init() {
        setTitle("Todo List App");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(640, 480);
        setLocationRelativeTo(null);

        todoList = new TodoList();
        model = new TodoTableModel();
        model.setList(todoList);

        initControlPanel();
        initContentPanel();
    }

    private void initContentPanel() {
        JPanel contentPanel = new JPanel(new BorderLayout());
        table = new JTable();
        table.setModel(model);
        contentPanel.add(new JScrollPane(table));
        add(contentPanel, BorderLayout.CENTER);
    }

    private void initControlPanel() {
        JPanel controlPanel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new BorderLayout());
        JLabel lblAddTodo = new JLabel("Zadejte todo:");
        JTextField txtAddTodo = new JTextField();
        JButton btnAdd = new JButton("Přidat");

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                todoList.addItem(new TodoItem(txtAddTodo.getText()));
                txtAddTodo.setText("");
                model.setList(todoList);
            }
        });

        formPanel.add(lblAddTodo, BorderLayout.WEST);
        formPanel.add(txtAddTodo, BorderLayout.CENTER);
        formPanel.add(btnAdd, BorderLayout.EAST);

        controlPanel.add(formPanel, BorderLayout.NORTH);

        add(controlPanel, BorderLayout.NORTH);

    }
}
