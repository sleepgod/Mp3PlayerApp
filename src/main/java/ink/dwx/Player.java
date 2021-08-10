package ink.dwx;

import cn.hutool.core.io.FileUtil;
import jaco.mp3.player.MP3Player;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

    private void myRun() {
        while (true) {
            if (mp3Player.isStopped()) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
