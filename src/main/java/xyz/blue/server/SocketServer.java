package xyz.blue.server;

import lombok.Data;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import xyz.blue.pojo.Client;
import xyz.blue.pojo.Msg;
import xyz.blue.pojo.User;
import xyz.blue.server.messageTool.OnMessageTool;
import xyz.blue.server.messageTool.OpenTool;
import xyz.blue.server.messageTool.StatusConstant;
import xyz.blue.service.DeviceService;
import xyz.blue.service.MsgService;
import xyz.blue.service.UserService;
import xyz.blue.tools.NowDate;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

@ServerEndpoint(value = "/ws/{userName}")
@Component
@Data
public class SocketServer implements StatusConstant {

    private static final Logger logger = LoggerFactory.getLogger(SocketServer.class);

    private static MsgService msgService;
    private static DeviceService deviceService;
    private static UserService userService;
    private static ApplicationContext applicationContext;
    OnMessageTool onMessageTool = new OnMessageTool();
    OpenTool openTool = new OpenTool();
    private HttpSession httpSession;
    private boolean isAutowired = false;

    public synchronized static void sendAll() {
        if (!socketServers.isEmpty()) {
            //群发，不能发送给服务端自己
            socketServers.forEach(client -> {
                try {
                    client.getSession().getBasicRemote().sendText("ok");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

//            logger.info("服务端推送给所有客户端,心跳");
        } else {
            logger.info("现在没有设备连接");
        }
    }

    public synchronized static int getOnlineNum() {
        return socketServers.size();
    }

    /**
     * 用线程安全的CopyOnWriteArraySet来存放客户端连接的信息
     */
    public static final CopyOnWriteArraySet<Client> socketServers = new CopyOnWriteArraySet<>();

    /**
     * websocket封装的session,信息推送，就是通过它来信息推送
     */

    private Session session;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        SocketServer.applicationContext = applicationContext;
    }

    NowDate nowdate = new NowDate();


//    /**
//     * 服务端的userName,因为用的是set，每个客户端的username必须不一样，否则会被覆盖。
//     * 要想完成ui界面聊天的功能，服务端也需要作为客户端来接收后台推送用户发送的信息
//     */

    public MsgService getMsgService() {
        return msgService;
    }

    /**
     * 信息发送的方法，通过客户端的userName
     * 拿到其对应的session，调用信息推送的方法
     * message
     * client_id
     */
    public synchronized static void sendMessage(String message, String client_id) {

        socketServers.forEach(client -> {
            if (client.getClient_id().equals(client_id)) {
                try {
                    client.getSession().getBasicRemote().sendText(message + "");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    /**
     * 连接关闭触发，通过sessionId来移除
     * socketServers中客户端连接信息
     */
    @OnClose
    public void onClose() {
        socketServers.forEach(client -> {
            if (client.getSession().getId().equals(session.getId())) {

                logger.info("客户端:【{}】断开连接", client.getClient_id());

//                System.out.println(socketServers.stream().map(Client::getClient_id));

                socketServers.remove(client);

            }
        });
    }

    public DeviceService getDeviceService() {
        return deviceService;
    }

    /**
     * 获取在线用户名，前端界面需要用到
     */
    public synchronized static List<String> getOnlineUsers() {

        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("loginUser");

        return socketServers.stream()
                .map(Client::getClient_id)
                .collect(Collectors.toList());
    }

    public UserService getUserService() {
        return userService;
    }

    /**
     * 多个人发送给指定的几个用户
     * message
     * persons
     */
    public synchronized static void SendMany(String message, String[] persons) {
        for (String client_id : persons) {
            sendMessage(message, client_id);
        }
    }

    /**
     * 收到客户端发送信息时触发
     * 我们将其推送给客户端(blue)
     * 其实也就是服务端本身，为了达到前端聊天效果才这么做的
     * <p>
     * message
     */
    @OnMessage
    public void onMessage(String message) {
        Client client = socketServers.stream().filter(cli -> cli.getSession() == session)
                .collect(Collectors.toList()).get(0);
        msgService.insert(new Msg(message));
        onMessageTool.MsgInfoCode(message, client, msgService, deviceService, userService);

    }

    /**
     * 发生错误时触发
     * error
     */
    @OnError
    public void onError(Throwable error) {
        if (!socketServers.isEmpty()) {
            socketServers.forEach(client -> {
                if (client.getSession().getId().equals(session.getId())) {
                    socketServers.remove(client);
                    logger.error("客户端:【{}】发生异常", client.getClient_id());
                    error.printStackTrace();
                }
            });
        }

    }

    /**
     * 用户连接时触发，我们将其添加到
     * 保存客户端连接信息的socketServers中
     * <p>
     * session
     * userName
     */
    @OnOpen
    public void onopen(Session session, @PathParam(value = "userName") String client_id) {

        if (!isAutowired) {
            msgService = applicationContext.getBean(MsgService.class);
            deviceService = applicationContext.getBean(DeviceService.class);
            userService = applicationContext.getBean(UserService.class);
        }

        this.session = session;
/*
        检测是否已经使用此id连接，这里使用多用户同时连接，故注释
        for (Client client : socketServers) {
            if (Objects.equals(client.getClient_id(), client_id)) {
                noDevice = false;
                onClose();
                break;
            }
        }
*/
        Client client = new Client(client_id, session);

        openTool.onopen(client, msgService, deviceService, userService);
        logger.info(String.valueOf(deviceService.queryDeviceList()));
        socketServers.add(client);
        if (client.getIdentity() == USER) {
            logger.info(nowdate.nowDate() + "用户:【{}】连接成功[{}]", client.getClient_id(), client.getIdentity());
        } else if (client.getIdentity() == DEVICE) {
            logger.info(nowdate.nowDate() + "设备:【{}】连接成功[{}]", client.getClient_id(), client.getIdentity());
        }


    }
}