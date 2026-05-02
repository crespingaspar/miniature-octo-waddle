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
import com.yb.entity.KechengfenleiEntity;
import com.yb.entity.view.KechengfenleiView;

import com.yb.service.KechengfenleiService;
import com.yb.utils.PageUtils;
import com.yb.utils.R;
import com.yb.utils.MPUtil;
import com.yb.utils.MapUtils;
import com.yb.utils.CommonUtil;
import java.io.IOException;

/**
 * 课程分类
 * 后端接口
 * @author 
 * @email 
 * @date 2026-04-11 14:37:53
 */
@RestController
@RequestMapping("/kechengfenlei")
public class KechengfenleiController {
    @Autowired
    private KechengfenleiService kechengfenleiService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,KechengfenleiEntity kechengfenlei,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<KechengfenleiEntity> ew = new QueryWrapper<KechengfenleiEntity>();


        //查询结果
		PageUtils page = kechengfenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kechengfenlei), params), params));
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
    public R list(@RequestParam Map<String, Object> params,KechengfenleiEntity kechengfenlei,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<KechengfenleiEntity> ew = new QueryWrapper<KechengfenleiEntity>();

        //查询结果
		PageUtils page = kechengfenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kechengfenlei), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( KechengfenleiEntity kechengfenlei){
       	QueryWrapper<KechengfenleiEntity> ew = new QueryWrapper<KechengfenleiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( kechengfenlei, "kechengfenlei"));
        return R.ok().put("data", kechengfenleiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(KechengfenleiEntity kechengfenlei){
        QueryWrapper< KechengfenleiEntity> ew = new QueryWrapper< KechengfenleiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( kechengfenlei, "kechengfenlei"));
		KechengfenleiView kechengfenleiView =  kechengfenleiService.selectView(ew);
		return R.ok("查询课程分类成功").put("data", kechengfenleiView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        KechengfenleiEntity kechengfenlei = kechengfenleiService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(kechengfenlei,deSens);
        return R.ok().put("data", kechengfenlei);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        KechengfenleiEntity kechengfenlei = kechengfenleiService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(kechengfenlei,deSens);
        return R.ok().put("data", kechengfenlei);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody KechengfenleiEntity kechengfenlei, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(kechengfenleiService.count(new QueryWrapper<KechengfenleiEntity>().eq("kechengfenlei", kechengfenlei.getKechengfenlei()))>0) {
            return R.error("课程分类已存在");
        }
        //ValidatorUtils.validateEntity(kechengfenlei);
        kechengfenleiService.save(kechengfenlei);
        return R.ok().put("data",kechengfenlei.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody KechengfenleiEntity kechengfenlei, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(kechengfenleiService.count(new QueryWrapper<KechengfenleiEntity>().eq("kechengfenlei", kechengfenlei.getKechengfenlei()))>0) {
            return R.error("课程分类已存在");
        }
        //ValidatorUtils.validateEntity(kechengfenlei);
        kechengfenleiService.save(kechengfenlei);
        return R.ok().put("data",kechengfenlei.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody KechengfenleiEntity kechengfenlei, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kechengfenlei);
        //验证字段唯一性，否则返回错误信息
        if(kechengfenleiService.count(new QueryWrapper<KechengfenleiEntity>().ne("id", kechengfenlei.getId()).eq("kechengfenlei", kechengfenlei.getKechengfenlei()))>0) {
            return R.error("课程分类已存在");
        }
        //全部更新
        kechengfenleiService.updateById(kechengfenlei);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        kechengfenleiService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }





    // hasAlipay:否




}
