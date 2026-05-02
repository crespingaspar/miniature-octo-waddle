package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.ZuoyepigaiDao;
import com.yb.entity.ZuoyepigaiEntity;
import com.yb.service.ZuoyepigaiService;
import com.yb.entity.vo.ZuoyepigaiVO;
import com.yb.entity.view.ZuoyepigaiView;

@Service("zuoyepigaiService")
public class ZuoyepigaiServiceImpl extends ServiceImpl<ZuoyepigaiDao, ZuoyepigaiEntity> implements ZuoyepigaiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZuoyepigaiEntity> page = baseMapper.selectPage(new Query<ZuoyepigaiEntity>(params).getPage(),new QueryWrapper<ZuoyepigaiEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<ZuoyepigaiEntity> wrapper) {
        Page<ZuoyepigaiEntity> page = baseMapper.selectPage(new Query<ZuoyepigaiEntity>(params).getPage(),wrapper);
        Page<ZuoyepigaiView> pageVo = (Page<ZuoyepigaiView>)page.convert(ZuoyepigaiView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<ZuoyepigaiVO> selectListVO(QueryWrapper<ZuoyepigaiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ZuoyepigaiVO selectVO(QueryWrapper<ZuoyepigaiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ZuoyepigaiView> selectListView(QueryWrapper<ZuoyepigaiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ZuoyepigaiView selectView(QueryWrapper<ZuoyepigaiEntity> wrapper) {
        ZuoyepigaiEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new ZuoyepigaiView(entity);
        } else {
            return null;
        }
	}


}
