package xyz.blue.tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JsonToString {

//    /*
//     * 解析设备发送的json转对象
//     * */
//    public static DeviceMsg ToDeviceMsg(String msg) {
//
//        try {
//            JSONObject obj = JSON.parseObject(msg);
//
//            if (obj.size() == 3) {
//                try {
//                    return new DeviceMsg(obj.getInteger("device_id"), obj.getInteger("to_user_id"), obj.getString("msg"));
//                } catch (Exception n) {
//                    return null;
//                }
//            } else {
//                //解析错误：key键不对应，数据类型错误
//                return null;
//            }
//
//        } catch (JSONException e) {
//            return null;
//        }
//    }

    public static String DeviceMsg(String msg) {
        JSONObject obj = JSON.parseObject(msg);
        return null;
    }
}
