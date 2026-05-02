package com.yb.entity.vo;

import com.yb.entity.KebiaoxinixEntity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 课表信息
 * @author 
 * @email 
 * @date 2026-04-11 14:37:55
 */
public class KebiaoxinixVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 节数
	 */
	
	private String sectionnum;
		
	/**
	 * 日期
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
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
				
	
	/**
	 * 设置：节数
	 */
	 
	public void setSectionnum(String sectionnum) {
		this.sectionnum = sectionnum;
	}
	
	/**
	 * 获取：节数
	 */
	public String getSectionnum() {
		return sectionnum;
	}
				
	
	/**
	 * 设置：日期
	 */
	 
	public void setRiqi(Date riqi) {
		this.riqi = riqi;
	}
	
	/**
	 * 获取：日期
	 */
	public Date getRiqi() {
		return riqi;
	}
				
	
	/**
	 * 设置：星期
	 */
	 
	public void setWeek(Integer week) {
		this.week = week;
	}
	
	/**
	 * 获取：星期
	 */
	public Integer getWeek() {
		return week;
	}
				
	
	/**
	 * 设置：班级
	 */
	 
	public void setBanji(String banji) {
		this.banji = banji;
	}
	
	/**
	 * 获取：班级
	 */
	public String getBanji() {
		return banji;
	}
				
	
	/**
	 * 设置：教室
	 */
	 
	public void setJiaoshi(String jiaoshi) {
		this.jiaoshi = jiaoshi;
	}
	
	/**
	 * 获取：教室
	 */
	public String getJiaoshi() {
		return jiaoshi;
	}
				
	
	/**
	 * 设置：工号
	 */
	 
	public void setGonghao(String gonghao) {
		this.gonghao = gonghao;
	}
	
	/**
	 * 获取：工号
	 */
	public String getGonghao() {
		return gonghao;
	}
				
	
	/**
	 * 设置：教师姓名
	 */
	 
	public void setJiaoshixingming(String jiaoshixingming) {
		this.jiaoshixingming = jiaoshixingming;
	}
	
	/**
	 * 获取：教师姓名
	 */
	public String getJiaoshixingming() {
		return jiaoshixingming;
	}
			
}
