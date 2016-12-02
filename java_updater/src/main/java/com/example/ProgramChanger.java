package com.example;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * Created by Belichenko Anton on 02.12.16.
 * mailto: a.belichenko@gmail.com
 */

public class ProgramChanger {

    private static final long SLEEP = 10;
    private boolean isInAndroidStudio;
    private int currentProgramIndex;

    private String[] programList = {
            "/Applications/Google Chrome.app",
            "/Applications/Android Studio.app"};

    public static final String ANDROID_STUDIO = "Android Studio";

    public boolean inAndroidStudioNow() {
        return isInAndroidStudio;
    }

    private void changeProgram() {
        if (currentProgramIndex + 1 > programList.length) {
            currentProgramIndex = 0;
        } else {
            currentProgramIndex = currentProgramIndex + 1;
        }
        System.out.println("In to change method - " + programList[currentProgramIndex]);
        try {
            Desktop.getDesktop().open(new File(programList[currentProgramIndex]));
            if (programList[currentProgramIndex].contains(ANDROID_STUDIO)) {
                isInAndroidStudio = true;
            } else {
                isInAndroidStudio = false;
            }
            System.out.println("Change program successfully");
        } catch (IOException e) {
            System.out.println("in exception " + e.toString());
            currentProgramIndex = 0;
            e.printStackTrace();
        }
    }

    public void start() {

        final ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("programChange")
                .setDaemon(true)
                .build();

        final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor(threadFactory);
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                changeProgram();
            }

        }, 10, SLEEP, TimeUnit.SECONDS);
    }

}
