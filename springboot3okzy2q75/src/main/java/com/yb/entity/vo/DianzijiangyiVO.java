package com.yb.entity.vo;

import com.yb.entity.DianzijiangyiEntity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 电子讲义
 * @author 
 * @email 
 * @date 2026-04-11 14:37:54
 */
public class DianzijiangyiVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
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
				
	
	/**
	 * 设置：讲义分类
	 */
	 
	public void setJiangyifenlei(String jiangyifenlei) {
		this.jiangyifenlei = jiangyifenlei;
	}
	
	/**
	 * 获取：讲义分类
	 */
	public String getJiangyifenlei() {
		return jiangyifenlei;
	}
				
	
	/**
	 * 设置：图片
	 */
	 
	public void setTupian(String tupian) {
		this.tupian = tupian;
	}
	
	/**
	 * 获取：图片
	 */
	public String getTupian() {
		return tupian;
	}
				
	
	/**
	 * 设置：附件下载
	 */
	 
	public void setFujianxiazai(String fujianxiazai) {
		this.fujianxiazai = fujianxiazai;
	}
	
	/**
	 * 获取：附件下载
	 */
	public String getFujianxiazai() {
		return fujianxiazai;
	}
				
	
	/**
	 * 设置：考点笔记
	 */
	 
	public void setKaodianbiji(String kaodianbiji) {
		this.kaodianbiji = kaodianbiji;
	}
	
	/**
	 * 获取：考点笔记
	 */
	public String getKaodianbiji() {
		return kaodianbiji;
	}
				
	
	/**
	 * 设置：讲义内容
	 */
	 
	public void setJiangyineirong(String jiangyineirong) {
		this.jiangyineirong = jiangyineirong;
	}
	
	/**
	 * 获取：讲义内容
	 */
	public String getJiangyineirong() {
		return jiangyineirong;
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
				
	
	/**
	 * 设置：最近点击时间
	 */
	 
	public void setClicktime(Date clicktime) {
		this.clicktime = clicktime;
	}
	
	/**
	 * 获取：最近点击时间
	 */
	public Date getClicktime() {
		return clicktime;
	}
				
	
	/**
	 * 设置：点击次数
	 */
	 
	public void setClicknum(Integer clicknum) {
		this.clicknum = clicknum;
	}
	
	/**
	 * 获取：点击次数
	 */
	public Integer getClicknum() {
		return clicknum;
	}
				
	
	/**
	 * 设置：评论数
	 */
	 
	public void setDiscussnum(Integer discussnum) {
		this.discussnum = discussnum;
	}
	
	/**
	 * 获取：评论数
	 */
	public Integer getDiscussnum() {
		return discussnum;
	}
				
	
	/**
	 * 设置：收藏数
	 */
	 
	public void setStoreupnum(Integer storeupnum) {
		this.storeupnum = storeupnum;
	}
	
	/**
	 * 获取：收藏数
	 */
	public Integer getStoreupnum() {
		return storeupnum;
	}
			
}
