package ink.dwx;

import cn.hutool.core.io.FileUtil;
import jaco.mp3.player.MP3Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Player {

    private MP3Player mp3Player;

    public Player(String path) {
        mp3Player = new MP3Player();
        mp3Player.setRepeat(true);
        mp3Player.setShuffle(true);

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
        } else if (FileUtil.isFile(path)) {
            System.out.println("文件:" + path);
            mp3Player.addToPlayList(FileUtil.file(path));
            play();
        } else {
            System.out.println("不支持的操作");
        }
    }

    private void play() {
        mp3Player.play();
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
