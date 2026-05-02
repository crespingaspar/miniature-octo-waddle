package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.ZuoyetijiaoDao;
import com.yb.entity.ZuoyetijiaoEntity;
import com.yb.service.ZuoyetijiaoService;
import com.yb.entity.vo.ZuoyetijiaoVO;
import com.yb.entity.view.ZuoyetijiaoView;

@Service("zuoyetijiaoService")
public class ZuoyetijiaoServiceImpl extends ServiceImpl<ZuoyetijiaoDao, ZuoyetijiaoEntity> implements ZuoyetijiaoService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZuoyetijiaoEntity> page = baseMapper.selectPage(new Query<ZuoyetijiaoEntity>(params).getPage(),new QueryWrapper<ZuoyetijiaoEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<ZuoyetijiaoEntity> wrapper) {
        Page<ZuoyetijiaoEntity> page = baseMapper.selectPage(new Query<ZuoyetijiaoEntity>(params).getPage(),wrapper);
        Page<ZuoyetijiaoView> pageVo = (Page<ZuoyetijiaoView>)page.convert(ZuoyetijiaoView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<ZuoyetijiaoVO> selectListVO(QueryWrapper<ZuoyetijiaoEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ZuoyetijiaoVO selectVO(QueryWrapper<ZuoyetijiaoEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ZuoyetijiaoView> selectListView(QueryWrapper<ZuoyetijiaoEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ZuoyetijiaoView selectView(QueryWrapper<ZuoyetijiaoEntity> wrapper) {
        ZuoyetijiaoEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new ZuoyetijiaoView(entity);
        } else {
            return null;
        }
	}


}
