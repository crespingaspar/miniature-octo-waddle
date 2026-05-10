package com.yb.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import com.yb.utils.*;
import com.yb.service.*;
import com.yb.entity.*;
import com.yb.entity.view.*;
import java.util.stream.Collectors;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.yb.annotation.IgnoreAuth;
import com.yb.entity.ChathelperEntity;
import com.yb.entity.view.ChathelperView;

import com.yb.service.ChathelperService;
import com.yb.utils.PageUtils;
import com.yb.utils.R;
import com.yb.utils.MPUtil;
import com.yb.utils.MapUtils;
import com.yb.utils.CommonUtil;
import java.io.IOException;

/**
 * 聊天助手表
 * 后端接口
 * @author 
 * @email 
 * @date 2026-04-11 14:37:55
 */
@RestController
@RequestMapping("/chathelper")
public class ChathelperController {
    @Autowired
    private ChathelperService chathelperService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ChathelperEntity chathelper,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<ChathelperEntity> ew = new QueryWrapper<ChathelperEntity>();


        //查询结果
		PageUtils page = chathelperService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chathelper), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }


    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ChathelperEntity chathelper,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<ChathelperEntity> ew = new QueryWrapper<ChathelperEntity>();

        //查询结果
		PageUtils page = chathelperService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chathelper), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ChathelperEntity chathelper){
       	QueryWrapper<ChathelperEntity> ew = new QueryWrapper<ChathelperEntity>();
      	ew.allEq(MPUtil.allEQMapPre( chathelper, "chathelper"));
        return R.ok().put("data", chathelperService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ChathelperEntity chathelper){
        QueryWrapper< ChathelperEntity> ew = new QueryWrapper< ChathelperEntity>();
 		ew.allEq(MPUtil.allEQMapPre( chathelper, "chathelper"));
		ChathelperView chathelperView =  chathelperService.selectView(ew);
		return R.ok("查询聊天助手表成功").put("data", chathelperView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ChathelperEntity chathelper = chathelperService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(chathelper,deSens);
        return R.ok().put("data", chathelper);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ChathelperEntity chathelper = chathelperService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(chathelper,deSens);
        return R.ok().put("data", chathelper);
    }




    /**
     * 保存聊天记录（接入DeepSeek自动回复 - 适配无userid版本）
     */
    @RequestMapping("/save")
    public R save(@RequestBody ChathelperEntity chathelper, HttpServletRequest request){
        // 1. 保存用户的原始提问
        // 这里的 setId 逻辑用于手动生成一个唯一的 ID，如果数据库是自增的，这一行可以注释掉
        chathelper.setId(new Date().getTime() + (long)(Math.random() * 1000));
        chathelperService.save(chathelper);

        // 2. 只有当用户有提问（ask不为空）时，才触发 AI 回复
        if (chathelper.getAsk() != null && !chathelper.getAsk().trim().isEmpty()) {
            // 调用 DeepSeek 工具类获取回答
            String aiReply = DeepSeekUtil.getChatResponse(chathelper.getAsk());

            // 3. 构造 AI 回复实体
            ChathelperEntity replyEntity = new ChathelperEntity();

            // 手动设置回复消息的 ID（防止与上一条冲突，加 1）
            replyEntity.setId(new Date().getTime() + (long)(Math.random() * 1000) + 1);

            // AI 的话存入 reply 字段，ask 字段留空
            replyEntity.setAsk("");
            replyEntity.setReply(aiReply);

            // 【关键】这里不再调用 setUserId 或 getUserid，避免报错

            // 稍作延迟保存，确保在数据库中排序在用户提问之后
            try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }

            chathelperService.save(replyEntity);
        }

        return R.ok();
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ChathelperEntity chathelper, HttpServletRequest request){
        //ValidatorUtils.validateEntity(chathelper);
        chathelperService.save(chathelper);
        return R.ok().put("data",chathelper.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        ChathelperEntity chathelper = chathelperService.getOne(new QueryWrapper<ChathelperEntity>().eq("", username));
        return R.ok().put("data", chathelper);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody ChathelperEntity chathelper, HttpServletRequest request){
        //ValidatorUtils.validateEntity(chathelper);
        //全部更新
        chathelperService.updateById(chathelper);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        chathelperService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }

	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,ChathelperEntity chathelper, HttpServletRequest request,String pre){
        QueryWrapper<ChathelperEntity> ew = new QueryWrapper<ChathelperEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        // 组装参数
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");

		PageUtils page = chathelperService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chathelper), params), params));
        return R.ok().put("data", page);
    }




    // hasAlipay:$hasAlipay




}
