package com.br.irecovery.util;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author ailson
 */
public class Log {
    private static FileHandler fileTxt;
    private static SimpleFormatter formatterTxt;
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    
    public static void setup() throws IOException {
    	// get the global logger to configure it
    	Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    	
    	// suppress the logging output to the console
        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        if (handlers[0] instanceof ConsoleHandler) {
                rootLogger.removeHandler(handlers[0]);
        }
        
        logger.setLevel(Level.INFO);
        fileTxt = new FileHandler("Logging.txt");
        System.out.println(fileTxt);
        
        // create a TXT formatter
        formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        logger.addHandler(fileTxt);
    }
    
    public static void setLog(Level level, String log){
    	  LOGGER.setLevel(level);         
          LOGGER.info(log);
          System.out.println(log);
    }
   
}
