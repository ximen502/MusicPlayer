package com.bzu;

import com.bzu.util.FileUtil;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class T1 {

    public static void main(String[] args) {
/*
        JFrame window = new JFrame();
        window.setPreferredSize(new Dimension(500, 380));
        window.setSize(window.getPreferredSize());
        window.setLocationRelativeTo(null);
        window.setUndecorated(false); // 参数为false，下面的setBackground将没有效果
        window.setBackground(new Color(0x0, 0, 0, 0xff));
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
*/
//        window.setIconImage(iconImage);

        // 这个timer在控制台应用中没有任何反映
//        Timer timer = new Timer(1000, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("hello world");
//            }
//        });
//        timer.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("000000000");
//            }
//        });
//
//        timer.start();

        T1 t1 = new T1();
//        t1.lrc();
        t1.play();
    }

    private void test() {
        java.util.Timer timer = new java.util.Timer();
    }

    private void lrc() {
        //去除歌词中的时间部分，只保留歌词文字内容
        String str = FileUtil.readFileSdcard(T1.class.getResource("/xlg.lrc").getPath());
        JSONObject object = new JSONObject(str);
        String lyric = object.getJSONObject("lrc").getString("lyric");
//        System.out.println(lyric);
        String[] split = lyric.split("\n");
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
            System.out.println(split[i].replaceFirst("\\[\\d{2}:\\d{2}.\\d{2}\\]", ""));
        }
    }

    private void play() {
        //使用jl1.0.jar实现播放mp3
        //http://www.java2s.com/Code/Jar/j/Downloadjl10jar.htm
        try {
            FileInputStream inputStream = new FileInputStream(getClass().getResource("/qc.mp3").getPath());
            BufferedInputStream stream = new BufferedInputStream(inputStream);
            Player player = new Player(stream);
            player.play();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
