package com.yb.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.*;
import org.springframework.beans.BeanUtils;
import com.baomidou.mybatisplus.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * 教师
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-04-11 14:37:53
 */
@TableName("jiaoshi")
@Data
public class JiaoshiEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public JiaoshiEntity() {
		
	}
	
	public JiaoshiEntity(T t) {
		try {
			BeanUtils.copyProperties(t,this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 主键id
	 */
    @TableId
	private Long id;
	/**
	 * 工号
	 */
	private String gonghao;
	
	/**
	 * 密码
	 */
	private String mima;
	
	/**
	 * 教师姓名
	 */
	private String jiaoshixingming;
	
	/**
	 * 头像
	 */
	private String touxiang;
	
	/**
	 * 资质证明
	 */
	private String zizhizhengming;
	
	/**
	 * 性别
	 */
	private String xingbie;
	
	/**
	 * 年龄
	 */
	private Integer nianling;
	
	/**
	 * 职称
	 */
	private String zhicheng;
	
	/**
	 * 擅长科目
	 */
	private String shanzhangkemu;
	
	/**
	 * 教学风格
	 */
	private String jiaoxuefengge;
	
	/**
	 * 辅导成果
	 */
	private String fudaochengguo;
	
	/**
	 * 是否审核
	 */
	private String sfsh;
	
	/**
	 * 审核回复
	 */
	private String shhf;
	
	/**
	 * 手机号
	 */
	private String mobile;
	
	/**
	 * 密保问题
	 */
	private String pquestion;
	
	/**
	 * 密保答案
	 */
	private String panswer;
	
	/**
	 * 状态
	 */
	private Integer status;
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

}
