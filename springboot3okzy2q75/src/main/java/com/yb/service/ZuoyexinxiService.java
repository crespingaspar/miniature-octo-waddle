package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.ZuoyexinxiEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.ZuoyexinxiVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.ZuoyexinxiView;


/**
 * 作业信息
 *
 * @author 
 * @email 
 * @date 2026-04-11 14:37:54
 */
public interface ZuoyexinxiService extends IService<ZuoyexinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ZuoyexinxiVO> selectListVO(QueryWrapper<ZuoyexinxiEntity> wrapper);
   	
   	ZuoyexinxiVO selectVO(@Param("ew") QueryWrapper<ZuoyexinxiEntity> wrapper);
   	
   	List<ZuoyexinxiView> selectListView(QueryWrapper<ZuoyexinxiEntity> wrapper);
   	
   	ZuoyexinxiView selectView(@Param("ew") QueryWrapper<ZuoyexinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<ZuoyexinxiEntity> wrapper);

   	

}

