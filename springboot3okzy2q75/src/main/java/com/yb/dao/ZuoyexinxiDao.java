package com.yb.dao;

import com.yb.entity.ZuoyexinxiEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.ZuoyexinxiVO;
import com.yb.entity.view.ZuoyexinxiView;


/**
 * 作业信息
 * 
 * @author 
 * @email 
 * @date 2026-04-11 14:37:54
 */
public interface ZuoyexinxiDao extends BaseMapper<ZuoyexinxiEntity> {
	
	List<ZuoyexinxiVO> selectListVO(@Param("ew") QueryWrapper<ZuoyexinxiEntity> wrapper);
	
	ZuoyexinxiVO selectVO(@Param("ew") QueryWrapper<ZuoyexinxiEntity> wrapper);
	
	List<ZuoyexinxiView> selectListView(@Param("ew") QueryWrapper<ZuoyexinxiEntity> wrapper);

	List<ZuoyexinxiView> selectListView(Page page,@Param("ew") QueryWrapper<ZuoyexinxiEntity> wrapper);

	
	ZuoyexinxiView selectView(@Param("ew") QueryWrapper<ZuoyexinxiEntity> wrapper);
	

}
