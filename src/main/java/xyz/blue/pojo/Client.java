package xyz.blue.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import xyz.blue.server.messageTool.StatusConstant;
import xyz.blue.service.impl.DeviceServiceImpl;

import javax.websocket.Session;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Client implements Serializable, StatusConstant {
    @Autowired
    DeviceServiceImpl deviceService;

    private static final long serialVersionUID = 8957107006902627635L;

    private String client_id;
    private Integer identity;
    private Session session;

    private List<Device> DeviceList = new ArrayList<>();


    public Client(String client_id, Session session) {
//        System.out.println(client_id+"第零位"+client_id.charAt(0));
//        根据第零位判断客户端身份
        if (String.valueOf(client_id.charAt(0)).equals("U")) {
            this.client_id = client_id.substring(1);
            this.identity = USER;
//            this.DeviceList.addAll(deviceService.queryDeviceListByUserID(Integer.parseInt(this.client_id)));
        } else {
            this.client_id = client_id;
            this.identity = DEVICE;
//            this.DeviceList.addAll(deviceService.query_deviceById(Integer.parseInt(client_id)));
        }
//        除去id前的零
        if (this.identity == USER) {
            for (int i = 0; i < this.client_id.length(); i++) {
                if (!String.valueOf(this.client_id.charAt(i)).equals("0")) {
                    this.client_id = this.client_id.substring(i);
                    break;
                }
            }
        }
        this.session = session;
    }

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public List<Device> getDeviceList() {
        return DeviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        DeviceList = deviceList;
    }

    public String getClient_id() {
        return client_id;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public Client() {
    }


}
