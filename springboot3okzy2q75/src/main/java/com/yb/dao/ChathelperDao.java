package com.yb.dao;

import com.yb.entity.ChathelperEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.ChathelperVO;
import com.yb.entity.view.ChathelperView;


/**
 * 聊天助手表
 * 
 * @author 
 * @email 
 * @date 2026-04-11 14:37:55
 */
public interface ChathelperDao extends BaseMapper<ChathelperEntity> {
	
	List<ChathelperVO> selectListVO(@Param("ew") QueryWrapper<ChathelperEntity> wrapper);
	
	ChathelperVO selectVO(@Param("ew") QueryWrapper<ChathelperEntity> wrapper);
	
	List<ChathelperView> selectListView(@Param("ew") QueryWrapper<ChathelperEntity> wrapper);

	List<ChathelperView> selectListView(Page page,@Param("ew") QueryWrapper<ChathelperEntity> wrapper);

	
	ChathelperView selectView(@Param("ew") QueryWrapper<ChathelperEntity> wrapper);
	

}
