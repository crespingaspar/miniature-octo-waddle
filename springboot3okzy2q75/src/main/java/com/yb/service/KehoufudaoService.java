package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.KehoufudaoEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.KehoufudaoVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.KehoufudaoView;


/**
 * 课后辅导
 *
 * @author 
 * @email 
 * @date 2026-04-11 14:37:55
 */
public interface KehoufudaoService extends IService<KehoufudaoEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<KehoufudaoVO> selectListVO(QueryWrapper<KehoufudaoEntity> wrapper);
   	
   	KehoufudaoVO selectVO(@Param("ew") QueryWrapper<KehoufudaoEntity> wrapper);
   	
   	List<KehoufudaoView> selectListView(QueryWrapper<KehoufudaoEntity> wrapper);
   	
   	KehoufudaoView selectView(@Param("ew") QueryWrapper<KehoufudaoEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<KehoufudaoEntity> wrapper);

   	

}

