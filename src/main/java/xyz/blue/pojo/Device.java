package xyz.blue.pojo;

public class Device {

    /*
     * 设备id
     * 设备名
     * 设备用户群
     * */
    private String device_id;


    private String device_name;
    private Integer user_id;
    private boolean status;
    private String device_mac;

    public Device() {
    }

    public Device(String device_id, Integer user_id) {
        this.device_id = device_id;
        this.user_id = user_id;
    }

    public Device(Integer user_id) {
        this.user_id = user_id;
    }

    public Device(String device_id, boolean status) {
        this.device_id = device_id;
        this.status = status;
    }

    public Device(String device_id, String device_name) {
        this.device_id = device_id;
        this.device_name = device_name;
    }

    public Device(String device_id, String device_name, Integer user_id, boolean status) {
        this.device_id = device_id;
        this.device_name = device_name;
        this.user_id = user_id;
        this.status = status;
    }

    public Device(String device_name, Integer user_id, boolean status) {
        this.device_name = device_name;
        this.user_id = user_id;
        this.status = status;
    }

    public Device(String device_id, String device_name, Integer user_id, boolean status, String device_mac) {
        this.device_id = device_id;
        this.device_name = device_name;
        this.user_id = user_id;
        this.status = status;
        this.device_mac = device_mac;
    }

    public Device(String device_name, String device_mac, Integer user_id) {
        this.device_name = device_name;
        this.user_id = user_id;
        this.device_mac = device_mac;
    }

    public Device(int device_mac, int user_id, boolean status) {
        this.device_mac = String.valueOf(device_mac);
        this.user_id = user_id;
        this.status = status;

    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Device{" +
                "device_id=" + device_id +
                ", device_name='" + device_name + '\'' +
                ", device_users=" + user_id +
                '}';
    }

    public String getDevice_mac() {
        return device_mac;
    }

    public void setDevice_mac(String device_mac) {
        this.device_mac = device_mac;
    }
}
