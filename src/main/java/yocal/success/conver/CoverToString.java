package yocal.success.conver;

import yocal.success.exception.YocalException;

import java.util.Map;

/**
 * @author: success
 * @date: 2025/9/11 16:58
 * @version: v1.0.0
 * @description: TODO
 **/
public class CoverToString {

    /**
     * 将Map转换为JSON字符串
     * @param map 业务参数Map
     * @return JSON字符串
     * @throws YocalException 转换异常
     */
    public static String convertMapToJson(Map<String, Object> map) throws YocalException {
        try {
            if (map == null || map.isEmpty()) {
                return "{}";
            }

            StringBuilder json = new StringBuilder("{");
            boolean first = true;

            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (!first) {
                    json.append(",");
                }

                // 添加键
                json.append("\"").append(entry.getKey()).append("\":");

                // 添加值
                Object value = entry.getValue();
                if (value == null) {
                    json.append("null");
                } else if (value instanceof String) {
                    json.append("\"").append(value).append("\"");
                } else if (value instanceof Number || value instanceof Boolean) {
                    json.append(value);
                } else {
                    // 对于复杂对象，转换为字符串
                    json.append("\"").append(value.toString()).append("\"");
                }

                first = false;
            }

            json.append("}");
            return json.toString();
        } catch (Exception e) {
            throw new YocalException("业务内容转换为JSON失败", e);
        }
    }
}
