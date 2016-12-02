package com.example;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Main {
    public static final int FIVE_SECONDS = 5000;
    public static final int MAX_Y = 400;
    public static final int MAX_X = 400;
    public static Robot robot;


    public static void main(String... args) throws Exception {

        ProgramChanger programChanger = new ProgramChanger();
        programChanger.start();

        robot = new Robot();
        Random random = new Random();
        while (true) {
            robot.mouseMove(random.nextInt(MAX_X), random.nextInt(MAX_Y));
            Thread.sleep(FIVE_SECONDS);
            swap();
        }
    }


    public static void swap() {
        int duration = 1000;
        long start = System.currentTimeMillis() + duration;

        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_CLOSE_BRACKET);
        robot.keyRelease(KeyEvent.VK_META);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
        while (System.currentTimeMillis() < start) {
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.delay(800);
            robot.keyRelease(KeyEvent.VK_DOWN);
        }
        start = System.currentTimeMillis() + duration;
        while (System.currentTimeMillis() < start) {
            robot.keyPress(KeyEvent.VK_UP);
            robot.delay(800);
            robot.keyRelease(KeyEvent.VK_UP);
        }
    }

}
