package com.yb.dao;

import com.yb.entity.DiscusskechengxinxiEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.DiscusskechengxinxiVO;
import com.yb.entity.view.DiscusskechengxinxiView;


/**
 * 课程信息评论
 * 
 * @author 
 * @email 
 * @date 2026-04-11 14:37:56
 */
public interface DiscusskechengxinxiDao extends BaseMapper<DiscusskechengxinxiEntity> {
	
	List<DiscusskechengxinxiVO> selectListVO(@Param("ew") QueryWrapper<DiscusskechengxinxiEntity> wrapper);
	
	DiscusskechengxinxiVO selectVO(@Param("ew") QueryWrapper<DiscusskechengxinxiEntity> wrapper);
	
	List<DiscusskechengxinxiView> selectListView(@Param("ew") QueryWrapper<DiscusskechengxinxiEntity> wrapper);

	List<DiscusskechengxinxiView> selectListView(Page page,@Param("ew") QueryWrapper<DiscusskechengxinxiEntity> wrapper);

	
	DiscusskechengxinxiView selectView(@Param("ew") QueryWrapper<DiscusskechengxinxiEntity> wrapper);
	

}
