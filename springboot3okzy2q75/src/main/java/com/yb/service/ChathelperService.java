package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.ChathelperEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.ChathelperVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.ChathelperView;


/**
 * 聊天助手表
 *
 * @author 
 * @email 
 * @date 2026-04-11 14:37:55
 */
public interface ChathelperService extends IService<ChathelperEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ChathelperVO> selectListVO(QueryWrapper<ChathelperEntity> wrapper);
   	
   	ChathelperVO selectVO(@Param("ew") QueryWrapper<ChathelperEntity> wrapper);
   	
   	List<ChathelperView> selectListView(QueryWrapper<ChathelperEntity> wrapper);
   	
   	ChathelperView selectView(@Param("ew") QueryWrapper<ChathelperEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<ChathelperEntity> wrapper);

   	

}

