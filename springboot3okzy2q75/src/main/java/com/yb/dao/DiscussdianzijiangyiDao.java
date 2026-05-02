package com.yb.dao;

import com.yb.entity.DiscussdianzijiangyiEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.DiscussdianzijiangyiVO;
import com.yb.entity.view.DiscussdianzijiangyiView;


/**
 * 电子讲义评论
 * 
 * @author 
 * @email 
 * @date 2026-04-11 14:37:56
 */
public interface DiscussdianzijiangyiDao extends BaseMapper<DiscussdianzijiangyiEntity> {
	
	List<DiscussdianzijiangyiVO> selectListVO(@Param("ew") QueryWrapper<DiscussdianzijiangyiEntity> wrapper);
	
	DiscussdianzijiangyiVO selectVO(@Param("ew") QueryWrapper<DiscussdianzijiangyiEntity> wrapper);
	
	List<DiscussdianzijiangyiView> selectListView(@Param("ew") QueryWrapper<DiscussdianzijiangyiEntity> wrapper);

	List<DiscussdianzijiangyiView> selectListView(Page page,@Param("ew") QueryWrapper<DiscussdianzijiangyiEntity> wrapper);

	
	DiscussdianzijiangyiView selectView(@Param("ew") QueryWrapper<DiscussdianzijiangyiEntity> wrapper);
	

}
