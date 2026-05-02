package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.JiangyifenleiEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.JiangyifenleiVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.JiangyifenleiView;


/**
 * 讲义分类
 *
 * @author 
 * @email 
 * @date 2026-04-11 14:37:54
 */
public interface JiangyifenleiService extends IService<JiangyifenleiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JiangyifenleiVO> selectListVO(QueryWrapper<JiangyifenleiEntity> wrapper);
   	
   	JiangyifenleiVO selectVO(@Param("ew") QueryWrapper<JiangyifenleiEntity> wrapper);
   	
   	List<JiangyifenleiView> selectListView(QueryWrapper<JiangyifenleiEntity> wrapper);
   	
   	JiangyifenleiView selectView(@Param("ew") QueryWrapper<JiangyifenleiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<JiangyifenleiEntity> wrapper);

   	

}

