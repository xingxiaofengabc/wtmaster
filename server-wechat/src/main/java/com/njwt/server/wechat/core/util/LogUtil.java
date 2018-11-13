package com.njwt.server.wechat.core.util;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

public class LogUtil {
    //private static BaseLogUtil objLog = new BaseLogUtil();
    private static final String LOGCONFIG = "log4j.properties";

    private static Logger objLog;

    private static Logger getLogger() {
        if(objLog==null){
//      DOMConfigurator.configure(getConfigFile());
            objLog = LoggerFactory.getLogger("LogUtil");
        }
        return objLog;
    }

    private static String getConfigFile(){
        String s = LogUtil.class.getClassLoader().getResource("").toString();
        String filePath = s + LOGCONFIG;
        return filePath;
    }

    // Start Info
    // /
    // / 记录信息
    // /
    // / 信息内容
    public static void info(String message, Exception exception) {
        try {
            log("INFO", message, exception);
        } catch (Exception ex) {

        }
    }

    // /
    // / 记录信息
    // /
    // / 信息内容
    public static void info(Object message) {
        log("INFO", message);
    }
    // end Info

    // Start Error
    public static void trace(String message) {
        try {
            log("TRACE", message);
        } catch (Exception ex) {
        }
    }

    public static void trace(String message, Exception exception) {
        try {
            log("TRACE", message, exception);
        } catch (Exception ex) {
        }
    }

    // /
    // / 记录一个错误信息
    // /
    // / 信息内容
    // / 异常对象
    public static void error(String message, Exception exception) {
        try {
            log("ERROR", message, exception);
        } catch (Exception ex) {

        }
    }

    // /
    // / 记录一个错误信息
    // /
    // / 信息内容
    public static void error(String message) {
        try {
            log("ERROR", message);
        } catch (Exception ex) {

        }
    }

    // end Error

    // Start Warning

    // /
    // / 记录一个警告信息
    // /
    // / 信息内容
    // / 异常对象
    public static void warning(String message, Exception exception) {
        try {
            log("WARN", message, exception);
        } catch (Exception ex) {

        }
    }

    // /
    // / 记录一个警告信息
    // /
    // / 信息内容
    public static void warning(String message) {
        try {
            log("WARN", message);
        } catch (Exception ex) {

        }
    }

    // end Warning

    // Start Fatal

    // /
    // / 记录一个程序致命性错误
    // /
    // / 信息内容
    // / 异常对象
    public static void fatal(String message, Exception exception) {
        try {
            log("FATAL", message, exception);
        } catch (Exception ex) {

        }
    }

    // /
    // / 记录一个程序致命性错误
    // /
    // / 信息内容
    public static void fatal(String message) {
        try {
            log("FATAL", message);
        } catch (Exception ex) {

        }
    }

    // end Fatal

    // Start Debug

    // /
    // / 记录调试信息
    // /
    // / 信息内容
    // / 异常对象
    public static void debug(String message, Exception exception) {
        try {
            log("DEBUG", message, exception);
        } catch (Exception ex) {

        }
    }

    // /
    // / 记录调试信息
    // /
    // / 信息内容
    public static void debug(String message) {
        try {
            log("DEBUG", message);
        } catch (Exception ex) {

        }
    }
    // end Debug

    public static void log(String level, Object msg)
    {
        log(level, msg, null);
    }

    public static void log(String level, Throwable e)
    {
        log(level, null, e);
    }

    public static void log(String level, Object msg, Throwable e)
    {
        try{

        }catch(Exception ex){
        }
    }
}
