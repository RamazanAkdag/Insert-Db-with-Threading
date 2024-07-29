package org.example.logging;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Logger {
    private static Logger logger;
    private PrintWriter writer;
    private LogLevel level;
    private String context;

    private ExecutorService executorService;

    private Logger() {
        executorService = Executors.newSingleThreadExecutor();
    }

    public static Logger getInstance(Class<?> clazz) {
        if (logger == null) {
            synchronized (Logger.class) {
                if (logger == null) {
                    logger = new Logger();
                    ((Logger) logger).initialize(clazz);
                }
            }
        }
        return logger;
    }

    private void initialize(Class<?> clazz) {
        this.context = clazz.getSimpleName();

        writer = new PrintWriter(System.out);
        this.level = LogLevel.DEBUG;
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }

    private void logMessage(LogLevel level, String message) {
        if (level.ordinal() >= this.level.ordinal()) {
           Thread thread = new Thread(() -> {
               String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
               writer.println(timeStamp + " [" + level + "] [" + context + "] " + message);
               writer.flush();
           });
           thread.start();
        }
    }

    public void logInline(String message) {
        executorService.submit(() -> {
            writer.print(message);
            writer.flush();
        });
    }

    public void debug(String message) {
        logMessage(LogLevel.DEBUG, message);
    }


    public void info(String message) {
        logMessage(LogLevel.INFO, message);
    }


    public void warn(String message) {
        logMessage(LogLevel.WARN, message);
    }


    public void error(String message) {
        logMessage(LogLevel.ERROR, message);
    }


    public void log(LogLevel level, String message) {
        logMessage(level, message);
    }

    public void close() {
        executorService.shutdown();
        writer.close();
    }
}