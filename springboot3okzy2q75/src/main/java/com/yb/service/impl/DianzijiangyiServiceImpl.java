package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.DianzijiangyiDao;
import com.yb.entity.DianzijiangyiEntity;
import com.yb.service.DianzijiangyiService;
import com.yb.entity.vo.DianzijiangyiVO;
import com.yb.entity.view.DianzijiangyiView;

@Service("dianzijiangyiService")
public class DianzijiangyiServiceImpl extends ServiceImpl<DianzijiangyiDao, DianzijiangyiEntity> implements DianzijiangyiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DianzijiangyiEntity> page = baseMapper.selectPage(new Query<DianzijiangyiEntity>(params).getPage(),new QueryWrapper<DianzijiangyiEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<DianzijiangyiEntity> wrapper) {
        Page<DianzijiangyiEntity> page = baseMapper.selectPage(new Query<DianzijiangyiEntity>(params).getPage(),wrapper);
        Page<DianzijiangyiView> pageVo = (Page<DianzijiangyiView>)page.convert(DianzijiangyiView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<DianzijiangyiVO> selectListVO(QueryWrapper<DianzijiangyiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DianzijiangyiVO selectVO(QueryWrapper<DianzijiangyiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DianzijiangyiView> selectListView(QueryWrapper<DianzijiangyiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DianzijiangyiView selectView(QueryWrapper<DianzijiangyiEntity> wrapper) {
        DianzijiangyiEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new DianzijiangyiView(entity);
        } else {
            return null;
        }
	}


}
