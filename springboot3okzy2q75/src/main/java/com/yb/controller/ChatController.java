package com.yb.controller;

import java.util.*;
import com.yb.utils.*;
import com.yb.service.*;
import com.yb.entity.*;
import com.yb.entity.view.*;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, ChatEntity chat, HttpServletRequest request){
        if(!request.getSession().getAttribute("role").toString().equals("管理员")) {
            chat.setUserid((Long)request.getSession().getAttribute("userId"));
        }
        QueryWrapper<ChatEntity> ew = new QueryWrapper<ChatEntity>();
        if (null != params.get("userid")) {
            ChatEntity new_chat = new ChatEntity();
            new_chat.setIsread(1);
            chatService.update(new_chat, new UpdateWrapper<ChatEntity>().eq("userid" , params.get("userid")).eq("isread","0"));
        }
        PageUtils page = chatService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chat), params), params));
        return R.ok().put("data", page);
    }

    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, ChatEntity chat, HttpServletRequest request){
        if(!request.getSession().getAttribute("role").toString().equals("管理员")) {
            chat.setUserid((Long)request.getSession().getAttribute("userId"));
        }
        QueryWrapper<ChatEntity> ew = new QueryWrapper<ChatEntity>();
        PageUtils page = chatService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chat), params), params));
        return R.ok().put("data", page);
    }

    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ChatEntity chat = chatService.getById(id);
        return R.ok().put("data", chat);
    }

    /**
     * 【核心修改】前台/后台发送消息接口，接入 DeepSeek
     */
    @RequestMapping("/add")
    public R add(@RequestBody ChatEntity chat, HttpServletRequest request){
        Long currentUserId = (Long)request.getSession().getAttribute("userId");
        chat.setUserid(currentUserId);
        chat.setAddtime(new Date());
        chat.setIsreply(0); // 0 表示用户提问

        // 1. 保存用户的提问
        chatService.save(chat);

        // 2. 如果是文字提问，则触发异步 DeepSeek 回复
        if (StringUtils.isNotBlank(chat.getAsk()) && chat.getType() == 1) {
            final String userContent = chat.getAsk();
            final Long uid = currentUserId;

            new Thread(() -> {
                try {
                    // 调用你之前写的 DeepSeek 工具类
                    String aiResult = DeepSeekUtil.getChatResponse(userContent);

                    // 3. 构造 AI 的回复记录
                    ChatEntity replyEntity = new ChatEntity();
                    replyEntity.setUserid(uid);
                    replyEntity.setAsk(""); // AI回复，提问设为空
                    replyEntity.setReply(aiResult);
                    replyEntity.setIsreply(1); // 1 表示客服/AI 回复
                    replyEntity.setType(1);
                    replyEntity.setAddtime(new Date());

                    // 4. 保存到数据库
                    chatService.save(replyEntity);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
        return R.ok();
    }

    @RequestMapping("/save")
    public R save(@RequestBody ChatEntity chat, HttpServletRequest request){
        return add(chat, request); // 让 save 逻辑和 add 一致
    }

    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ChatEntity chat, HttpServletRequest request){
        chatService.updateById(chat);
        return R.ok();
    }

    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        chatService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }
}