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
import com.yb.service.TokenService;
import com.yb.entity.TokenEntity;
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
import com.yb.entity.XueshengEntity;
import com.yb.entity.view.XueshengView;

import com.yb.service.XueshengService;
import com.yb.utils.PageUtils;
import com.yb.utils.R;
import com.yb.utils.MPUtil;
import com.yb.utils.MapUtils;
import com.yb.utils.CommonUtil;
import java.io.IOException;
import com.yb.service.SmsregistercodeService;
import com.yb.entity.SmsregistercodeEntity;

/**
 * 学生
 * 后端接口
 * @author 
 * @email 
 * @date 2026-04-11 14:37:53
 */
@RestController
@RequestMapping("/xuesheng")
public class XueshengController {
    @Autowired
    private XueshengService xueshengService;
    @Autowired
    private SmsregistercodeService smsregistercodeService;







	@Autowired
	private TokenService tokenService;

	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		// 根据登录查询用户信息
        XueshengEntity u = xueshengService.getOne(new QueryWrapper<XueshengEntity>().eq("xuehao", username));
        // 判断用户锁定状态
        if(u!=null && u.getStatus().intValue()==1) {
            //返回已锁定提示
            return R.error("账号已锁定，请联系管理员。");
        }
        if(u==null || !u.getMima().equals(password)) {
            //账号或密码不正确提示
			return R.error("账号或密码不正确");
		}
        // 获取登录token
		String token = tokenService.generateToken(u.getId(), username,"xuesheng",  "学生" );
        //返回token
		return R.ok().put("token", token);
	}



	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody XueshengEntity xuesheng, @RequestParam(required = false) String smscode){
    	//ValidatorUtils.validateEntity(xuesheng);
        //根据登录账号获取用户信息判断是否存在该用户，否则返回错误信息
    	XueshengEntity u = xueshengService.getOne(new QueryWrapper<XueshengEntity>().eq("xuehao", xuesheng.getXuehao()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
        //判断是否存在相同学号，否则返回错误信息
        if(xueshengService.count(new QueryWrapper<XueshengEntity>().eq("xuehao", xuesheng.getXuehao()))>0) {
            return R.error("学号已存在");
        }
        //判断是否存在相同手机号，否则返回错误信息
        u =xueshengService.getOne(new QueryWrapper<XueshengEntity>().eq("mobile", xuesheng.getMobile()));
        if(u!=null) {
            return R.error("手机已被注册");
        }
        //判断验证码是否正确，否则返回错误信息
        List<SmsregistercodeEntity> smsregistercodeList = smsregistercodeService.list(new QueryWrapper<SmsregistercodeEntity>().eq("role", "学生").eq("mobile", xuesheng.getMobile()).orderByDesc("addtime"));
        boolean smsValidate = false;
        if(smsregistercodeList!=null && smsregistercodeList.size()>0) {
            if(smsregistercodeList.get(0).getCode().equals(smscode)) {
                smsValidate = true;
            }
        }
        if(!smsValidate) return R.error("短信验证码不正确");
		Long uId = new Date().getTime();
		xuesheng.setId(uId);
        //保存用户
        xueshengService.save(xuesheng);
        return R.ok();
    }


    /**
     * 发送短信
     */
    @IgnoreAuth
    @RequestMapping("/sendsms")
    public R sendsms(@RequestParam String mobile){
        String code = CommonUtil.getRandomNumber(6);
        SmsregistercodeEntity smsregistercode = new SmsregistercodeEntity();
        smsregistercode.setCode(code);
        smsregistercode.setRole("学生");
        smsregistercode.setMobile(mobile);
        smsregistercodeService.save(smsregistercode);
        CommonUtil.sendSMS(mobile, code);
        return R.ok().put("data", code);
    }

    /**
     * 发送登录短信
     */
    @IgnoreAuth
    @RequestMapping("/sendsms/login")
    public R sendsmsForLogin(@RequestParam String mobile){
        XueshengEntity u =xueshengService.getOne(new QueryWrapper<XueshengEntity>().eq("mobile", mobile));
        if(u==null) return R.error("用户不存在");
        String code = CommonUtil.getRandomNumber(6);
        SmsregistercodeEntity smsregistercode = new SmsregistercodeEntity();
        smsregistercode.setCode(code);
        smsregistercode.setRole("学生");
        smsregistercode.setMobile(mobile);
        smsregistercodeService.save(smsregistercode);
        CommonUtil.sendSMS(mobile, code);
        return R.ok().put("data", code);
    }

    /**
     * 手机号登录
     */
    @IgnoreAuth
    @RequestMapping("/sms/login")
    public R emailLogin(@RequestParam String mobile,@RequestParam(required = false) String smscode){
        XueshengEntity u =xueshengService.getOne(new QueryWrapper<XueshengEntity>().eq("mobile", mobile));
        if(u==null) return R.error("用户不存在");
        //判断验证码是否正确，否则返回错误信息
        List<SmsregistercodeEntity> smsregistercodeList = smsregistercodeService.list(new QueryWrapper<SmsregistercodeEntity>().eq("role", "学生").eq("mobile", mobile).orderByDesc("addtime"));
        boolean smsValidate = false;
        if(smsregistercodeList!=null && smsregistercodeList.size()>0) {
            if(smsregistercodeList.get(0).getCode().equals(smscode)) {
                smsValidate = true;
            }
        }
        if(!smsValidate) return R.error("短信验证码不正确");
        // 判断用户锁定状态
        if(u!=null && u.getStatus().intValue()==1) {
            //返回已锁定提示
            return R.error(510,"该账号已被锁定，是否恢复？").put("username", u.getXuehao());
        }
        // 获取登录token
        String token = tokenService.generateToken(u.getId(), u.getXuehao(),"xuesheng",  "学生" );
        //返回token
        return R.ok().put("token", token).put("username", u.getXuehao());
    }


	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}

	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        XueshengEntity u = xueshengService.getById(id);
        return R.ok().put("data", u);
    }

    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	//根据登录账号判断是否存在用户信息，否则返回错误信息
        XueshengEntity u = xueshengService.getOne(new QueryWrapper<XueshengEntity>().eq("xuehao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        //重置密码为123456
        u.setMima("123456");
        xueshengService.updateById(u);
        return R.ok("密码已重置为：123456");
    }

    /**
     * 获取账号列表
     */
    @RequestMapping("/accountList")
    public R getAccountList(@RequestParam Map<String, Object> params,XueshengEntity xuesheng){
        QueryWrapper<XueshengEntity> ew = new QueryWrapper<XueshengEntity>();
        QueryWrapper<XueshengEntity> wrapper =MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xuesheng), params), params);
        List<Map> list = xueshengService.list(wrapper).stream().map(v -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", v.getId());
            map.put("account", v.getXuehao());
            return map;
        }).collect(Collectors.toList());
        return R.ok().put("data", list);
    }






    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,XueshengEntity xuesheng,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<XueshengEntity> ew = new QueryWrapper<XueshengEntity>();


        //查询结果
		PageUtils page = xueshengService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xuesheng), params), params));
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
    public R list(@RequestParam Map<String, Object> params,XueshengEntity xuesheng,
                @RequestParam(required = false) Double nianlingstart,
                @RequestParam(required = false) Double nianlingend,
                @RequestParam(required = false) Double statusstart,
                @RequestParam(required = false) Double statusend,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<XueshengEntity> ew = new QueryWrapper<XueshengEntity>();
        if(nianlingstart!=null) ew.ge("nianling", nianlingstart);
        if(nianlingend!=null) ew.le("nianling", nianlingend);
        if(statusstart!=null) ew.ge("status", statusstart);
        if(statusend!=null) ew.le("status", statusend);

        //查询结果
		PageUtils page = xueshengService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xuesheng), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( XueshengEntity xuesheng){
       	QueryWrapper<XueshengEntity> ew = new QueryWrapper<XueshengEntity>();
      	ew.allEq(MPUtil.allEQMapPre( xuesheng, "xuesheng"));
        return R.ok().put("data", xueshengService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(XueshengEntity xuesheng){
        QueryWrapper< XueshengEntity> ew = new QueryWrapper< XueshengEntity>();
 		ew.allEq(MPUtil.allEQMapPre( xuesheng, "xuesheng"));
		XueshengView xueshengView =  xueshengService.selectView(ew);
		return R.ok("查询学生成功").put("data", xueshengView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        XueshengEntity xuesheng = xueshengService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(xuesheng,deSens);
        return R.ok().put("data", xuesheng);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        XueshengEntity xuesheng = xueshengService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(xuesheng,deSens);
        return R.ok().put("data", xuesheng);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody XueshengEntity xuesheng, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(xueshengService.count(new QueryWrapper<XueshengEntity>().eq("xuehao", xuesheng.getXuehao()))>0) {
            return R.error("学号已存在");
        }
        //ValidatorUtils.validateEntity(xuesheng);
        //验证账号唯一性，否则返回错误信息
        XueshengEntity u = xueshengService.getOne(new QueryWrapper<XueshengEntity>().eq("xuehao", xuesheng.getXuehao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	xuesheng.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		xuesheng.setId(new Date().getTime());
        xueshengService.save(xuesheng);
        return R.ok().put("data",xuesheng.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody XueshengEntity xuesheng, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(xueshengService.count(new QueryWrapper<XueshengEntity>().eq("xuehao", xuesheng.getXuehao()))>0) {
            return R.error("学号已存在");
        }
        //ValidatorUtils.validateEntity(xuesheng);
        //验证账号唯一性，否则返回错误信息
        XueshengEntity u = xueshengService.getOne(new QueryWrapper<XueshengEntity>().eq("xuehao", xuesheng.getXuehao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	xuesheng.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		xuesheng.setId(new Date().getTime());
        xueshengService.save(xuesheng);
        return R.ok().put("data",xuesheng.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        XueshengEntity xuesheng = xueshengService.getOne(new QueryWrapper<XueshengEntity>().eq("xuehao", username));
        return R.ok().put("data", xuesheng);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody XueshengEntity xuesheng, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xuesheng);
        //验证字段唯一性，否则返回错误信息
        if(xueshengService.count(new QueryWrapper<XueshengEntity>().ne("id", xuesheng.getId()).eq("xuehao", xuesheng.getXuehao()))>0) {
            return R.error("学号已存在");
        }
        //全部更新
        xueshengService.updateById(xuesheng);
        if(null!=xuesheng.getXuehao())
        {
            // 修改token
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setUsername(xuesheng.getXuehao());
            tokenService.update(tokenEntity, new UpdateWrapper<TokenEntity>().eq("userid", xuesheng.getId()));
        }
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        xueshengService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }





    // hasAlipay:否




}
