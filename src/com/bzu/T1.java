package com.bzu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class T1 {

    public static void main(String[] args) {
//        System.out.println("");
        JFrame window = new JFrame();
        window.setPreferredSize(new Dimension(500, 380));
        window.setSize(window.getPreferredSize());
        window.setLocationRelativeTo(null);
        window.setUndecorated(false); // 参数为false，下面的setBackground将没有效果
        window.setBackground(new Color(0x0, 0, 0, 0xff));
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        window.setIconImage(iconImage);

        // 这个timer在控制台应用中没有任何反映
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("hello world");
            }
        });
        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("000000000");
            }
        });

        timer.start();
    }

    private void test() {
        java.util.Timer timer = new java.util.Timer();
    }


}
