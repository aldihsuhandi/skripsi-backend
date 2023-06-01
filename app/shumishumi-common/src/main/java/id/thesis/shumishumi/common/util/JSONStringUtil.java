package id.thesis.shumishumi.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

public class JSONStringUtil {
    public static String parseObject(Map<String, String> map) {
        ObjectMapper objectMapper = new ObjectMapper();
        if (CollectionUtils.isEmpty(map)) {
            map = new HashMap<>();
        }
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
        if (StringUtils.isEmpty(jsonString)) {
            return new HashMap<>();
        }

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
