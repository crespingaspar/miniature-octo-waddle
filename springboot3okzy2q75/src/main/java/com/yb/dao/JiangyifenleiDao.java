package com.yb.dao;

import com.yb.entity.JiangyifenleiEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.JiangyifenleiVO;
import com.yb.entity.view.JiangyifenleiView;


/**
 * 讲义分类
 * 
 * @author 
 * @email 
 * @date 2026-04-11 14:37:54
 */
public interface JiangyifenleiDao extends BaseMapper<JiangyifenleiEntity> {
	
	List<JiangyifenleiVO> selectListVO(@Param("ew") QueryWrapper<JiangyifenleiEntity> wrapper);
	
	JiangyifenleiVO selectVO(@Param("ew") QueryWrapper<JiangyifenleiEntity> wrapper);
	
	List<JiangyifenleiView> selectListView(@Param("ew") QueryWrapper<JiangyifenleiEntity> wrapper);

	List<JiangyifenleiView> selectListView(Page page,@Param("ew") QueryWrapper<JiangyifenleiEntity> wrapper);

	
	JiangyifenleiView selectView(@Param("ew") QueryWrapper<JiangyifenleiEntity> wrapper);
	

}
