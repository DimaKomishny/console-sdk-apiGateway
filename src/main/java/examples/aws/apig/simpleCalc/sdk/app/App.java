package examples.aws.apig.simpleCalc.sdk.app;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Replier replier = new Replier();
        while (true) {
            System.out.println(replier.reply(scanner.nextLine()));
        }
    }
}