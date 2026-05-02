package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.DianzijiangyiEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.DianzijiangyiVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.DianzijiangyiView;


/**
 * 电子讲义
 *
 * @author 
 * @email 
 * @date 2026-04-11 14:37:54
 */
public interface DianzijiangyiService extends IService<DianzijiangyiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DianzijiangyiVO> selectListVO(QueryWrapper<DianzijiangyiEntity> wrapper);
   	
   	DianzijiangyiVO selectVO(@Param("ew") QueryWrapper<DianzijiangyiEntity> wrapper);
   	
   	List<DianzijiangyiView> selectListView(QueryWrapper<DianzijiangyiEntity> wrapper);
   	
   	DianzijiangyiView selectView(@Param("ew") QueryWrapper<DianzijiangyiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<DianzijiangyiEntity> wrapper);

   	

}

