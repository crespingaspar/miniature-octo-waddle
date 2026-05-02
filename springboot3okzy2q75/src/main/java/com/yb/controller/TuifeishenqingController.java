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
import com.yb.entity.TuifeishenqingEntity;
import com.yb.entity.view.TuifeishenqingView;

import com.yb.service.TuifeishenqingService;
import com.yb.utils.PageUtils;
import com.yb.utils.R;
import com.yb.utils.MPUtil;
import com.yb.utils.MapUtils;
import com.yb.utils.CommonUtil;
import java.io.IOException;

/**
 * 退费申请
 * 后端接口
 * @author 
 * @email 
 * @date 2026-04-11 14:37:54
 */
@RestController
@RequestMapping("/tuifeishenqing")
public class TuifeishenqingController {
    @Autowired
    private TuifeishenqingService tuifeishenqingService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,TuifeishenqingEntity tuifeishenqing,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("xuesheng")) {
			tuifeishenqing.setXuehao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("jiaoshi")) {
			tuifeishenqing.setGonghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        QueryWrapper<TuifeishenqingEntity> ew = new QueryWrapper<TuifeishenqingEntity>();


        //查询结果
		PageUtils page = tuifeishenqingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, tuifeishenqing), params), params));
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
    public R list(@RequestParam Map<String, Object> params,TuifeishenqingEntity tuifeishenqing,
                @RequestParam(required = false) Double feiyongstart,
                @RequestParam(required = false) Double feiyongend,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date shenqingshijianstart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date shenqingshijianend,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<TuifeishenqingEntity> ew = new QueryWrapper<TuifeishenqingEntity>();
        if(feiyongstart!=null) ew.ge("feiyong", feiyongstart);
        if(feiyongend!=null) ew.le("feiyong", feiyongend);
        if(shenqingshijianstart!=null) ew.ge("shenqingshijian", shenqingshijianstart);
        if(shenqingshijianend!=null) ew.le("shenqingshijian", shenqingshijianend);

        //查询结果
		PageUtils page = tuifeishenqingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, tuifeishenqing), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( TuifeishenqingEntity tuifeishenqing){
       	QueryWrapper<TuifeishenqingEntity> ew = new QueryWrapper<TuifeishenqingEntity>();
      	ew.allEq(MPUtil.allEQMapPre( tuifeishenqing, "tuifeishenqing"));
        return R.ok().put("data", tuifeishenqingService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(TuifeishenqingEntity tuifeishenqing){
        QueryWrapper< TuifeishenqingEntity> ew = new QueryWrapper< TuifeishenqingEntity>();
 		ew.allEq(MPUtil.allEQMapPre( tuifeishenqing, "tuifeishenqing"));
		TuifeishenqingView tuifeishenqingView =  tuifeishenqingService.selectView(ew);
		return R.ok("查询退费申请成功").put("data", tuifeishenqingView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        TuifeishenqingEntity tuifeishenqing = tuifeishenqingService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(tuifeishenqing,deSens);
        return R.ok().put("data", tuifeishenqing);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        TuifeishenqingEntity tuifeishenqing = tuifeishenqingService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(tuifeishenqing,deSens);
        return R.ok().put("data", tuifeishenqing);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody TuifeishenqingEntity tuifeishenqing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(tuifeishenqing);
        tuifeishenqingService.save(tuifeishenqing);
        return R.ok().put("data",tuifeishenqing.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody TuifeishenqingEntity tuifeishenqing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(tuifeishenqing);
        tuifeishenqingService.save(tuifeishenqing);
        return R.ok().put("data",tuifeishenqing.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody TuifeishenqingEntity tuifeishenqing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(tuifeishenqing);
        //全部更新
        tuifeishenqingService.updateById(tuifeishenqing);
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<TuifeishenqingEntity> list = new ArrayList<TuifeishenqingEntity>();
        for(Long id : ids) {
            TuifeishenqingEntity tuifeishenqing = tuifeishenqingService.getById(id);
            tuifeishenqing.setSfsh(sfsh);
            tuifeishenqing.setShhf(shhf);
            list.add(tuifeishenqing);
        }
        tuifeishenqingService.updateBatchById(list);
        return R.ok();
    }




    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        tuifeishenqingService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }





    // hasAlipay:否




}
