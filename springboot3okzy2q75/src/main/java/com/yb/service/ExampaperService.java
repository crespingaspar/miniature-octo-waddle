package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.ExampaperEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.ExampaperVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.ExampaperView;


/**
 * 在线测试
 *
 * @author 
 * @email 
 * @date 2026-04-11 14:37:55
 */
public interface ExampaperService extends IService<ExampaperEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ExampaperVO> selectListVO(QueryWrapper<ExampaperEntity> wrapper);
   	
   	ExampaperVO selectVO(@Param("ew") QueryWrapper<ExampaperEntity> wrapper);
   	
   	List<ExampaperView> selectListView(QueryWrapper<ExampaperEntity> wrapper);
   	
   	ExampaperView selectView(@Param("ew") QueryWrapper<ExampaperEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<ExampaperEntity> wrapper);

   	

}

