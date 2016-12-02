package com.example;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by Belichenko Anton on 02.12.16.
 * mailto: a.belichenko@gmail.com
 */

public class ProgramChanger {

    Logger log = Logger.getLogger(ProgramChanger.class.getName());
    private static final int NUM_THREADS = 1;
    private static final int SLEEP_MINUTES = 1;
    private boolean isInAndroidStudio;
    private int currentProgramIndex;
    private String[] programList = {"/Applications/Android Studio.app",
            "/Applications/Google Chrome.app"};

    public static final String ANDROID_STUDIO = "Android Studio";

    public boolean inAndroidStudioNow() {
        return isInAndroidStudio;
    }

    private void changeProgram() {
        if (currentProgramIndex + 1 > programList.length) {
            currentProgramIndex = 0;
        } else {
            currentProgramIndex += 1;
        }
        System.out.print("In to change method - "+programList[currentProgramIndex]);
        try {
            Desktop.getDesktop().open(new File(programList[currentProgramIndex]));
            if (programList[currentProgramIndex].contains(ANDROID_STUDIO)) {
                isInAndroidStudio = true;
            } else {
                isInAndroidStudio = false;
            }
            System.out.print("Change program successfully");
        } catch (IOException e) {
            System.out.print("in exception "+ e.toString());
            currentProgramIndex = 0;
            e.printStackTrace();
        }
    }

    public void start() {
        final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(NUM_THREADS);
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                changeProgram();
            }
        }, SLEEP_MINUTES,  SLEEP_MINUTES, TimeUnit.MINUTES);
    }

}
