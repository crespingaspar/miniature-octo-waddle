package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.ZuoyexinxiDao;
import com.yb.entity.ZuoyexinxiEntity;
import com.yb.service.ZuoyexinxiService;
import com.yb.entity.vo.ZuoyexinxiVO;
import com.yb.entity.view.ZuoyexinxiView;

@Service("zuoyexinxiService")
public class ZuoyexinxiServiceImpl extends ServiceImpl<ZuoyexinxiDao, ZuoyexinxiEntity> implements ZuoyexinxiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZuoyexinxiEntity> page = baseMapper.selectPage(new Query<ZuoyexinxiEntity>(params).getPage(),new QueryWrapper<ZuoyexinxiEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<ZuoyexinxiEntity> wrapper) {
        Page<ZuoyexinxiEntity> page = baseMapper.selectPage(new Query<ZuoyexinxiEntity>(params).getPage(),wrapper);
        Page<ZuoyexinxiView> pageVo = (Page<ZuoyexinxiView>)page.convert(ZuoyexinxiView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<ZuoyexinxiVO> selectListVO(QueryWrapper<ZuoyexinxiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ZuoyexinxiVO selectVO(QueryWrapper<ZuoyexinxiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ZuoyexinxiView> selectListView(QueryWrapper<ZuoyexinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ZuoyexinxiView selectView(QueryWrapper<ZuoyexinxiEntity> wrapper) {
        ZuoyexinxiEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new ZuoyexinxiView(entity);
        } else {
            return null;
        }
	}


}
