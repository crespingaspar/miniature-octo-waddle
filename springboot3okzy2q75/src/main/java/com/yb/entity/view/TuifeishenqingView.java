package com.yb.entity.view;

import com.yb.entity.TuifeishenqingEntity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.yb.utils.EncryptUtil;
 

/**
 * 退费申请
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2026-04-11 14:37:54
 */
@TableName("tuifeishenqing")
public class TuifeishenqingView  extends TuifeishenqingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public TuifeishenqingView(){
	}
 
 	public TuifeishenqingView(TuifeishenqingEntity tuifeishenqingEntity){
 	try {
			BeanUtils.copyProperties(tuifeishenqingEntity,this);
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
