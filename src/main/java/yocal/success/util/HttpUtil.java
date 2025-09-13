package yocal.success.util;

import yocal.success.config.YocalV2Config;
import yocal.success.exception.YocalException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * HTTP工具类
 */
public class HttpUtil {

    /**
     * 发送POST请求
     * @param params 请求参数
     * @return 响应结果
     * @throws YocalException HTTP请求异常
     */
    public static String post(YocalV2Config config,Map<String,String> params,String sign) throws YocalException {
        try {
            URL obj = new URL(config.getGateway());
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            con.setRequestProperty("Accept", "*/*");
            con.setRequestProperty("Connection", "keep-alive");
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);

            StringBuilder urlParameters = new StringBuilder();

            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (urlParameters.length() > 0) {
                    urlParameters.append("&");
                }
                urlParameters.append(URLEncoder.encode(entry.getKey(), "UTF-8"))
                        .append("=")
                        .append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            }

            urlParameters.append("&sign=").append(URLEncoder.encode(sign, "UTF-8"));

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = urlParameters.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = con.getResponseCode();

            String responseMessage = con.getResponseMessage();

            for (int i = 1; con.getHeaderFieldKey(i) != null; i++) {
                System.out.println("  " + con.getHeaderFieldKey(i) + ": " + con.getHeaderField(i));
            }

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                return response.toString();
            } else {

                BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream(), StandardCharsets.UTF_8));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                throw new YocalException("HTTP请求失败，响应码: " + responseCode + "，响应内容: " + response.toString());
            }
        } catch (Exception e) {
            throw new YocalException("HTTP请求异常", e);
        }
    }

    /**
     * 发送POST请求并返回响应体和响应头
     * @param config SDK配置
     * @param params 请求参数
     * @param sign 签名
     * @return 包含响应体和响应头的数组，[0]为响应体，[1]为响应头Map
     * @throws YocalException HTTP请求异常
     */
    public static Object[] postWithHeaders(YocalV2Config config, Map<String,String> params, String sign) throws YocalException {
        try {
            URL obj = new URL(config.getGateway());
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            con.setRequestProperty("Accept", "*/*");
            con.setRequestProperty("Connection", "keep-alive");
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);

            StringBuilder urlParameters = new StringBuilder();

            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (urlParameters.length() > 0) {
                    urlParameters.append("&");
                }
                urlParameters.append(URLEncoder.encode(entry.getKey(), "UTF-8"))
                        .append("=")
                        .append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            }

            urlParameters.append("&sign=").append(URLEncoder.encode(sign, "UTF-8"));

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = urlParameters.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = con.getResponseCode();
            String responseMessage = con.getResponseMessage();

            // 收集响应头
            Map<String, String> headers = new HashMap<>();
            for (int i = 1; con.getHeaderFieldKey(i) != null; i++) {
                String headerName = con.getHeaderFieldKey(i);
                String headerValue = con.getHeaderField(i);
                headers.put(headerName, headerValue);
                System.out.println("  " + headerName + ": " + headerValue);
            }

            String responseBody;
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                responseBody = response.toString();
            } else {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream(), StandardCharsets.UTF_8));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                responseBody = response.toString();
                throw new YocalException("HTTP请求失败，响应码: " + responseCode + "，响应内容: " + responseBody);
            }

            return new Object[]{responseBody, headers};

        } catch (Exception e) {
            throw new YocalException("HTTP请求异常", e);
        }
    }
}
