package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.DiscusskechengxinxiEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.DiscusskechengxinxiVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.DiscusskechengxinxiView;


/**
 * 课程信息评论
 *
 * @author 
 * @email 
 * @date 2026-04-11 14:37:56
 */
public interface DiscusskechengxinxiService extends IService<DiscusskechengxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscusskechengxinxiVO> selectListVO(QueryWrapper<DiscusskechengxinxiEntity> wrapper);
   	
   	DiscusskechengxinxiVO selectVO(@Param("ew") QueryWrapper<DiscusskechengxinxiEntity> wrapper);
   	
   	List<DiscusskechengxinxiView> selectListView(QueryWrapper<DiscusskechengxinxiEntity> wrapper);
   	
   	DiscusskechengxinxiView selectView(@Param("ew") QueryWrapper<DiscusskechengxinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<DiscusskechengxinxiEntity> wrapper);

   	

}

