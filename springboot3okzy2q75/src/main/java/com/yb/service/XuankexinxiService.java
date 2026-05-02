package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.XuankexinxiEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.XuankexinxiVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.XuankexinxiView;


/**
 * 选课信息
 *
 * @author 
 * @email 
 * @date 2026-04-11 14:37:54
 */
public interface XuankexinxiService extends IService<XuankexinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<XuankexinxiVO> selectListVO(QueryWrapper<XuankexinxiEntity> wrapper);
   	
   	XuankexinxiVO selectVO(@Param("ew") QueryWrapper<XuankexinxiEntity> wrapper);
   	
   	List<XuankexinxiView> selectListView(QueryWrapper<XuankexinxiEntity> wrapper);
   	
   	XuankexinxiView selectView(@Param("ew") QueryWrapper<XuankexinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<XuankexinxiEntity> wrapper);

   	

}

