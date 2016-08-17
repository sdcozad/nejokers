package com.nejokers.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.SimpleLogger;
import org.testng.ITestContext;

public class SLF4JLogging {


    public Logger initLogger(final ITestContext context, final String className) {

        System.setProperty(SimpleLogger.DEFAULT_LOG_LEVEL_KEY,
            context.getCurrentXmlTest().getParameter("testLogLevel"));
        System.setProperty(SimpleLogger.DATE_TIME_FORMAT_KEY, "[yyyy/MM/dd HH:mm:ss]");
        System.setProperty(SimpleLogger.SHOW_DATE_TIME_KEY, "true");
        System.setProperty(SimpleLogger.LEVEL_IN_BRACKETS_KEY, "true");
        Logger logger = LoggerFactory.getLogger(className);
        return logger;
    }

}
