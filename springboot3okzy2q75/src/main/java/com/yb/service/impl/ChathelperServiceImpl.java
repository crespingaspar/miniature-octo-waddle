package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.ChathelperDao;
import com.yb.entity.ChathelperEntity;
import com.yb.service.ChathelperService;
import com.yb.entity.vo.ChathelperVO;
import com.yb.entity.view.ChathelperView;

@Service("chathelperService")
public class ChathelperServiceImpl extends ServiceImpl<ChathelperDao, ChathelperEntity> implements ChathelperService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ChathelperEntity> page = baseMapper.selectPage(new Query<ChathelperEntity>(params).getPage(),new QueryWrapper<ChathelperEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<ChathelperEntity> wrapper) {
        Page<ChathelperEntity> page = baseMapper.selectPage(new Query<ChathelperEntity>(params).getPage(),wrapper);
        Page<ChathelperView> pageVo = (Page<ChathelperView>)page.convert(ChathelperView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<ChathelperVO> selectListVO(QueryWrapper<ChathelperEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ChathelperVO selectVO(QueryWrapper<ChathelperEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ChathelperView> selectListView(QueryWrapper<ChathelperEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ChathelperView selectView(QueryWrapper<ChathelperEntity> wrapper) {
        ChathelperEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new ChathelperView(entity);
        } else {
            return null;
        }
	}


}
