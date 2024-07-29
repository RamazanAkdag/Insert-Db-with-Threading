package org.example.tracking;

import org.example.logging.Logger;

public class ProgressBar {
    private final int total;
    private int current = 0;
    private final Logger logger;

    public ProgressBar(int total, Class<?> context) {
        this.total = total;
        this.logger = Logger.getInstance(context);
    }

    public synchronized void increment() {
        current++;
        printProgress();
    }

    private void printProgress() {
        int progressPercentage = (int) (((double) current / total) * 100);
        StringBuilder progress = new StringBuilder();
        for (int i = 0; i < 50; i++) {
            if (i < progressPercentage / 2) {
                progress.append("=");
            } else {
                progress.append(" ");
            }
        }
        System.out.print("\r[" + progress + "] " + progressPercentage + "%");
    }
}
