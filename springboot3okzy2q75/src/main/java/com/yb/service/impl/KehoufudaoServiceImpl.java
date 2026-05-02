package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.KehoufudaoDao;
import com.yb.entity.KehoufudaoEntity;
import com.yb.service.KehoufudaoService;
import com.yb.entity.vo.KehoufudaoVO;
import com.yb.entity.view.KehoufudaoView;

@Service("kehoufudaoService")
public class KehoufudaoServiceImpl extends ServiceImpl<KehoufudaoDao, KehoufudaoEntity> implements KehoufudaoService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<KehoufudaoEntity> page = baseMapper.selectPage(new Query<KehoufudaoEntity>(params).getPage(),new QueryWrapper<KehoufudaoEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<KehoufudaoEntity> wrapper) {
        Page<KehoufudaoEntity> page = baseMapper.selectPage(new Query<KehoufudaoEntity>(params).getPage(),wrapper);
        Page<KehoufudaoView> pageVo = (Page<KehoufudaoView>)page.convert(KehoufudaoView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<KehoufudaoVO> selectListVO(QueryWrapper<KehoufudaoEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public KehoufudaoVO selectVO(QueryWrapper<KehoufudaoEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<KehoufudaoView> selectListView(QueryWrapper<KehoufudaoEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public KehoufudaoView selectView(QueryWrapper<KehoufudaoEntity> wrapper) {
        KehoufudaoEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new KehoufudaoView(entity);
        } else {
            return null;
        }
	}


}
