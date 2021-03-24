package xyz.blue.config;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

public class RequestListener implements ServletRequestListener {
    public RequestListener() {

    }

    public void requestInitialized(ServletRequestEvent event) {
        ((HttpServletRequest) event.getServletRequest()).getSession();

    }

    public void requestDestroyed(ServletRequestEvent arg0) {

    }
}
