package xyz.blue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import xyz.blue.server.SocketServer;

@SpringBootApplication
@EnableAsync //开启异步调用
public class BlueApplication {

    public static void main(String[] args) {
//        SpringApplication.run(BlueApplication.class, args);
        ConfigurableApplicationContext configurableApplicationContext =
                SpringApplication.run(BlueApplication.class, args);
        //这里将Spring Application注入到websocket类中定义的Application中。
        SocketServer.setApplicationContext(configurableApplicationContext);
    }


}
