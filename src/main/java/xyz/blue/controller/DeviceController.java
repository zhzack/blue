package xyz.blue.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.blue.pojo.Device;
import xyz.blue.server.SocketServer;
import xyz.blue.service.impl.DeviceServiceImpl;
import xyz.blue.service.impl.UserServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/device")
public class DeviceController {

    private static final Logger logger = LoggerFactory.getLogger(SocketServer.class);
    @Autowired
    UserServiceImpl userService;
    @Autowired
    DeviceServiceImpl deviceService;

    @PostMapping("/add")
    @ResponseBody
    public boolean addDevice(int device_mac, int user_id) {
        boolean addSuccess = false;
        try {
            deviceService.insert_device(new Device(device_mac, user_id, true));
            addSuccess = true;
        } catch (Exception e) {
            logger.info("添加设备失败");
        }
        return addSuccess;
    }


    @PostMapping("/update")
    @ResponseBody
    public boolean updateDevice(int device_mac, int user_id) {
        boolean updateSuccess = false;
        try {
            deviceService.update_deviceById(new Device(device_mac, user_id, true));
            updateSuccess = true;
        } catch (Exception e) {
            logger.info("设备信息更新失败");
        }
        return updateSuccess;
    }

    @PostMapping("/updateStatus")
    @ResponseBody
    public boolean updateDevice(String device_mac) {
        boolean updateSuccess = false;
        try {
            deviceService.update_device_statusById(new Device(device_mac, true));
            updateSuccess = true;
        } catch (Exception e) {
            logger.info("设备信息更新失败");
        }
        return updateSuccess;
    }

    @PostMapping("/delete")
    @ResponseBody
    public boolean delDevice(int device_mac) {
        boolean deleteSuccess = false;
        try {
            deviceService.del_deviceById(device_mac);
            deleteSuccess = true;
        } catch (Exception e) {
            logger.info("删除设备失败");
        }
        return deleteSuccess;
    }

    @PostMapping("/selectByUserId")
    @ResponseBody
    public List<Device> selectDeviceByUserId(String user_id) {
        System.out.println(user_id);
        List<Device> deviceList = null;
        try {
            deviceList = deviceService.queryDeviceListByUserID(Integer.parseInt(user_id));
        } catch (Exception e) {

//          根据用户名查询失败
            logger.info("根据用户名查询失败");

        }
        return deviceList;
    }

}
