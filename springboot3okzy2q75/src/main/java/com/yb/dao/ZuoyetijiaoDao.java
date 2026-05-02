package com.yb.dao;

import com.yb.entity.ZuoyetijiaoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.ZuoyetijiaoVO;
import com.yb.entity.view.ZuoyetijiaoView;


/**
 * 作业提交
 * 
 * @author 
 * @email 
 * @date 2026-04-11 14:37:54
 */
public interface ZuoyetijiaoDao extends BaseMapper<ZuoyetijiaoEntity> {
	
	List<ZuoyetijiaoVO> selectListVO(@Param("ew") QueryWrapper<ZuoyetijiaoEntity> wrapper);
	
	ZuoyetijiaoVO selectVO(@Param("ew") QueryWrapper<ZuoyetijiaoEntity> wrapper);
	
	List<ZuoyetijiaoView> selectListView(@Param("ew") QueryWrapper<ZuoyetijiaoEntity> wrapper);

	List<ZuoyetijiaoView> selectListView(Page page,@Param("ew") QueryWrapper<ZuoyetijiaoEntity> wrapper);

	
	ZuoyetijiaoView selectView(@Param("ew") QueryWrapper<ZuoyetijiaoEntity> wrapper);
	

}
