package id.thesis.shumishumi.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;

import java.util.HashMap;
import java.util.Map;

public class JSONStringUtil {
    public static String parseObject(Map<String, String> map) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            jsonString = "{}";
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
        return jsonString;
    }

    public static Map<String, String> parseJSONString(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> hashMap = new HashMap<>();
        try {
            hashMap = objectMapper.readValue(jsonString, HashMap.class);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        return hashMap;
    }
}
