package com.yb.dao;

import com.yb.entity.TousufankuiEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.TousufankuiVO;
import com.yb.entity.view.TousufankuiView;


/**
 * 投诉反馈
 * 
 * @author 
 * @email 
 * @date 2026-04-11 14:37:55
 */
public interface TousufankuiDao extends BaseMapper<TousufankuiEntity> {
	
	List<TousufankuiVO> selectListVO(@Param("ew") QueryWrapper<TousufankuiEntity> wrapper);
	
	TousufankuiVO selectVO(@Param("ew") QueryWrapper<TousufankuiEntity> wrapper);
	
	List<TousufankuiView> selectListView(@Param("ew") QueryWrapper<TousufankuiEntity> wrapper);

	List<TousufankuiView> selectListView(Page page,@Param("ew") QueryWrapper<TousufankuiEntity> wrapper);

	
	TousufankuiView selectView(@Param("ew") QueryWrapper<TousufankuiEntity> wrapper);
	

}
