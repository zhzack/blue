package xyz.blue.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.blue.pojo.Device;
import xyz.blue.server.SocketServer;
import xyz.blue.service.impl.DeviceServiceImpl;
import xyz.blue.service.impl.UserServiceImpl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Controller

public class TestController {


    @Autowired
    UserServiceImpl userService;

    @Autowired
    DeviceServiceImpl deviceService;
    private static final Logger logger = LoggerFactory.getLogger(SocketServer.class);

    @GetMapping("/t")
    @ResponseBody
    public List<Device> get_device(int device) {
        List<Device> deviceList = null;
        deviceService.update_device_statusById(new Device(String.valueOf(device), true));
//        try {
//            deviceList = deviceService.query_deviceById(device);
//        } catch (Exception e) {
//            logger.info(String.valueOf(e));
//        }
////            查询设备列表，如果查询无结果，则向数据库插入
////            有结果则更新设备状态，并把设备列表设置给client对象
//        try {
//            if (deviceList == null) {
////                logger.info(client.getClient_id()+"无设备");
//                deviceService.insert_device(new Device(String.valueOf(device), true));
//            } else {
////                logger.info(client.getClient_id()+"更新状态");
//
//            }
////            client.setDeviceList(deviceList);
//        } catch (Exception e) {
//            logger.info(String.valueOf(e));
//        }
        List<Device> d = deviceService.query_deviceById(device);
        return d;
    }

    @GetMapping("/get_ws_ip")
    @ResponseBody
    public void get_ws_ip() throws UnknownHostException {
        InetAddress addr = InetAddress.getLocalHost();
        System.out.println("Local HostAddress: " + addr.getHostAddress());

        System.out.println(InetAddress.getLocalHost().getHostAddress());
        String hostname = addr.getHostName();
        System.out.println("Local host name: " + hostname);
    }


}
