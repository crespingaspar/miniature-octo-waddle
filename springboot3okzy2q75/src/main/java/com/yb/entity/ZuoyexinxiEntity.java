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
 * 作业信息
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-04-11 14:37:54
 */
@TableName("zuoyexinxi")
@Data
public class ZuoyexinxiEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public ZuoyexinxiEntity() {
		
	}
	
	public ZuoyexinxiEntity(T t) {
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
	 * 作业名称
	 */
	private String zuoyemingcheng;
	
	/**
	 * 科目
	 */
	private String kemu;
	
	/**
	 * 图片
	 */
	private String tupian;
	
	/**
	 * 作业文件
	 */
	private String zuoyewenjian;
	
	/**
	 * 作业要求
	 */
	private String zuoyeyaoqiu;
	
	/**
	 * 工号
	 */
	private String gonghao;
	
	/**
	 * 教师姓名
	 */
	private String jiaoshixingming;
	
	/**
	 * 倒计结束时间
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date reversetime;
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

}
