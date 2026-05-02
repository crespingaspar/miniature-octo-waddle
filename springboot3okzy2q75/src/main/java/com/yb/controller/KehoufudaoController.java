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
import com.yb.entity.KehoufudaoEntity;
import com.yb.entity.view.KehoufudaoView;

import com.yb.service.KehoufudaoService;
import com.yb.utils.PageUtils;
import com.yb.utils.R;
import com.yb.utils.MPUtil;
import com.yb.utils.MapUtils;
import com.yb.utils.CommonUtil;
import java.io.IOException;

/**
 * 课后辅导
 * 后端接口
 * @author 
 * @email 
 * @date 2026-04-11 14:37:55
 */
@RestController
@RequestMapping("/kehoufudao")
public class KehoufudaoController {
    @Autowired
    private KehoufudaoService kehoufudaoService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,KehoufudaoEntity kehoufudao,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("xuesheng")) {
			kehoufudao.setXuehao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("jiaoshi")) {
			kehoufudao.setGonghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        QueryWrapper<KehoufudaoEntity> ew = new QueryWrapper<KehoufudaoEntity>();


        //查询结果
		PageUtils page = kehoufudaoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kehoufudao), params), params));
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
    public R list(@RequestParam Map<String, Object> params,KehoufudaoEntity kehoufudao,
                @RequestParam(required = false) Double xuexixiaoguostart,
                @RequestParam(required = false) Double xuexixiaoguoend,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date fudaoshijianstart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date fudaoshijianend,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<KehoufudaoEntity> ew = new QueryWrapper<KehoufudaoEntity>();
        if(xuexixiaoguostart!=null) ew.ge("xuexixiaoguo", xuexixiaoguostart);
        if(xuexixiaoguoend!=null) ew.le("xuexixiaoguo", xuexixiaoguoend);
        if(fudaoshijianstart!=null) ew.ge("fudaoshijian", fudaoshijianstart);
        if(fudaoshijianend!=null) ew.le("fudaoshijian", fudaoshijianend);

        //查询结果
		PageUtils page = kehoufudaoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kehoufudao), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( KehoufudaoEntity kehoufudao){
       	QueryWrapper<KehoufudaoEntity> ew = new QueryWrapper<KehoufudaoEntity>();
      	ew.allEq(MPUtil.allEQMapPre( kehoufudao, "kehoufudao"));
        return R.ok().put("data", kehoufudaoService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(KehoufudaoEntity kehoufudao){
        QueryWrapper< KehoufudaoEntity> ew = new QueryWrapper< KehoufudaoEntity>();
 		ew.allEq(MPUtil.allEQMapPre( kehoufudao, "kehoufudao"));
		KehoufudaoView kehoufudaoView =  kehoufudaoService.selectView(ew);
		return R.ok("查询课后辅导成功").put("data", kehoufudaoView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        KehoufudaoEntity kehoufudao = kehoufudaoService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(kehoufudao,deSens);
        return R.ok().put("data", kehoufudao);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        KehoufudaoEntity kehoufudao = kehoufudaoService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(kehoufudao,deSens);
        return R.ok().put("data", kehoufudao);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody KehoufudaoEntity kehoufudao, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kehoufudao);
        kehoufudaoService.save(kehoufudao);
        return R.ok().put("data",kehoufudao.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody KehoufudaoEntity kehoufudao, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kehoufudao);
        kehoufudaoService.save(kehoufudao);
        return R.ok().put("data",kehoufudao.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody KehoufudaoEntity kehoufudao, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kehoufudao);
        //全部更新
        kehoufudaoService.updateById(kehoufudao);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        kehoufudaoService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }





    // hasAlipay:否




}
