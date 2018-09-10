package tw.com.wd.service;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.web.context.support.ServletRequestHandledEvent;
import org.springframework.web.servlet.DispatcherServlet;

public class MyListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {

        ServletRequestHandledEvent servletReqEent = null;
        DispatcherServlet dispatcherServlet = null;

        if (applicationEvent instanceof ServletRequestHandledEvent) {
            servletReqEent = (ServletRequestHandledEvent)applicationEvent;

            if (servletReqEent.getSource() instanceof DispatcherServlet) {
                dispatcherServlet = (DispatcherServlet) servletReqEent.getSource();
            }
        }

        System.out.printf("ApplicationEvent Class: %s\n", applicationEvent.getClass().toString());
        System.out.printf("ApplicationEvent Source: %s\n", applicationEvent.getSource().getClass().toString());


    }
}
