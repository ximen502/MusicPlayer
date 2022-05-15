package com.bzu;

import com.bzu.util.FileUtil;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import jmp123.demo.MiniPlayer;
import jmp123.output.Audio;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class T1 {

    private MiniPlayer miniPlayer;

    public static void main(String[] args) {
        T1 t1 = new T1();

        t1.ui(t1);


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

//        t1.lrc();
//        t1.play();
//        t1.playByJmp();
//        t1.chkFile();
    }

    private void ui(T1 t1) {
        JFrame window = new JFrame();
        window.setPreferredSize(new Dimension(500, 380));
        window.setSize(window.getPreferredSize());
        window.setLocationRelativeTo(null);
        window.setUndecorated(false); // 参数为false，下面的setBackground将没有效果
        window.setBackground(new Color(0x0, 0, 0, 0xff));
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new FlowLayout());
        JButton play = new JButton("Play");
        JButton previous = new JButton("<<<");
        JButton next = new JButton(">>>");
        panel.add(previous);
        panel.add(play);
        panel.add(next);
        window.add(panel, BorderLayout.CENTER);

//        window.setIconImage(iconImage);

        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println("-----");
                playByJmp();
                mPlayer.start();
            }
        });
    }

    Thread mPlayer = new Thread() {
        @Override
        public void run() {
            super.run();
            while (true) {
//                if (miniPlayer.isPaused()) {
//                    miniPlayer.run();
//                } else {
//                    miniPlayer.pause();
//                }
            }
        }
    };

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

    private void playByJmp() {
        //使用jmp123.jar实现
        //http://jmp123.sourceforge.net/index.html
        String path = getClass().getResource("/qc.mp3").getPath();
        if (miniPlayer != null) {
            return;
        }
        miniPlayer = new MiniPlayer(new Audio());
        long t0 = System.nanoTime();
//        System.out.println(t0);
        try {
            String msg = miniPlayer.open(path);
            long t1 = System.nanoTime() - t0;
            File file = new File(path);
            long length = file.length();
            int frameCount = miniPlayer.getFrameCount();
            System.out.println(msg);
            System.out.println("------------------------");
            System.out.printf("    length: %d bytes, %d frames\n", length, frameCount);
            System.out.printf("elapsed time: %d ns (%.9fs, %.2f, fps)\n", t1, t1/1e9, frameCount/(t1/1e9));
            miniPlayer.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void playByThird() {

    }

    private void chkFile(){
        String s1 = FileUtil.getFileMD5("/Music/MusicPlayer/lib/jmp123.jar");
        String s2 = FileUtil.getFileMD5("/bin/classes/jmp123.jar");
        System.out.println(s1);
        System.out.println(s2);
    }

}
