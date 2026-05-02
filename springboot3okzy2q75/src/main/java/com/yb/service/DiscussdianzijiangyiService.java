package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.DiscussdianzijiangyiEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.DiscussdianzijiangyiVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.DiscussdianzijiangyiView;


/**
 * 电子讲义评论
 *
 * @author 
 * @email 
 * @date 2026-04-11 14:37:56
 */
public interface DiscussdianzijiangyiService extends IService<DiscussdianzijiangyiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussdianzijiangyiVO> selectListVO(QueryWrapper<DiscussdianzijiangyiEntity> wrapper);
   	
   	DiscussdianzijiangyiVO selectVO(@Param("ew") QueryWrapper<DiscussdianzijiangyiEntity> wrapper);
   	
   	List<DiscussdianzijiangyiView> selectListView(QueryWrapper<DiscussdianzijiangyiEntity> wrapper);
   	
   	DiscussdianzijiangyiView selectView(@Param("ew") QueryWrapper<DiscussdianzijiangyiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<DiscussdianzijiangyiEntity> wrapper);

   	

}

