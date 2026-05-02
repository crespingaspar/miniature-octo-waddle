package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.KechengfenleiEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.KechengfenleiVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.KechengfenleiView;


/**
 * 课程分类
 *
 * @author 
 * @email 
 * @date 2026-04-11 14:37:53
 */
public interface KechengfenleiService extends IService<KechengfenleiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<KechengfenleiVO> selectListVO(QueryWrapper<KechengfenleiEntity> wrapper);
   	
   	KechengfenleiVO selectVO(@Param("ew") QueryWrapper<KechengfenleiEntity> wrapper);
   	
   	List<KechengfenleiView> selectListView(QueryWrapper<KechengfenleiEntity> wrapper);
   	
   	KechengfenleiView selectView(@Param("ew") QueryWrapper<KechengfenleiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<KechengfenleiEntity> wrapper);

   	

}

