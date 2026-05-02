package com.yb.entity.vo;

import com.yb.entity.KehoufudaoEntity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 课后辅导
 * @author 
 * @email 
 * @date 2026-04-11 14:37:55
 */
public class KehoufudaoVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 课程分类
	 */
	
	private String kechengfenlei;
		
	/**
	 * 图片
	 */
	
	private String tupian;
		
	/**
	 * 学习效果
	 */
	
	private String xuexixiaoguo;
		
	/**
	 * 辅导内容
	 */
	
	private String fudaoneirong;
		
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
	 * 辅导时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date fudaoshijian;
				
	
	/**
	 * 设置：课程分类
	 */
	 
	public void setKechengfenlei(String kechengfenlei) {
		this.kechengfenlei = kechengfenlei;
	}
	
	/**
	 * 获取：课程分类
	 */
	public String getKechengfenlei() {
		return kechengfenlei;
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
	 * 设置：学习效果
	 */
	 
	public void setXuexixiaoguo(String xuexixiaoguo) {
		this.xuexixiaoguo = xuexixiaoguo;
	}
	
	/**
	 * 获取：学习效果
	 */
	public String getXuexixiaoguo() {
		return xuexixiaoguo;
	}
				
	
	/**
	 * 设置：辅导内容
	 */
	 
	public void setFudaoneirong(String fudaoneirong) {
		this.fudaoneirong = fudaoneirong;
	}
	
	/**
	 * 获取：辅导内容
	 */
	public String getFudaoneirong() {
		return fudaoneirong;
	}
				
	
	/**
	 * 设置：学号
	 */
	 
	public void setXuehao(String xuehao) {
		this.xuehao = xuehao;
	}
	
	/**
	 * 获取：学号
	 */
	public String getXuehao() {
		return xuehao;
	}
				
	
	/**
	 * 设置：姓名
	 */
	 
	public void setXingming(String xingming) {
		this.xingming = xingming;
	}
	
	/**
	 * 获取：姓名
	 */
	public String getXingming() {
		return xingming;
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
	 * 设置：辅导时间
	 */
	 
	public void setFudaoshijian(Date fudaoshijian) {
		this.fudaoshijian = fudaoshijian;
	}
	
	/**
	 * 获取：辅导时间
	 */
	public Date getFudaoshijian() {
		return fudaoshijian;
	}
			
}
