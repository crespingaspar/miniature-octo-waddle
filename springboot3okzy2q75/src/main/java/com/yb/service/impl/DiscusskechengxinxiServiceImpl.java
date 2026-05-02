package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.DiscusskechengxinxiDao;
import com.yb.entity.DiscusskechengxinxiEntity;
import com.yb.service.DiscusskechengxinxiService;
import com.yb.entity.vo.DiscusskechengxinxiVO;
import com.yb.entity.view.DiscusskechengxinxiView;

@Service("discusskechengxinxiService")
public class DiscusskechengxinxiServiceImpl extends ServiceImpl<DiscusskechengxinxiDao, DiscusskechengxinxiEntity> implements DiscusskechengxinxiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscusskechengxinxiEntity> page = baseMapper.selectPage(new Query<DiscusskechengxinxiEntity>(params).getPage(),new QueryWrapper<DiscusskechengxinxiEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<DiscusskechengxinxiEntity> wrapper) {
        Page<DiscusskechengxinxiEntity> page = baseMapper.selectPage(new Query<DiscusskechengxinxiEntity>(params).getPage(),wrapper);
        Page<DiscusskechengxinxiView> pageVo = (Page<DiscusskechengxinxiView>)page.convert(DiscusskechengxinxiView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<DiscusskechengxinxiVO> selectListVO(QueryWrapper<DiscusskechengxinxiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscusskechengxinxiVO selectVO(QueryWrapper<DiscusskechengxinxiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscusskechengxinxiView> selectListView(QueryWrapper<DiscusskechengxinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscusskechengxinxiView selectView(QueryWrapper<DiscusskechengxinxiEntity> wrapper) {
        DiscusskechengxinxiEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new DiscusskechengxinxiView(entity);
        } else {
            return null;
        }
	}


}
