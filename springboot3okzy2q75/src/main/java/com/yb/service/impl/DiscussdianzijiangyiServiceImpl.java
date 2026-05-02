package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.DiscussdianzijiangyiDao;
import com.yb.entity.DiscussdianzijiangyiEntity;
import com.yb.service.DiscussdianzijiangyiService;
import com.yb.entity.vo.DiscussdianzijiangyiVO;
import com.yb.entity.view.DiscussdianzijiangyiView;

@Service("discussdianzijiangyiService")
public class DiscussdianzijiangyiServiceImpl extends ServiceImpl<DiscussdianzijiangyiDao, DiscussdianzijiangyiEntity> implements DiscussdianzijiangyiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussdianzijiangyiEntity> page = baseMapper.selectPage(new Query<DiscussdianzijiangyiEntity>(params).getPage(),new QueryWrapper<DiscussdianzijiangyiEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<DiscussdianzijiangyiEntity> wrapper) {
        Page<DiscussdianzijiangyiEntity> page = baseMapper.selectPage(new Query<DiscussdianzijiangyiEntity>(params).getPage(),wrapper);
        Page<DiscussdianzijiangyiView> pageVo = (Page<DiscussdianzijiangyiView>)page.convert(DiscussdianzijiangyiView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<DiscussdianzijiangyiVO> selectListVO(QueryWrapper<DiscussdianzijiangyiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscussdianzijiangyiVO selectVO(QueryWrapper<DiscussdianzijiangyiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscussdianzijiangyiView> selectListView(QueryWrapper<DiscussdianzijiangyiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscussdianzijiangyiView selectView(QueryWrapper<DiscussdianzijiangyiEntity> wrapper) {
        DiscussdianzijiangyiEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new DiscussdianzijiangyiView(entity);
        } else {
            return null;
        }
	}


}
