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
import com.yb.entity.XuankexinxiEntity;
import com.yb.entity.view.XuankexinxiView;

import com.yb.service.XuankexinxiService;
import com.yb.utils.PageUtils;
import com.yb.utils.R;
import com.yb.utils.MPUtil;
import com.yb.utils.MapUtils;
import com.yb.utils.CommonUtil;
import java.io.IOException;

/**
 * 选课信息
 * 后端接口
 * @author 
 * @email 
 * @date 2026-04-11 14:37:54
 */
@RestController
@RequestMapping("/xuankexinxi")
public class XuankexinxiController {
    @Autowired
    private XuankexinxiService xuankexinxiService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,XuankexinxiEntity xuankexinxi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("xuesheng")) {
			xuankexinxi.setXuehao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("jiaoshi")) {
			xuankexinxi.setGonghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        QueryWrapper<XuankexinxiEntity> ew = new QueryWrapper<XuankexinxiEntity>();


        //查询结果
		PageUtils page = xuankexinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xuankexinxi), params), params));
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
    public R list(@RequestParam Map<String, Object> params,XuankexinxiEntity xuankexinxi,
                @RequestParam(required = false) Double keshistart,
                @RequestParam(required = false) Double keshiend,
                @RequestParam(required = false) Double feiyongstart,
                @RequestParam(required = false) Double feiyongend,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date xuankeshijianstart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date xuankeshijianend,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<XuankexinxiEntity> ew = new QueryWrapper<XuankexinxiEntity>();
        if(keshistart!=null) ew.ge("keshi", keshistart);
        if(keshiend!=null) ew.le("keshi", keshiend);
        if(feiyongstart!=null) ew.ge("feiyong", feiyongstart);
        if(feiyongend!=null) ew.le("feiyong", feiyongend);
        if(xuankeshijianstart!=null) ew.ge("xuankeshijian", xuankeshijianstart);
        if(xuankeshijianend!=null) ew.le("xuankeshijian", xuankeshijianend);

        //查询结果
		PageUtils page = xuankexinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xuankexinxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( XuankexinxiEntity xuankexinxi){
       	QueryWrapper<XuankexinxiEntity> ew = new QueryWrapper<XuankexinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( xuankexinxi, "xuankexinxi"));
        return R.ok().put("data", xuankexinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(XuankexinxiEntity xuankexinxi){
        QueryWrapper< XuankexinxiEntity> ew = new QueryWrapper< XuankexinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( xuankexinxi, "xuankexinxi"));
		XuankexinxiView xuankexinxiView =  xuankexinxiService.selectView(ew);
		return R.ok("查询选课信息成功").put("data", xuankexinxiView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        XuankexinxiEntity xuankexinxi = xuankexinxiService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(xuankexinxi,deSens);
        return R.ok().put("data", xuankexinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        XuankexinxiEntity xuankexinxi = xuankexinxiService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(xuankexinxi,deSens);
        return R.ok().put("data", xuankexinxi);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody XuankexinxiEntity xuankexinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xuankexinxi);
        xuankexinxiService.save(xuankexinxi);
        return R.ok().put("data",xuankexinxi.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody XuankexinxiEntity xuankexinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xuankexinxi);
        xuankexinxiService.save(xuankexinxi);
        return R.ok().put("data",xuankexinxi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody XuankexinxiEntity xuankexinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xuankexinxi);
        //全部更新
        xuankexinxiService.updateById(xuankexinxi);
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<XuankexinxiEntity> list = new ArrayList<XuankexinxiEntity>();
        for(Long id : ids) {
            XuankexinxiEntity xuankexinxi = xuankexinxiService.getById(id);
            xuankexinxi.setSfsh(sfsh);
            xuankexinxi.setShhf(shhf);
            list.add(xuankexinxi);
        }
        xuankexinxiService.updateBatchById(list);
        return R.ok();
    }




    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        xuankexinxiService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }





    // hasAlipay:否




}
