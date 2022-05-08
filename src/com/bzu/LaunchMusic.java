package com.bzu;

import javax.swing.*;
import java.awt.*;

public class LaunchMusic {
    public static void main(String[] args) {
        JFrame window = new Player();
        window.setPreferredSize(new Dimension(500, 380));
        window.setSize(window.getPreferredSize());
        window.setLocationRelativeTo(null);
        window.setUndecorated(false); // 参数为false，下面的setBackground将没有效果
        window.setBackground(new Color(0x0, 0, 0, 0xff));
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

//    @Test
//    public void t1() throws URISyntaxException {
//        String str = FileUtil.readFileSdcard(getClass().getResource("/kdqg.lrc").getPath());
//        String[] arr = str.split(System.getProperty("line.separator"));
////        System.out.println(str);
//        for (int i = 0; i < arr.length; i++) {
//            System.out.println(arr[i]);
//        }
//    }
}
