package com.yb.dao;

import com.yb.entity.XuexijinduEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.XuexijinduVO;
import com.yb.entity.view.XuexijinduView;


/**
 * 学习进度
 * 
 * @author 
 * @email 
 * @date 2026-04-11 14:37:54
 */
public interface XuexijinduDao extends BaseMapper<XuexijinduEntity> {
	
	List<XuexijinduVO> selectListVO(@Param("ew") QueryWrapper<XuexijinduEntity> wrapper);
	
	XuexijinduVO selectVO(@Param("ew") QueryWrapper<XuexijinduEntity> wrapper);
	
	List<XuexijinduView> selectListView(@Param("ew") QueryWrapper<XuexijinduEntity> wrapper);

	List<XuexijinduView> selectListView(Page page,@Param("ew") QueryWrapper<XuexijinduEntity> wrapper);

	
	XuexijinduView selectView(@Param("ew") QueryWrapper<XuexijinduEntity> wrapper);
	

}
