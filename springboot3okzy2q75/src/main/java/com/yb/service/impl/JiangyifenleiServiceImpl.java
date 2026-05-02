package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.JiangyifenleiDao;
import com.yb.entity.JiangyifenleiEntity;
import com.yb.service.JiangyifenleiService;
import com.yb.entity.vo.JiangyifenleiVO;
import com.yb.entity.view.JiangyifenleiView;

@Service("jiangyifenleiService")
public class JiangyifenleiServiceImpl extends ServiceImpl<JiangyifenleiDao, JiangyifenleiEntity> implements JiangyifenleiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JiangyifenleiEntity> page = baseMapper.selectPage(new Query<JiangyifenleiEntity>(params).getPage(),new QueryWrapper<JiangyifenleiEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<JiangyifenleiEntity> wrapper) {
        Page<JiangyifenleiEntity> page = baseMapper.selectPage(new Query<JiangyifenleiEntity>(params).getPage(),wrapper);
        Page<JiangyifenleiView> pageVo = (Page<JiangyifenleiView>)page.convert(JiangyifenleiView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<JiangyifenleiVO> selectListVO(QueryWrapper<JiangyifenleiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public JiangyifenleiVO selectVO(QueryWrapper<JiangyifenleiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<JiangyifenleiView> selectListView(QueryWrapper<JiangyifenleiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public JiangyifenleiView selectView(QueryWrapper<JiangyifenleiEntity> wrapper) {
        JiangyifenleiEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new JiangyifenleiView(entity);
        } else {
            return null;
        }
	}


}
