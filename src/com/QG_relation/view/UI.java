package com.QG_relation.view;

import com.QG_relation.model.Relation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame{
    private JTextArea outputBox;
    private JTextField inputBox;
    private JButton btnSubmit, btnClear;
    Relation relation;

    public UI(){
        CreateView();
        relation = new Relation();

        //make window hide application on close
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //set title
        setTitle("QG Relation");
        //set display size
        setSize(846, 360);
        setMinimumSize(new Dimension(846, 360));
        //Center the frame to the middle of the screen
        setLocationRelativeTo(null);
        //setIconImage(QGIcon);
        ImageIcon QGIcon = new ImageIcon(getClass().getResource("/images/QGIcon.png"));
        setIconImage(QGIcon.getImage());
        //enable resize
        setResizable(true);
    }

    private void CreateView() {
        JPanel MainPanel = new JPanel(new BorderLayout());
        MainPanel.setBorder(new EmptyBorder(10, 10, 10,10));
        getContentPane().add(MainPanel);

        // Top(North)
        JPanel NorthPanel = new JPanel(new BorderLayout());
        MainPanel.add(NorthPanel, BorderLayout.NORTH);

        JLabel label = new JLabel("Enter two people: ");
        NorthPanel.add(label, BorderLayout.WEST);

        inputBox = new JTextField(25);
        NorthPanel.add(inputBox, BorderLayout.CENTER);

        btnSubmit = new JButton("Submit");
        NorthPanel.add(btnSubmit, BorderLayout.EAST);
        btnSubmit.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String[] people = relation.tellStr(inputBox.getText());
                        String result = relation.getRelation(people).toString();
                        result = result.substring(1, result.length() - 1);
                        outputBox.append(result+"\n");
                        inputBox.setText("");
                    }
                }
        );

        //center
        outputBox = new JTextArea();
        outputBox.setEditable(false);
        //change to another row in the text box
        // when the sentence hits the end of the current row
        outputBox.setLineWrap(true);
        outputBox.setWrapStyleWord(true);
        outputBox.setText("Input two name, use [space] to separate them\n");

        JScrollPane scrollPane = new JScrollPane(outputBox);
        MainPanel.add(scrollPane);

        // Bottom(South)
        JPanel SouthPanel = new JPanel(new BorderLayout());
        MainPanel.add(SouthPanel, BorderLayout.SOUTH);

        btnClear = new JButton("Clear Text");
        SouthPanel.add(btnClear);
        btnClear.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        outputBox.setText("");
                    }
                }
        );
    }
}
