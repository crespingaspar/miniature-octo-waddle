package com.yb.dao;

import com.yb.entity.ZuoyepigaiEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.ZuoyepigaiVO;
import com.yb.entity.view.ZuoyepigaiView;


/**
 * 作业批改
 * 
 * @author 
 * @email 
 * @date 2026-04-11 14:37:54
 */
public interface ZuoyepigaiDao extends BaseMapper<ZuoyepigaiEntity> {
	
	List<ZuoyepigaiVO> selectListVO(@Param("ew") QueryWrapper<ZuoyepigaiEntity> wrapper);
	
	ZuoyepigaiVO selectVO(@Param("ew") QueryWrapper<ZuoyepigaiEntity> wrapper);
	
	List<ZuoyepigaiView> selectListView(@Param("ew") QueryWrapper<ZuoyepigaiEntity> wrapper);

	List<ZuoyepigaiView> selectListView(Page page,@Param("ew") QueryWrapper<ZuoyepigaiEntity> wrapper);

	
	ZuoyepigaiView selectView(@Param("ew") QueryWrapper<ZuoyepigaiEntity> wrapper);
	

}
