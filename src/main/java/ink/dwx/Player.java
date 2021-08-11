package ink.dwx;

import cn.hutool.core.io.FileUtil;
import jaco.mp3.player.MP3Player;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {

    private MP3Player mp3Player;
    private boolean gui = false;

    public void playFile(String file) {
        if (FileUtil.isFile(file)) {
            mp3Player.addToPlayList(FileUtil.file(file));
            play();
        } else {
            System.out.println("不支持的操作");
        }
    }

    public void playPath(String path) {
        if (FileUtil.isDirectory(path)) {
            List<File> loopList = FileUtil.loopFiles(path, null);
            List<File> fList = new ArrayList<>();
            for (int i = 0; i < loopList.size(); i++) {
                String type = FileUtil.getType(loopList.get(i));
                if (type.equalsIgnoreCase("mp3")) {
                    fList.add(loopList.get(i));
                }
            }
            System.out.println("文件数:" + fList.size());
            for (int i = 0; i < fList.size(); i++) {
                mp3Player.addToPlayList(fList.get(i));
            }
            play();
        } else {
            System.out.println("不支持的操作");
        }
    }

    public Player(boolean gui) {
        this.gui = gui;
        mp3Player = new MP3Player();
        mp3Player.setRepeat(true);
        mp3Player.setShuffle(true);

    }

    private void play() {
        if (gui) {
            mp3Player.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

            JFrame frame = new JFrame("MP3 Player");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(mp3Player);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }

        mp3Player.play();

        if (!gui) {
            myRun();
        }
    }

    private void showHelp() {
        System.out.println("快捷操作:");
        System.out.println("\tn : 下一首");
        System.out.println("\tm : 上一首");
        System.out.println("\ta : 暂停");
        System.out.println("\tp : 播放");
        System.out.println("\tq : 退出");
    }

    private void myRun() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (mp3Player.isStopped()) {
                break;
            }
            showHelp();
            String opsn = scanner.next();
            switch (opsn) {
                case "n": {
                    mp3Player.skipForward();
                    break;
                }
                case "m": {
                    mp3Player.skipBackward();
                    break;
                }
                case "a": {
                    mp3Player.pause();
                    break;
                }
                case "p": {
                    mp3Player.play();
                    break;
                }
                case "q": {
                    mp3Player.stop();
                    break;
                }
            }
        }
    }
}
