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
import com.yb.utils.UserBasedCollaborativeFiltering;
import com.yb.algorithm.recommend.RecommendAlgorithmFactory;
import com.yb.entity.KechengxinxiEntity;
import com.yb.entity.view.KechengxinxiView;

import com.yb.service.KechengxinxiService;
import com.yb.utils.PageUtils;
import com.yb.utils.R;
import com.yb.utils.MPUtil;
import com.yb.utils.MapUtils;
import com.yb.utils.CommonUtil;
import java.io.IOException;
import com.yb.service.StoreupService;
import com.yb.entity.StoreupEntity;

/**
 * 课程信息
 * 后端接口
 * @author 
 * @email 
 * @date 2026-04-11 14:37:53
 */
@RestController
@RequestMapping("/kechengxinxi")
public class KechengxinxiController {
    @Autowired
    private KechengxinxiService kechengxinxiService;

    @Autowired
    private StoreupService storeupService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,KechengxinxiEntity kechengxinxi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaoshi")) {
			kechengxinxi.setGonghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        QueryWrapper<KechengxinxiEntity> ew = new QueryWrapper<KechengxinxiEntity>();


        //查询结果
		PageUtils page = kechengxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kechengxinxi), params), params));
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
    public R list(@RequestParam Map<String, Object> params,KechengxinxiEntity kechengxinxi,
                @RequestParam(required = false) Double feiyongstart,
                @RequestParam(required = false) Double feiyongend,
                @RequestParam(required = false) Double keshistart,
                @RequestParam(required = false) Double keshiend,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date clicktimestart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date clicktimeend,
                @RequestParam(required = false) Double clicknumstart,
                @RequestParam(required = false) Double clicknumend,
                @RequestParam(required = false) Double discussnumstart,
                @RequestParam(required = false) Double discussnumend,
                @RequestParam(required = false) Double totalscorestart,
                @RequestParam(required = false) Double totalscoreend,
                @RequestParam(required = false) Double storeupnumstart,
                @RequestParam(required = false) Double storeupnumend,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<KechengxinxiEntity> ew = new QueryWrapper<KechengxinxiEntity>();
        if(feiyongstart!=null) ew.ge("feiyong", feiyongstart);
        if(feiyongend!=null) ew.le("feiyong", feiyongend);
        if(keshistart!=null) ew.ge("keshi", keshistart);
        if(keshiend!=null) ew.le("keshi", keshiend);
        if(clicktimestart!=null) ew.ge("clicktime", clicktimestart);
        if(clicktimeend!=null) ew.le("clicktime", clicktimeend);
        if(clicknumstart!=null) ew.ge("clicknum", clicknumstart);
        if(clicknumend!=null) ew.le("clicknum", clicknumend);
        if(discussnumstart!=null) ew.ge("discussnum", discussnumstart);
        if(discussnumend!=null) ew.le("discussnum", discussnumend);
        if(totalscorestart!=null) ew.ge("totalscore", totalscorestart);
        if(totalscoreend!=null) ew.le("totalscore", totalscoreend);
        if(storeupnumstart!=null) ew.ge("storeupnum", storeupnumstart);
        if(storeupnumend!=null) ew.le("storeupnum", storeupnumend);

        //查询结果
		PageUtils page = kechengxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kechengxinxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( KechengxinxiEntity kechengxinxi){
       	QueryWrapper<KechengxinxiEntity> ew = new QueryWrapper<KechengxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( kechengxinxi, "kechengxinxi"));
        return R.ok().put("data", kechengxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(KechengxinxiEntity kechengxinxi){
        QueryWrapper< KechengxinxiEntity> ew = new QueryWrapper< KechengxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( kechengxinxi, "kechengxinxi"));
		KechengxinxiView kechengxinxiView =  kechengxinxiService.selectView(ew);
		return R.ok("查询课程信息成功").put("data", kechengxinxiView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        KechengxinxiEntity kechengxinxi = kechengxinxiService.getById(id);
        if(null==kechengxinxi.getClicknum()){
            kechengxinxi.setClicknum(0);
        }
		kechengxinxi.setClicknum(kechengxinxi.getClicknum()+1);
		kechengxinxiService.updateById(kechengxinxi);
        kechengxinxi = kechengxinxiService.selectView(new QueryWrapper<KechengxinxiEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(kechengxinxi,deSens);
        return R.ok().put("data", kechengxinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        KechengxinxiEntity kechengxinxi = kechengxinxiService.getById(id);
        if(null==kechengxinxi.getClicknum()){
            kechengxinxi.setClicknum(0);
        }
		kechengxinxi.setClicknum(kechengxinxi.getClicknum()+1);
		kechengxinxiService.updateById(kechengxinxi);
        kechengxinxi = kechengxinxiService.selectView(new QueryWrapper<KechengxinxiEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(kechengxinxi,deSens);
        return R.ok().put("data", kechengxinxi);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody KechengxinxiEntity kechengxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kechengxinxi);
        kechengxinxiService.save(kechengxinxi);
        return R.ok().put("data",kechengxinxi.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody KechengxinxiEntity kechengxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kechengxinxi);
        kechengxinxiService.save(kechengxinxi);
        return R.ok().put("data",kechengxinxi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody KechengxinxiEntity kechengxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kechengxinxi);
        //全部更新
        kechengxinxiService.updateById(kechengxinxi);
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<KechengxinxiEntity> list = new ArrayList<KechengxinxiEntity>();
        for(Long id : ids) {
            KechengxinxiEntity kechengxinxi = kechengxinxiService.getById(id);
            kechengxinxi.setSfsh(sfsh);
            kechengxinxi.setShhf(shhf);
            list.add(kechengxinxi);
        }
        kechengxinxiService.updateBatchById(list);
        return R.ok();
    }




    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        kechengxinxiService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }

	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,KechengxinxiEntity kechengxinxi, HttpServletRequest request,String pre){
        QueryWrapper<KechengxinxiEntity> ew = new QueryWrapper<KechengxinxiEntity>();
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
		params.put("sort", "clicknum");
        params.put("order", "desc");

		PageUtils page = kechengxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kechengxinxi), params), params));
        return R.ok().put("data", page);
    }


    /**
     * 协同算法（基于用户的协同算法：购买、收藏、关注）
     */
    @RequestMapping("/autoSort2")
    public R autoSort2(@RequestParam Map<String, Object> params,KechengxinxiEntity kechengxinxi, HttpServletRequest request){
        String userId = request.getSession().getAttribute("userId").toString();
        Integer limit = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit").toString());
        //用户行为数据
        List<RecommendAlgorithmFactory.UserBehavior> userBehaviors = new ArrayList<>();
        // 查询收藏/关注数据
        userBehaviors.addAll(storeupService.list(new QueryWrapper<StoreupEntity>().eq("type", 1 ).eq("tablename", "kechengxinxi")).stream().map(storeup -> {
            return new RecommendAlgorithmFactory.UserBehavior(storeup.getUserid(), storeup.getRefid());
        }).collect(Collectors.toList()));
        // 根据物品推荐用户
        // 用协 算法推荐物品id
        List<Long> recommendations = RecommendAlgorithmFactory.buildUserItemMatrix(userBehaviors).recommendItems(Long.parseLong(userId), limit);
        // 输出推荐结果
        System.out.println("Recommendations for " + userId + ":");
        recommendations.forEach(recommendation -> System.out.println(recommendation));
        QueryWrapper<KechengxinxiEntity> ew = new QueryWrapper<KechengxinxiEntity>();

        if(recommendations!=null && recommendations.size()>0) {
            ew.in("id", recommendations);
            ew.last("order by FIELD(id, "+String.join(",", recommendations.stream().map(String::valueOf).collect(Collectors.toList()))+")");
        }
        // 根据协同结果查询结果并返回
        PageUtils page = kechengxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kechengxinxi), params), params));
        List<KechengxinxiEntity> pageList = (List<KechengxinxiEntity>)page.getList();
        if(recommendations!=null && recommendations.size()>0 && pageList.size()<limit) {
            int toAddNum = limit-pageList.size();
            ew = new QueryWrapper<KechengxinxiEntity>();
            ew.notIn("id", recommendations);
            ew.orderByAsc("id");
            ew.last("limit "+toAddNum);
            pageList.addAll(kechengxinxiService.list(ew));
        } else if(pageList.size()>limit) {
            pageList = pageList.subList(0, limit);
        }
        page.setList(pageList);
        return R.ok().put("data", page);
    }


    // hasAlipay:否




}
