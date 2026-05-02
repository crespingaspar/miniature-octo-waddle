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
 * 学生
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-04-11 14:37:53
 */
@TableName("xuesheng")
@Data
public class XueshengEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public XueshengEntity() {
		
	}
	
	public XueshengEntity(T t) {
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
	 * 学号
	 */
	private String xuehao;
	
	/**
	 * 密码
	 */
	private String mima;
	
	/**
	 * 姓名
	 */
	private String xingming;
	
	/**
	 * 头像
	 */
	private String touxiang;
	
	/**
	 * 性别
	 */
	private String xingbie;
	
	/**
	 * 年龄
	 */
	private Integer nianling;
	
	/**
	 * 目标院校
	 */
	private String mubiaoyuanxiao;
	
	/**
	 * 班级
	 */
	private String banji;
	
	/**
	 * 备考科目
	 */
	private String beikaokemu;
	
	/**
	 * 基础水平
	 */
	private String jichushuiping;
	
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
