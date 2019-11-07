package com.raines.raineslearn.createThreadDemo;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器Timer
 */
public class CreatThreadDemo5 {
    public static void main(String[] args) {
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("定时器线程执行了...");
            }
        },0,1000);   //延迟0，周期1s

    }
}
