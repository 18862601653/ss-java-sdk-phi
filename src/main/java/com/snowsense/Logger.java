package com.snowsense;

public class Logger {
    public enum LogLevel {
        Debug, Info, Warn, Error;
    }

    private Logger.LoggerHandler handler;
    private LogLevel logLevel;

    private static Logger sLogger;

    private Logger() {
        logLevel = LogLevel.Warn;

        handler = new LoggerHandler() {
            @Override
            public void debug(String tag, String message) {
                System.out.println("[Debug]-" + tag + " : " + message);
            }

            @Override
            public void info(String tag, String message) {
                System.out.println("[Info]-" + tag + " : " + message);
            }

            @Override
            public void warn(String tag, String message) {
                warn(tag, message, null);
            }

            @Override
            public void warn(String tag, String message, Throwable exception) {
                System.out.println("[warn]-" + tag + " : " + message + ", \nexception=" + exception);
            }

            @Override
            public void error(String tag, String message) {
                error(tag, message, null);
            }

            @Override
            public void error(String tag, String message, Throwable exception) {
                System.err.println("[Error]-" + tag + " : " + message + ", \nexception=" + exception);
            }
        };
    }

    public void setHandler(Logger.LoggerHandler handler) {
        this.handler = handler;
    }

    public void setLogLevel(LogLevel logLevel) {
        if (logLevel != null) {
            this.logLevel = logLevel;
        }
    }

    public static Logger get() {
        if (sLogger == null) {
            sLogger = new Logger();
        }

        return sLogger;
    }

    public void debug(String tag, String message) {
        if (shouldLog(LogLevel.Debug)) {
            handler.debug(tag, message);
        }
    }

    public void info(String tag, String message) {
        if (shouldLog(LogLevel.Info)) {
            handler.info(tag, message);
        }
    }

    public void warn(String tag, String message) {
        warn(tag, message);
    }

    public void warn(String tag, String message, Throwable exception) {
        if (shouldLog(LogLevel.Warn)) {
            handler.warn(tag, message, exception);
        }
    }

    public void error(String tag, String message) {
        error(tag, message, null);
    }

    public void error(String tag, String message, Throwable exception) {
        if (shouldLog(LogLevel.Error)) {
            handler.error(tag, message, exception);
        }
    }

    private boolean shouldLog(LogLevel requestLevel) {
        return requestLevel.ordinal() >= logLevel.ordinal();
    }

    public static interface LoggerHandler {
        void debug(String tag, String message);

        void info(String tag, String message);

        void warn(String tag, String message);

        void warn(String tag, String message, Throwable exception);

        void error(String tag, String message);

        void error(String tag, String message, Throwable exception);
    }
}
