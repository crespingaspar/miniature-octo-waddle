package com.yb.dao;

import com.yb.entity.ExampaperEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.ExampaperVO;
import com.yb.entity.view.ExampaperView;


/**
 * 在线测试
 * 
 * @author 
 * @email 
 * @date 2026-04-11 14:37:55
 */
public interface ExampaperDao extends BaseMapper<ExampaperEntity> {
	
	List<ExampaperVO> selectListVO(@Param("ew") QueryWrapper<ExampaperEntity> wrapper);
	
	ExampaperVO selectVO(@Param("ew") QueryWrapper<ExampaperEntity> wrapper);
	
	List<ExampaperView> selectListView(@Param("ew") QueryWrapper<ExampaperEntity> wrapper);

	List<ExampaperView> selectListView(Page page,@Param("ew") QueryWrapper<ExampaperEntity> wrapper);

	
	ExampaperView selectView(@Param("ew") QueryWrapper<ExampaperEntity> wrapper);
	

}
