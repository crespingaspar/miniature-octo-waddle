package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.ZuoyepigaiEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.ZuoyepigaiVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.ZuoyepigaiView;


/**
 * 作业批改
 *
 * @author 
 * @email 
 * @date 2026-04-11 14:37:54
 */
public interface ZuoyepigaiService extends IService<ZuoyepigaiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ZuoyepigaiVO> selectListVO(QueryWrapper<ZuoyepigaiEntity> wrapper);
   	
   	ZuoyepigaiVO selectVO(@Param("ew") QueryWrapper<ZuoyepigaiEntity> wrapper);
   	
   	List<ZuoyepigaiView> selectListView(QueryWrapper<ZuoyepigaiEntity> wrapper);
   	
   	ZuoyepigaiView selectView(@Param("ew") QueryWrapper<ZuoyepigaiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<ZuoyepigaiEntity> wrapper);

   	

}

