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
 * 电子讲义
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-04-11 14:37:54
 */
@TableName("dianzijiangyi")
@Data
public class DianzijiangyiEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public DianzijiangyiEntity() {
		
	}
	
	public DianzijiangyiEntity(T t) {
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
	 * 讲义名称
	 */
	private String jiangyimingcheng;
	
	/**
	 * 讲义分类
	 */
	private String jiangyifenlei;
	
	/**
	 * 图片
	 */
	private String tupian;
	
	/**
	 * 附件下载
	 */
	private String fujianxiazai;
	
	/**
	 * 考点笔记
	 */
	private String kaodianbiji;
	
	/**
	 * 讲义内容
	 */
	private String jiangyineirong;
	
	/**
	 * 工号
	 */
	private String gonghao;
	
	/**
	 * 教师姓名
	 */
	private String jiaoshixingming;
	
	/**
	 * 最近点击时间
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date clicktime;
	
	/**
	 * 点击次数
	 */
	private Integer clicknum;
	
	/**
	 * 评论数
	 */
	private Integer discussnum;
	
	/**
	 * 收藏数
	 */
	private Integer storeupnum;
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

}
