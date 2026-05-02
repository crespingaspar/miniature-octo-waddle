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
import com.yb.entity.ZuoyexinxiEntity;
import com.yb.entity.view.ZuoyexinxiView;

import com.yb.service.ZuoyexinxiService;
import com.yb.utils.PageUtils;
import com.yb.utils.R;
import com.yb.utils.MPUtil;
import com.yb.utils.MapUtils;
import com.yb.utils.CommonUtil;
import java.io.IOException;

/**
 * 作业信息
 * 后端接口
 * @author 
 * @email 
 * @date 2026-04-11 14:37:54
 */
@RestController
@RequestMapping("/zuoyexinxi")
public class ZuoyexinxiController {
    @Autowired
    private ZuoyexinxiService zuoyexinxiService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZuoyexinxiEntity zuoyexinxi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaoshi")) {
			zuoyexinxi.setGonghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        QueryWrapper<ZuoyexinxiEntity> ew = new QueryWrapper<ZuoyexinxiEntity>();


        //查询结果
		PageUtils page = zuoyexinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zuoyexinxi), params), params));
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
    public R list(@RequestParam Map<String, Object> params,ZuoyexinxiEntity zuoyexinxi,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date reversetimestart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date reversetimeend,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<ZuoyexinxiEntity> ew = new QueryWrapper<ZuoyexinxiEntity>();
        if(reversetimestart!=null) ew.ge("reversetime", reversetimestart);
        if(reversetimeend!=null) ew.le("reversetime", reversetimeend);

        //查询结果
		PageUtils page = zuoyexinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zuoyexinxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZuoyexinxiEntity zuoyexinxi){
       	QueryWrapper<ZuoyexinxiEntity> ew = new QueryWrapper<ZuoyexinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( zuoyexinxi, "zuoyexinxi"));
        return R.ok().put("data", zuoyexinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZuoyexinxiEntity zuoyexinxi){
        QueryWrapper< ZuoyexinxiEntity> ew = new QueryWrapper< ZuoyexinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( zuoyexinxi, "zuoyexinxi"));
		ZuoyexinxiView zuoyexinxiView =  zuoyexinxiService.selectView(ew);
		return R.ok("查询作业信息成功").put("data", zuoyexinxiView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZuoyexinxiEntity zuoyexinxi = zuoyexinxiService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(zuoyexinxi,deSens);
        return R.ok().put("data", zuoyexinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZuoyexinxiEntity zuoyexinxi = zuoyexinxiService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(zuoyexinxi,deSens);
        return R.ok().put("data", zuoyexinxi);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ZuoyexinxiEntity zuoyexinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zuoyexinxi);
        zuoyexinxiService.save(zuoyexinxi);
        return R.ok().put("data",zuoyexinxi.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ZuoyexinxiEntity zuoyexinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zuoyexinxi);
        zuoyexinxiService.save(zuoyexinxi);
        return R.ok().put("data",zuoyexinxi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ZuoyexinxiEntity zuoyexinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zuoyexinxi);
        //全部更新
        zuoyexinxiService.updateById(zuoyexinxi);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        zuoyexinxiService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }





    // hasAlipay:否




}
