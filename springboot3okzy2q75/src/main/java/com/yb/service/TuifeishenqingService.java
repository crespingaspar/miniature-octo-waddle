package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.TuifeishenqingEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.TuifeishenqingVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.TuifeishenqingView;


/**
 * 退费申请
 *
 * @author 
 * @email 
 * @date 2026-04-11 14:37:54
 */
public interface TuifeishenqingService extends IService<TuifeishenqingEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<TuifeishenqingVO> selectListVO(QueryWrapper<TuifeishenqingEntity> wrapper);
   	
   	TuifeishenqingVO selectVO(@Param("ew") QueryWrapper<TuifeishenqingEntity> wrapper);
   	
   	List<TuifeishenqingView> selectListView(QueryWrapper<TuifeishenqingEntity> wrapper);
   	
   	TuifeishenqingView selectView(@Param("ew") QueryWrapper<TuifeishenqingEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<TuifeishenqingEntity> wrapper);

   	

}

