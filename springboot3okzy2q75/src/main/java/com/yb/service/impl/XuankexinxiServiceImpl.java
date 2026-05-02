package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.XuankexinxiDao;
import com.yb.entity.XuankexinxiEntity;
import com.yb.service.XuankexinxiService;
import com.yb.entity.vo.XuankexinxiVO;
import com.yb.entity.view.XuankexinxiView;

@Service("xuankexinxiService")
public class XuankexinxiServiceImpl extends ServiceImpl<XuankexinxiDao, XuankexinxiEntity> implements XuankexinxiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<XuankexinxiEntity> page = baseMapper.selectPage(new Query<XuankexinxiEntity>(params).getPage(),new QueryWrapper<XuankexinxiEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<XuankexinxiEntity> wrapper) {
        Page<XuankexinxiEntity> page = baseMapper.selectPage(new Query<XuankexinxiEntity>(params).getPage(),wrapper);
        Page<XuankexinxiView> pageVo = (Page<XuankexinxiView>)page.convert(XuankexinxiView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<XuankexinxiVO> selectListVO(QueryWrapper<XuankexinxiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public XuankexinxiVO selectVO(QueryWrapper<XuankexinxiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<XuankexinxiView> selectListView(QueryWrapper<XuankexinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public XuankexinxiView selectView(QueryWrapper<XuankexinxiEntity> wrapper) {
        XuankexinxiEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new XuankexinxiView(entity);
        } else {
            return null;
        }
	}


}
