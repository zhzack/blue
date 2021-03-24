package xyz.blue.service;

import xyz.blue.pojo.Device;

import java.util.List;

public interface DeviceService {

    //通过用户名查询设备
    List<Device> queryDeviceListByUserID(int user_id);

    //查询设备列表
    List<Device> queryDeviceList();

    //加入设备
    void insert_device(Device device);

    //加入设备,设备自联结时添加
    void insert_Device(Device device);

    //日志记录
    void insert_deviceLog(Device device);

    //查找设备
    List<Device> query_deviceById(int deviceId);

    //删除设备
    void del_deviceById(int deviceId);

    //更新设备信息
    void update_deviceById(Device device);

    void update_device_statusById(Device device);

}
