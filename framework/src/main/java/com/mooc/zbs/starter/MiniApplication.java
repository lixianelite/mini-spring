package com.mooc.zbs.starter;

import com.mooc.zbs.beans.BeanFactory;
import com.mooc.zbs.core.ClassScanner;
import com.mooc.zbs.web.handler.HandlerManager;
import com.mooc.zbs.web.server.TomcatServer;

import java.util.List;

public class MiniApplication {

    public static void run(Class<?> cls, String[] args) {
        System.out.println("Hello mini-spring!");
        TomcatServer tomcatServer = new TomcatServer(args);

        try {
            tomcatServer.startServer();
            List<Class<?>> classList = ClassScanner.scannClass(cls.getPackage().getName());
            classList.forEach(it -> System.out.println(it.getName()));
            HandlerManager.resolveMappingHandler(classList);
            BeanFactory.initBean(classList);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
