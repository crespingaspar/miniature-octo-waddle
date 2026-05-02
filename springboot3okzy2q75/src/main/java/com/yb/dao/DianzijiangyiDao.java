package com.yb.dao;

import com.yb.entity.DianzijiangyiEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.DianzijiangyiVO;
import com.yb.entity.view.DianzijiangyiView;


/**
 * 电子讲义
 * 
 * @author 
 * @email 
 * @date 2026-04-11 14:37:54
 */
public interface DianzijiangyiDao extends BaseMapper<DianzijiangyiEntity> {
	
	List<DianzijiangyiVO> selectListVO(@Param("ew") QueryWrapper<DianzijiangyiEntity> wrapper);
	
	DianzijiangyiVO selectVO(@Param("ew") QueryWrapper<DianzijiangyiEntity> wrapper);
	
	List<DianzijiangyiView> selectListView(@Param("ew") QueryWrapper<DianzijiangyiEntity> wrapper);

	List<DianzijiangyiView> selectListView(Page page,@Param("ew") QueryWrapper<DianzijiangyiEntity> wrapper);

	
	DianzijiangyiView selectView(@Param("ew") QueryWrapper<DianzijiangyiEntity> wrapper);
	

}
