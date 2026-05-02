package com.yb.dao;

import com.yb.entity.XuankexinxiEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.XuankexinxiVO;
import com.yb.entity.view.XuankexinxiView;


/**
 * 选课信息
 * 
 * @author 
 * @email 
 * @date 2026-04-11 14:37:54
 */
public interface XuankexinxiDao extends BaseMapper<XuankexinxiEntity> {
	
	List<XuankexinxiVO> selectListVO(@Param("ew") QueryWrapper<XuankexinxiEntity> wrapper);
	
	XuankexinxiVO selectVO(@Param("ew") QueryWrapper<XuankexinxiEntity> wrapper);
	
	List<XuankexinxiView> selectListView(@Param("ew") QueryWrapper<XuankexinxiEntity> wrapper);

	List<XuankexinxiView> selectListView(Page page,@Param("ew") QueryWrapper<XuankexinxiEntity> wrapper);

	
	XuankexinxiView selectView(@Param("ew") QueryWrapper<XuankexinxiEntity> wrapper);
	

}
