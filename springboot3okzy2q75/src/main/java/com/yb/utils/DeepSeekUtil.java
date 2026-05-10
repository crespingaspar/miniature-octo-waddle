package com.yb.utils;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class DeepSeekUtil {

    private static final String API_KEY = "sk-a6e8cac4b00e4387ae515aab29b57732";
    private static final String API_URL = "https://api.deepseek.com/chat/completions";
    public static void main(String[] args) {
        System.out.println("测试开始...");
        System.out.println(getChatResponse("你好"));
    }
    public static String getChatResponse(String userMessage) {
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("model", "deepseek-chat");

            JSONArray messages = new JSONArray();
            JSONObject systemMsg = new JSONObject();
            systemMsg.put("role", "system");
            systemMsg.put("content", "你是一个智能在线学习平台的客服助手。");
            messages.add(systemMsg);

            JSONObject userMsg = new JSONObject();
            userMsg.put("role", "user");
            userMsg.put("content", userMessage);
            messages.add(userMsg);

            paramMap.put("messages", messages);
            paramMap.put("stream", false);

            // 发送请求
            String result = HttpRequest.post(API_URL)
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .body(JSON.toJSONString(paramMap))
                    .timeout(60000)
                    .execute().body();

            // --- 核心调试代码：打印原始返回结果 ---
            System.out.println("DeepSeek服务器原始返回内容：" + result);
            // ----------------------------------

            JSONObject jsonObject = JSON.parseObject(result);

            // 检查是否有错误信息返回
            if (jsonObject.containsKey("error")) {
                return "DeepSeek报错：" + jsonObject.getJSONObject("error").getString("message");
            }

            JSONArray choices = jsonObject.getJSONArray("choices");
            if (choices != null && !choices.isEmpty()) {
                return choices.getJSONObject(0).getJSONObject("message").getString("content");
            }
            return "抱歉，客服系统开小差了，请稍后再试。";
        } catch (Exception e) {
            System.err.println("发生异常错误：");
            e.printStackTrace();
            return "连接客服大模型失败，请检查网络。";
        }
    }
//    public static String getChatResponse(String userMessage) {
//        try {
//            Map<String, Object> paramMap = new HashMap<>();
//            paramMap.put("model", "deepseek-chat");
//
//            JSONArray messages = new JSONArray();
//            // 系统设定（你可以修改这段话，让AI拥有不同的性格）
//            JSONObject systemMsg = new JSONObject();
//            systemMsg.put("role", "system");
//            systemMsg.put("content", "你是一个智能在线学习平台的客服助手。请用友善、专业的语气回答学生或老师的问题。回答尽量简短精炼。");
//            messages.add(systemMsg);
//
//            // 用户输入
//            JSONObject userMsg = new JSONObject();
//            userMsg.put("role", "user");
//            userMsg.put("content", userMessage);
//            messages.add(userMsg);
//
//            paramMap.put("messages", messages);
//            paramMap.put("stream", false);
//
//            String result = HttpRequest.post(API_URL)
//                    .header("Authorization", "Bearer " + API_KEY)
//                    .header("Content-Type", "application/json")
//                    .body(JSON.toJSONString(paramMap))
//                    .timeout(20000) // 设置超时时间20秒
//                    .execute().body();
//
//            JSONObject jsonObject = JSON.parseObject(result);
//            JSONArray choices = jsonObject.getJSONArray("choices");
//            if (choices != null && !choices.isEmpty()) {
//                return choices.getJSONObject(0).getJSONObject("message").getString("content");
//            }
//            return "抱歉，客服系统开小差了，请稍后再试。";
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "连接客服大模型失败，请检查网络或API Key配置。";
//        }
//    }
}
