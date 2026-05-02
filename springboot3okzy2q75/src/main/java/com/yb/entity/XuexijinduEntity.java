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
 * 学习进度
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-04-11 14:37:54
 */
@TableName("xuexijindu")
@Data
public class XuexijinduEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public XuexijinduEntity() {
		
	}
	
	public XuexijinduEntity(T t) {
		try {
			BeanUtils.copyProperties(t,this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 主键id
	 */
    @TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 课程号
	 */
	private String kechenghao;
	
	/**
	 * 课程名称
	 */
	private String kechengmingcheng;
	
	/**
	 * 课程分类
	 */
	private String kechengfenlei;
	
	/**
	 * 图片
	 */
	private String tupian;
	
	/**
	 * 难度
	 */
	private String nandu;
	
	/**
	 * 作业完成率
	 */
	private Double zuoyewanchenglv;
	
	/**
	 * 知识掌握情况
	 */
	private String zhishizhangwoqingkuang;
	
	/**
	 * 学号
	 */
	private String xuehao;
	
	/**
	 * 姓名
	 */
	private String xingming;
	
	/**
	 * 工号
	 */
	private String gonghao;
	
	/**
	 * 教师姓名
	 */
	private String jiaoshixingming;
	
	/**
	 * 学习时间
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date xuexishijian;
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

}
