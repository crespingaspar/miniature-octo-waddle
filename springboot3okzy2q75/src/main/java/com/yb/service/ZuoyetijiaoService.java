package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.ZuoyetijiaoEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.ZuoyetijiaoVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.ZuoyetijiaoView;


/**
 * 作业提交
 *
 * @author 
 * @email 
 * @date 2026-04-11 14:37:54
 */
public interface ZuoyetijiaoService extends IService<ZuoyetijiaoEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ZuoyetijiaoVO> selectListVO(QueryWrapper<ZuoyetijiaoEntity> wrapper);
   	
   	ZuoyetijiaoVO selectVO(@Param("ew") QueryWrapper<ZuoyetijiaoEntity> wrapper);
   	
   	List<ZuoyetijiaoView> selectListView(QueryWrapper<ZuoyetijiaoEntity> wrapper);
   	
   	ZuoyetijiaoView selectView(@Param("ew") QueryWrapper<ZuoyetijiaoEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<ZuoyetijiaoEntity> wrapper);

   	

}

