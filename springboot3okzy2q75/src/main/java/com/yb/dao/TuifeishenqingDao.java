package com.yb.dao;

import com.yb.entity.TuifeishenqingEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.TuifeishenqingVO;
import com.yb.entity.view.TuifeishenqingView;


/**
 * 退费申请
 * 
 * @author 
 * @email 
 * @date 2026-04-11 14:37:54
 */
public interface TuifeishenqingDao extends BaseMapper<TuifeishenqingEntity> {
	
	List<TuifeishenqingVO> selectListVO(@Param("ew") QueryWrapper<TuifeishenqingEntity> wrapper);
	
	TuifeishenqingVO selectVO(@Param("ew") QueryWrapper<TuifeishenqingEntity> wrapper);
	
	List<TuifeishenqingView> selectListView(@Param("ew") QueryWrapper<TuifeishenqingEntity> wrapper);

	List<TuifeishenqingView> selectListView(Page page,@Param("ew") QueryWrapper<TuifeishenqingEntity> wrapper);

	
	TuifeishenqingView selectView(@Param("ew") QueryWrapper<TuifeishenqingEntity> wrapper);
	

}
