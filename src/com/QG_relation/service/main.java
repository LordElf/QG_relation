package com.QG_relation.service;

import com.QG_relation.view.welcomes;

import javax.swing.*;

public class main {
    public static void main(String[] args){
        try{
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new welcomes().setVisible(true);
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
