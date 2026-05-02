package com.yb.dao;

import com.yb.entity.KehoufudaoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.KehoufudaoVO;
import com.yb.entity.view.KehoufudaoView;


/**
 * 课后辅导
 * 
 * @author 
 * @email 
 * @date 2026-04-11 14:37:55
 */
public interface KehoufudaoDao extends BaseMapper<KehoufudaoEntity> {
	
	List<KehoufudaoVO> selectListVO(@Param("ew") QueryWrapper<KehoufudaoEntity> wrapper);
	
	KehoufudaoVO selectVO(@Param("ew") QueryWrapper<KehoufudaoEntity> wrapper);
	
	List<KehoufudaoView> selectListView(@Param("ew") QueryWrapper<KehoufudaoEntity> wrapper);

	List<KehoufudaoView> selectListView(Page page,@Param("ew") QueryWrapper<KehoufudaoEntity> wrapper);

	
	KehoufudaoView selectView(@Param("ew") QueryWrapper<KehoufudaoEntity> wrapper);
	

}
