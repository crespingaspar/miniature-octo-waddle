package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.NewsEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.NewsVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.NewsView;


/**
 * 公告资讯
 *
 * @author 
 * @email 
 * @date 2026-04-11 14:37:55
 */
public interface NewsService extends IService<NewsEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<NewsVO> selectListVO(QueryWrapper<NewsEntity> wrapper);
   	
   	NewsVO selectVO(@Param("ew") QueryWrapper<NewsEntity> wrapper);
   	
   	List<NewsView> selectListView(QueryWrapper<NewsEntity> wrapper);
   	
   	NewsView selectView(@Param("ew") QueryWrapper<NewsEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<NewsEntity> wrapper);

   	

}

