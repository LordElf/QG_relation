package com.QG_relation.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class welcomes extends JFrame {
    private ImageIcon QG = new ImageIcon(getClass().getResource("/images/QG.png"));
    private UI UI = new UI();

    public welcomes(){
        CreateView();

        //make window hide application on close
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //set title
        setTitle("QG Relation");
        //set display size
        setSize(846, 360);
        //Center the frame to the middle of the screen
        setLocationRelativeTo(null);
        //setIconImage(QGIcon);
        ImageIcon QGIcon = new ImageIcon(getClass().getResource("/images/QGIcon.png"));
        setIconImage(QGIcon.getImage());
        //enable resize
        setResizable(true);
    }

    private void CreateView(){
        JPanel MainPanel = new JPanel();
        getContentPane().add(MainPanel);

        JPanel picsPanel = new JPanel();
        MainPanel.add(picsPanel);
        picsPanel.add(new JLabel(QG));

        JPanel WelComePanel = new JPanel(new GridBagLayout());
        MainPanel.add(WelComePanel);
        GridBagConstraints c = new GridBagConstraints();

        JLabel sign1 = new JLabel("Welcome to QG Relation");
        sign1.setForeground(Color.WHITE);
        sign1.setFont(new Font ("Consolas",sign1.getFont().getStyle(), 24));
        c.anchor = GridBagConstraints.CENTER;
        c.gridwidth = 2;
        c.gridy = 0;
        WelComePanel.add(sign1,c);

        JButton Enter = new JButton("Enter");
        c.gridx = 1;
        c.gridy++;
        c.weighty = 3;
        c.weightx = 2;
        c.anchor = GridBagConstraints.CENTER;
        c.gridwidth = 3;
        WelComePanel.add(Enter, c);

        WelComePanel.setBackground(Color.DARK_GRAY);
        picsPanel.setBackground(Color.DARK_GRAY);
        MainPanel.setBackground(Color.DARK_GRAY);
        Enter.setBackground(Color.DARK_GRAY);
        Enter.setForeground(Color.WHITE);

        Enter.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                UI.setVisible(true);
                            }
                        });
                    }
                }
        );

    }

}

