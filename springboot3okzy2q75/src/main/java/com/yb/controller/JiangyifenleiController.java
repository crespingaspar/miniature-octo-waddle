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
import com.yb.entity.JiangyifenleiEntity;
import com.yb.entity.view.JiangyifenleiView;

import com.yb.service.JiangyifenleiService;
import com.yb.utils.PageUtils;
import com.yb.utils.R;
import com.yb.utils.MPUtil;
import com.yb.utils.MapUtils;
import com.yb.utils.CommonUtil;
import java.io.IOException;

/**
 * 讲义分类
 * 后端接口
 * @author 
 * @email 
 * @date 2026-04-11 14:37:54
 */
@RestController
@RequestMapping("/jiangyifenlei")
public class JiangyifenleiController {
    @Autowired
    private JiangyifenleiService jiangyifenleiService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JiangyifenleiEntity jiangyifenlei,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<JiangyifenleiEntity> ew = new QueryWrapper<JiangyifenleiEntity>();


        //查询结果
		PageUtils page = jiangyifenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiangyifenlei), params), params));
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
    public R list(@RequestParam Map<String, Object> params,JiangyifenleiEntity jiangyifenlei,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<JiangyifenleiEntity> ew = new QueryWrapper<JiangyifenleiEntity>();

        //查询结果
		PageUtils page = jiangyifenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiangyifenlei), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JiangyifenleiEntity jiangyifenlei){
       	QueryWrapper<JiangyifenleiEntity> ew = new QueryWrapper<JiangyifenleiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jiangyifenlei, "jiangyifenlei"));
        return R.ok().put("data", jiangyifenleiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JiangyifenleiEntity jiangyifenlei){
        QueryWrapper< JiangyifenleiEntity> ew = new QueryWrapper< JiangyifenleiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jiangyifenlei, "jiangyifenlei"));
		JiangyifenleiView jiangyifenleiView =  jiangyifenleiService.selectView(ew);
		return R.ok("查询讲义分类成功").put("data", jiangyifenleiView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JiangyifenleiEntity jiangyifenlei = jiangyifenleiService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(jiangyifenlei,deSens);
        return R.ok().put("data", jiangyifenlei);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JiangyifenleiEntity jiangyifenlei = jiangyifenleiService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(jiangyifenlei,deSens);
        return R.ok().put("data", jiangyifenlei);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JiangyifenleiEntity jiangyifenlei, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(jiangyifenleiService.count(new QueryWrapper<JiangyifenleiEntity>().eq("jiangyifenlei", jiangyifenlei.getJiangyifenlei()))>0) {
            return R.error("讲义分类已存在");
        }
        //ValidatorUtils.validateEntity(jiangyifenlei);
        jiangyifenleiService.save(jiangyifenlei);
        return R.ok().put("data",jiangyifenlei.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody JiangyifenleiEntity jiangyifenlei, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(jiangyifenleiService.count(new QueryWrapper<JiangyifenleiEntity>().eq("jiangyifenlei", jiangyifenlei.getJiangyifenlei()))>0) {
            return R.error("讲义分类已存在");
        }
        //ValidatorUtils.validateEntity(jiangyifenlei);
        jiangyifenleiService.save(jiangyifenlei);
        return R.ok().put("data",jiangyifenlei.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody JiangyifenleiEntity jiangyifenlei, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jiangyifenlei);
        //验证字段唯一性，否则返回错误信息
        if(jiangyifenleiService.count(new QueryWrapper<JiangyifenleiEntity>().ne("id", jiangyifenlei.getId()).eq("jiangyifenlei", jiangyifenlei.getJiangyifenlei()))>0) {
            return R.error("讲义分类已存在");
        }
        //全部更新
        jiangyifenleiService.updateById(jiangyifenlei);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        jiangyifenleiService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }





    // hasAlipay:否




}
