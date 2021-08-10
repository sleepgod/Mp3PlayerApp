package ink.dwx;

import org.apache.commons.cli.*;

/**
 * Hello world!
 */
public class Mp3PlayerApp {

    public static void main(String[] args) {
        CommandLineParser parser = new DefaultParser();

        Options options = new Options();
        options.addOption("h", false, "帮助");
        options.addOption("g", false, "显示图形界面");
        options.addOption("f", true, "播放单个mp3文件");
        options.addOption("p", true, "播放此文件夹下的mp3文件");

        try {
            CommandLine commandLine = parser.parse(options, args);
            Player player = new Player(commandLine.hasOption("g"));

            if (commandLine.hasOption("p")) {
                player.playPath(commandLine.getOptionValue("p"));
            } else if (commandLine.hasOption("f")) {
                player.playFile(commandLine.getOptionValue("f"));
            } else if (commandLine.hasOption("h")) {
                showHelp(options);
            } else {
                showHelp(options);
            }
        } catch (ParseException e) {
            System.out.println("输入错误:");
            showHelp(options);
        }
    }

    private static void showHelp(Options options) {
        Option[] ops = options.getOptions().toArray(new Option[options.getOptions().size()]);
        for (int i = 0; i < ops.length; i++) {
            System.out.println("-" + ops[i].getOpt() + " : " + ops[i].getDescription());
        }
    }
}
