package com.fastjrun.ddns;

import org.quartz.Scheduler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BatchInit {

    public static final String SPRING_CONFIG_FILE = "applicationContext.xml";

    public static final String COMMAND_START_DEFAULT = "startup";

    public void startup(String command) throws Exception {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(SPRING_CONFIG_FILE);
        Scheduler scheduler = (Scheduler) appContext.getBean("scheduler");

        if (COMMAND_START_DEFAULT.equalsIgnoreCase(command)) {
            scheduler.start();
        } 
    }

    public static void main(String[] args) {
        try {
            String command = "startup";
            BatchInit batch = new BatchInit();
            batch.startup(command);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
