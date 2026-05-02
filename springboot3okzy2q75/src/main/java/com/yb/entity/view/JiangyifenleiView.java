package com.yb.entity.view;

import com.yb.entity.JiangyifenleiEntity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.yb.utils.EncryptUtil;
 

/**
 * 讲义分类
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2026-04-11 14:37:54
 */
@TableName("jiangyifenlei")
public class JiangyifenleiView  extends JiangyifenleiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public JiangyifenleiView(){
	}
 
 	public JiangyifenleiView(JiangyifenleiEntity jiangyifenleiEntity){
 	try {
			BeanUtils.copyProperties(jiangyifenleiEntity,this);
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
