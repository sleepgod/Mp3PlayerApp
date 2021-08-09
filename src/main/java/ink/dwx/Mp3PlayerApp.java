package ink.dwx;

/**
 * Hello world!
 */
public class Mp3PlayerApp {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("缺少参数!");
            return;
        }

        new Player(args[0]);
    }
}
