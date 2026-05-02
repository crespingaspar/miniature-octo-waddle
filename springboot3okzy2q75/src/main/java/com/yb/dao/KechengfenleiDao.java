package com.yb.dao;

import com.yb.entity.KechengfenleiEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.KechengfenleiVO;
import com.yb.entity.view.KechengfenleiView;


/**
 * 课程分类
 * 
 * @author 
 * @email 
 * @date 2026-04-11 14:37:53
 */
public interface KechengfenleiDao extends BaseMapper<KechengfenleiEntity> {
	
	List<KechengfenleiVO> selectListVO(@Param("ew") QueryWrapper<KechengfenleiEntity> wrapper);
	
	KechengfenleiVO selectVO(@Param("ew") QueryWrapper<KechengfenleiEntity> wrapper);
	
	List<KechengfenleiView> selectListView(@Param("ew") QueryWrapper<KechengfenleiEntity> wrapper);

	List<KechengfenleiView> selectListView(Page page,@Param("ew") QueryWrapper<KechengfenleiEntity> wrapper);

	
	KechengfenleiView selectView(@Param("ew") QueryWrapper<KechengfenleiEntity> wrapper);
	

}
