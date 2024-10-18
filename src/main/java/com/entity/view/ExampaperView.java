package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.ExampaperEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 试卷表
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("exampaper")
public class ExampaperView extends ExampaperEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 科目的值
	*/
	@ColumnInfo(comment="科目的字典表值",type="varchar(200)")
	private String kemuValue;
	/**
	* 试卷状态的值
	*/
	@ColumnInfo(comment="试卷状态的字典表值",type="varchar(200)")
	private String exampaperValue;
	/**
	* 组卷方式的值
	*/
	@ColumnInfo(comment="组卷方式的字典表值",type="varchar(200)")
	private String zujuanValue;




	public ExampaperView() {

	}

	public ExampaperView(ExampaperEntity exampaperEntity) {
		try {
			BeanUtils.copyProperties(this, exampaperEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 科目的值
	*/
	public String getKemuValue() {
		return kemuValue;
	}
	/**
	* 设置： 科目的值
	*/
	public void setKemuValue(String kemuValue) {
		this.kemuValue = kemuValue;
	}
	//当前表的
	/**
	* 获取： 试卷状态的值
	*/
	public String getExampaperValue() {
		return exampaperValue;
	}
	/**
	* 设置： 试卷状态的值
	*/
	public void setExampaperValue(String exampaperValue) {
		this.exampaperValue = exampaperValue;
	}
	//当前表的
	/**
	* 获取： 组卷方式的值
	*/
	public String getZujuanValue() {
		return zujuanValue;
	}
	/**
	* 设置： 组卷方式的值
	*/
	public void setZujuanValue(String zujuanValue) {
		this.zujuanValue = zujuanValue;
	}




	@Override
	public String toString() {
		return "ExampaperView{" +
			", kemuValue=" + kemuValue +
			", exampaperValue=" + exampaperValue +
			", zujuanValue=" + zujuanValue +
			"} " + super.toString();
	}
}
