package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.TuifeishenqingDao;
import com.yb.entity.TuifeishenqingEntity;
import com.yb.service.TuifeishenqingService;
import com.yb.entity.vo.TuifeishenqingVO;
import com.yb.entity.view.TuifeishenqingView;

@Service("tuifeishenqingService")
public class TuifeishenqingServiceImpl extends ServiceImpl<TuifeishenqingDao, TuifeishenqingEntity> implements TuifeishenqingService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TuifeishenqingEntity> page = baseMapper.selectPage(new Query<TuifeishenqingEntity>(params).getPage(),new QueryWrapper<TuifeishenqingEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<TuifeishenqingEntity> wrapper) {
        Page<TuifeishenqingEntity> page = baseMapper.selectPage(new Query<TuifeishenqingEntity>(params).getPage(),wrapper);
        Page<TuifeishenqingView> pageVo = (Page<TuifeishenqingView>)page.convert(TuifeishenqingView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<TuifeishenqingVO> selectListVO(QueryWrapper<TuifeishenqingEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public TuifeishenqingVO selectVO(QueryWrapper<TuifeishenqingEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<TuifeishenqingView> selectListView(QueryWrapper<TuifeishenqingEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public TuifeishenqingView selectView(QueryWrapper<TuifeishenqingEntity> wrapper) {
        TuifeishenqingEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new TuifeishenqingView(entity);
        } else {
            return null;
        }
	}


}
