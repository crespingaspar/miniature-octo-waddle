package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.KechengfenleiDao;
import com.yb.entity.KechengfenleiEntity;
import com.yb.service.KechengfenleiService;
import com.yb.entity.vo.KechengfenleiVO;
import com.yb.entity.view.KechengfenleiView;

@Service("kechengfenleiService")
public class KechengfenleiServiceImpl extends ServiceImpl<KechengfenleiDao, KechengfenleiEntity> implements KechengfenleiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<KechengfenleiEntity> page = baseMapper.selectPage(new Query<KechengfenleiEntity>(params).getPage(),new QueryWrapper<KechengfenleiEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<KechengfenleiEntity> wrapper) {
        Page<KechengfenleiEntity> page = baseMapper.selectPage(new Query<KechengfenleiEntity>(params).getPage(),wrapper);
        Page<KechengfenleiView> pageVo = (Page<KechengfenleiView>)page.convert(KechengfenleiView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<KechengfenleiVO> selectListVO(QueryWrapper<KechengfenleiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public KechengfenleiVO selectVO(QueryWrapper<KechengfenleiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<KechengfenleiView> selectListView(QueryWrapper<KechengfenleiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public KechengfenleiView selectView(QueryWrapper<KechengfenleiEntity> wrapper) {
        KechengfenleiEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new KechengfenleiView(entity);
        } else {
            return null;
        }
	}


}
