package xyz.blue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.blue.pojo.Msg;
import xyz.blue.service.MsgService;
import xyz.blue.service.impl.DeviceServiceImpl;
import xyz.blue.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Controller

public class ChildDeviceController {


    @Autowired
    UserServiceImpl userService;
    @Autowired
    DeviceServiceImpl deviceService;

    @Autowired
    MsgService msgService;

    @RequestMapping("/insert_msg")
    @ResponseBody
    public void insert_msg() {
        Msg msg = new Msg("00", "1", "00000001", "00000002", "123");

        msgService.insert(msg);
        System.out.println(msg.toString());
    }



    @GetMapping("/getData")
    @ResponseBody
    public List<Double> getData(int device_id) {
        //JSONObject
        if (device_id != 0) {
            List<Double> data = new ArrayList<>();
            data.add((Math.random() * 100) % 30);
            data.add((Math.random() * 100) % 40);
            data.add((Math.random() * 100) % 20);
            return data;
        } else {
            return null;
        }
    }


}
