package xyz.blue.service.impl;


import org.springframework.stereotype.Service;
import xyz.blue.mapper.DeviceMapper;
import xyz.blue.pojo.Device;
import xyz.blue.service.DeviceService;

import java.util.List;

@Service("DeviceService")//别名
public class DeviceServiceImpl implements DeviceService {

    private final DeviceMapper deviceMapper;

    public DeviceServiceImpl(DeviceMapper deviceMapper) {
        this.deviceMapper = deviceMapper;
    }

    @Override
    public List<Device> queryDeviceListByUserID(int user_id) {
        return deviceMapper.queryDeviceListByUserID(user_id);
    }


    @Override
    public void insert_device(Device device) {
        deviceMapper.insert_device(device);
    }

    @Override
    public void insert_Device(Device device) {
        deviceMapper.insert_Device(device);
    }

    @Override
    public void insert_deviceLog(Device device) {
        deviceMapper.insert_deviceLog(device);
    }

    @Override
    public List<Device> query_deviceById(int deviceId) {
        return deviceMapper.query_deviceById(deviceId);
    }

    @Override
    public void del_deviceById(int deviceId) {
        deviceMapper.del_deviceById(deviceId);
    }

    @Override
    public void update_deviceById(Device device) {
        deviceMapper.update_deviceById(device);
    }

    @Override
    public void update_device_statusById(Device device) {
        deviceMapper.update_device_statusById(device);
    }

    @Override
    public List<Device> queryDeviceList() {
        return deviceMapper.queryDeviceList();
    }
}

