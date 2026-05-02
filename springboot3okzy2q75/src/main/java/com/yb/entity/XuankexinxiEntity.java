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
 * 选课信息
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-04-11 14:37:54
 */
@TableName("xuankexinxi")
@Data
public class XuankexinxiEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public XuankexinxiEntity() {
		
	}
	
	public XuankexinxiEntity(T t) {
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
	 * 剩余课时
	 */
	private Double keshi;
	
	/**
	 * 费用
	 */
	private Double feiyong;
	
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
	 * 选课时间
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date xuankeshijian;
	
	/**
	 * 跨表用户id
	 */
	private Long crossuserid;
	
	/**
	 * 跨表主键id
	 */
	private Long crossrefid;
	
	/**
	 * 是否审核
	 */
	private String sfsh;
	
	/**
	 * 审核回复
	 */
	private String shhf;
	
	/**
	 * 是否支付
	 */
	private String ispay;
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

}
