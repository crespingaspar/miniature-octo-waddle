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
 * 课表信息
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-04-11 14:37:55
 */
@TableName("kebiaoxinix")
@Data
public class KebiaoxinixEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public KebiaoxinixEntity() {
		
	}
	
	public KebiaoxinixEntity(T t) {
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
	 * 课程名称
	 */
	private String kechengmingcheng;
	
	/**
	 * 节数
	 */
	private String sectionnum;
	
	/**
	 * 日期
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat
	private Date riqi;
	
	/**
	 * 星期
	 */
	private Integer week;
	
	/**
	 * 班级
	 */
	private String banji;
	
	/**
	 * 教室
	 */
	private String jiaoshi;
	
	/**
	 * 工号
	 */
	private String gonghao;
	
	/**
	 * 教师姓名
	 */
	private String jiaoshixingming;
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

}
