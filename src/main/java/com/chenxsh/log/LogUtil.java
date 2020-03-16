package com.chenxsh.log;

import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 日志工具类
 * 目前使用
 *
 * @author chenxsh
 */
public class LogUtil {
    public static final String TAG = "chenxshAndroidLog";

    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int NOTHING = 6;
    public static final int level = VERBOSE;// 修改日志打印级别，生产环境请改为：NOTHING

    /**
     * 输出的最大行数
     */
    private final static int LOG_MAXLENGTH = 3 * 1024;

    /**
     * Log输出所在类
     */
    private static String className;

    /**
     * Log输出所在方法
     */
    private static String methodName;

    /**
     * Log输出所行号
     */
    private static int lineNumber;

    /**
     * 当前线程ID
     */
    private static String threadName;

    /**
     * 创建Log输出的基本信息
     *
     * @param log 日志信息
     * @return 处理后的日志信息
     */
    private static String createLog(String log) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        buffer.append(methodName);
        buffer.append("()");
        buffer.append(" line:");
        buffer.append(lineNumber);
        buffer.append(" threadName:");
        buffer.append(threadName);
        buffer.append("] ");
        buffer.append(log);

        return buffer.toString();
    }

    /**
     * 取得输出所在位置的信息 className methodName lineNumber
     *
     * @param sElements 堆栈；深度1
     */
    private static void getMethodNames(StackTraceElement[] sElements) {
        className = sElements[1].getFileName().split("\\.")[0];
        methodName = sElements[1].getMethodName();
        lineNumber = sElements[1].getLineNumber();
        threadName = Thread.currentThread().getName();
    }

    /**
     * 取得输出所在位置的信息 className methodName lineNumber
     *
     * @param sElements 堆栈；深度2
     */
    private static void getMethodNames2(StackTraceElement[] sElements) {
        className = sElements[2].getFileName().split("\\.")[0];
        methodName = sElements[2].getMethodName();
        lineNumber = sElements[2].getLineNumber();
        threadName = Thread.currentThread().getName();
    }

    /**
     * VERBOSE级别
     *
     * @param tag 日志输出标签
     * @param msg 日志关键信息
     */
    public static void v(String tag, String msg) {
        if (msg == null) {
            msg = "null";
        }
        if (level <= VERBOSE) {
            if (msg.length() <= LOG_MAXLENGTH) {
                getMethodNames(new Throwable().getStackTrace());
//                Log.v(tag, className + createLog(msg));
                log(tag, msg, VERBOSE);
            } else {
                logUtil(tag, msg, VERBOSE);
            }
        }
    }

    /**
     * DEBUG级别
     *
     * @param tag 日志输出标签
     * @param msg 日志关键信息
     */
    public static void d(String tag, String msg) {
        if (msg == null) {
            msg = "null";
        }
        if (level <= DEBUG) {
            if (msg.length() <= LOG_MAXLENGTH) {
                getMethodNames(new Throwable().getStackTrace());
//                Log.d(tag, className + createLog(msg));
                log(tag, msg, DEBUG);
//                return;
            } else {
                logUtil(tag, msg, DEBUG);
            }
//            int strLength = msg.length();
//            int start = 0;
//            int end = LOG_MAXLENGTH;
//            // 截断取整
//            for (int i = 0; i <= (msg.length() / LOG_MAXLENGTH); i++) {
//                if (strLength > end) {
//                    getMethodNames(new Throwable().getStackTrace());
//                    Log.d(tag + "->" + i + "<-", className + createLog(msg.substring(start, end)));
//                    log(tag + "->" + i + "<-", msg.substring(start, end), DEBUG);
//                    start = end;
//                    end = end + LOG_MAXLENGTH;
//                }
//                else {
//                    getMethodNames(new Throwable().getStackTrace());
//                    Log.d(tag + "-->" + i + "<--", className + createLog(msg.substring(start, strLength)));
//                    log(tag + "-->" + i + "<--", msg.substring(start, strLength), DEBUG);
//                   // 跳出循环
//                    break;
//                }
//            }
        }
    }

    /**
     * INFO级别
     *
     * @param tag 日志输出标签
     * @param msg 日志关键信息
     */
    public static void i(String tag, String msg) {
        if (msg == null) {
            msg = "null";
        }
        if (level <= INFO) {
            if (msg.length() <= LOG_MAXLENGTH) {
                getMethodNames(new Throwable().getStackTrace());
//                Log.i(tag, className + createLog(msg));
                log(tag, msg, INFO);
            } else {
                logUtil(tag, msg, INFO);
            }
        }
    }

    /**
     * WARN级别
     *
     * @param tag 日志输出标签
     * @param msg 日志关键信息
     */
    public static void w(String tag, String msg) {
        if (msg == null) {
            msg = "null";
        }
        if (level <= WARN) {
            if (msg.length() <= LOG_MAXLENGTH) {
                getMethodNames(new Throwable().getStackTrace());
//                Log.w(tag, className + createLog(msg));
                log(tag, msg, WARN);
            } else {
                logUtil(tag, msg, WARN);
            }
        }

    }

    /**
     * ERROR级别
     *
     * @param tag 日志输出标签
     * @param msg 日志关键信息
     */
    public static void e(String tag, String msg) {
        if (msg == null) {
            msg = "null";
        }
        if (level <= ERROR) {
            if (msg.length() <= LOG_MAXLENGTH) {
                getMethodNames(new Throwable().getStackTrace());
//                Log.e(tag, className + createLog(msg));
                log(tag, msg, ERROR);
            } else {
                logUtil(tag, msg, ERROR);
//                int strLength = msg.length();
//                int start = 0;
//                int end = LOG_MAXLENGTH;
//                // 截断取整
//                for (int i = 0; i <= (msg.length() / LOG_MAXLENGTH); i++) {
//                    if (strLength > end) {
//                        getMethodNames(new Throwable().getStackTrace());
////                        Log.e(tag + "->" + i + "<-", className + createLog(msg.substring(start, end)));
//                        log(tag + "->" + i + "<-", msg.substring(start, end), ERROR);
//                        start = end;
//                        end = end + LOG_MAXLENGTH;
//                    }
//                    else {
//                        getMethodNames(new Throwable().getStackTrace());
////                        Log.e(tag + "-->" + i + "<--", className + createLog(msg.substring(start, strLength)));
//                        log(tag + "-->" + i + "<--", msg.substring(start, strLength), ERROR);
//                        // 跳出循环
//                        break;
//                    }
//                }
            }
        }
    }

    /**
     * 不受等级控制的日志输出
     *
     * @param tag 日志输出标签
     * @param msg 日志关键信息
     */
    public static void n(String tag, String msg) {
        if (msg == null) {
            msg = "null";
        }
        if (level <= NOTHING) {
            getMethodNames(new Throwable().getStackTrace());
//            Log.i(tag, className + createLog(msg));
            log(tag, msg, INFO);
        }
    }

    /**
     * 日志输出；等级封装
     *
     * @param tag      日志输出标签
     * @param msg      日志关键信息
     * @param logLevel 日志等级
     */
    private static void logUtil(String tag, String msg, int logLevel) {
        int strLength = msg.length();
        int start = 0;
        int end = LOG_MAXLENGTH;

        // 截断取整
        for (int i = 0; i <= (msg.length() / LOG_MAXLENGTH); i++) {
            if (strLength > end) {
                getMethodNames2(new Throwable().getStackTrace());
                log(tag + "->" + i + "<-", msg.substring(start, end), logLevel);
                start = end;
                end = end + LOG_MAXLENGTH;
            } else {
                getMethodNames2(new Throwable().getStackTrace());
                log(tag + "-->" + i + "<--", msg.substring(start, strLength), logLevel);
                // 跳出循环
                break;
            }
        }
    }

    /**
     * 日志输出
     *
     * @param tag      日志输出标签
     * @param msg      日志关键信息
     * @param logLevel 日志等级
     */
    private static void log(String tag, String msg, int logLevel) {
        switch (logLevel) {
            case VERBOSE:
                Log.v(tag, className + createLog(msg));
//                Log.v(tag, msg);
                break;
            case DEBUG:
                Log.d(tag, className + createLog(msg));
//                Log.d(tag, msg);
                break;
            case INFO:
                Log.i(tag, className + createLog(msg));
//                Log.i(tag, msg);
                break;
            case WARN:
                Log.w(tag, className + createLog(msg));
//                Log.w(tag, msg);
                break;
            case ERROR:
                Log.e(tag, className + createLog(msg));
//                Log.e(tag, msg);
                break;
            default:
                break;
        }
    }

    /**
     * 获取堆栈信息
     *
     * @param throwable 异常
     * @return 异常堆栈
     */
    public static String getStackTraceString(Throwable throwable) {
        StringWriter sw = new StringWriter();
        try {
            PrintWriter pw = new PrintWriter(sw, true);
            throwable.printStackTrace(pw);
            pw.flush();
            pw.close();

            sw.flush();
            sw.close();
        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
        } catch (Throwable t) {
            Log.e(TAG, Log.getStackTraceString(t));
        }
        return sw.toString();
    }
}
