package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;

public class TimePrinter implements ActionListener {
    public static void main(String[] args) {
        var listener = new TimePrinter();
        var timer = new Timer(1000,listener);
        timer.start();
        JOptionPane.showMessageDialog(null,"quit?");
        System.exit(0);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("at the tone ,the time is " + Instant.ofEpochMilli(e.getWhen())
        );
        Toolkit.getDefaultToolkit().beep();
    }
}
