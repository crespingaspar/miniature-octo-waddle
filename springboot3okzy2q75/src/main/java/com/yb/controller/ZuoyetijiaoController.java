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
import com.yb.entity.ZuoyetijiaoEntity;
import com.yb.entity.view.ZuoyetijiaoView;

import com.yb.service.ZuoyetijiaoService;
import com.yb.utils.PageUtils;
import com.yb.utils.R;
import com.yb.utils.MPUtil;
import com.yb.utils.MapUtils;
import com.yb.utils.CommonUtil;
import java.io.IOException;

/**
 * 作业提交
 * 后端接口
 * @author 
 * @email 
 * @date 2026-04-11 14:37:54
 */
@RestController
@RequestMapping("/zuoyetijiao")
public class ZuoyetijiaoController {
    @Autowired
    private ZuoyetijiaoService zuoyetijiaoService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZuoyetijiaoEntity zuoyetijiao,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaoshi")) {
			zuoyetijiao.setGonghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("xuesheng")) {
			zuoyetijiao.setXuehao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        QueryWrapper<ZuoyetijiaoEntity> ew = new QueryWrapper<ZuoyetijiaoEntity>();


        //查询结果
		PageUtils page = zuoyetijiaoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zuoyetijiao), params), params));
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
    public R list(@RequestParam Map<String, Object> params,ZuoyetijiaoEntity zuoyetijiao,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date tijiaoshijianstart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date tijiaoshijianend,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<ZuoyetijiaoEntity> ew = new QueryWrapper<ZuoyetijiaoEntity>();
        if(tijiaoshijianstart!=null) ew.ge("tijiaoshijian", tijiaoshijianstart);
        if(tijiaoshijianend!=null) ew.le("tijiaoshijian", tijiaoshijianend);

        //查询结果
		PageUtils page = zuoyetijiaoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zuoyetijiao), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZuoyetijiaoEntity zuoyetijiao){
       	QueryWrapper<ZuoyetijiaoEntity> ew = new QueryWrapper<ZuoyetijiaoEntity>();
      	ew.allEq(MPUtil.allEQMapPre( zuoyetijiao, "zuoyetijiao"));
        return R.ok().put("data", zuoyetijiaoService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZuoyetijiaoEntity zuoyetijiao){
        QueryWrapper< ZuoyetijiaoEntity> ew = new QueryWrapper< ZuoyetijiaoEntity>();
 		ew.allEq(MPUtil.allEQMapPre( zuoyetijiao, "zuoyetijiao"));
		ZuoyetijiaoView zuoyetijiaoView =  zuoyetijiaoService.selectView(ew);
		return R.ok("查询作业提交成功").put("data", zuoyetijiaoView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZuoyetijiaoEntity zuoyetijiao = zuoyetijiaoService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(zuoyetijiao,deSens);
        return R.ok().put("data", zuoyetijiao);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZuoyetijiaoEntity zuoyetijiao = zuoyetijiaoService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(zuoyetijiao,deSens);
        return R.ok().put("data", zuoyetijiao);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ZuoyetijiaoEntity zuoyetijiao, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zuoyetijiao);
        zuoyetijiaoService.save(zuoyetijiao);
        return R.ok().put("data",zuoyetijiao.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ZuoyetijiaoEntity zuoyetijiao, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zuoyetijiao);
        zuoyetijiaoService.save(zuoyetijiao);
        return R.ok().put("data",zuoyetijiao.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ZuoyetijiaoEntity zuoyetijiao, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zuoyetijiao);
        //全部更新
        zuoyetijiaoService.updateById(zuoyetijiao);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        zuoyetijiaoService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }





    // hasAlipay:否




}
