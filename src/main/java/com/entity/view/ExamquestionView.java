package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.ExamquestionEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 试题表
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("examquestion")
public class ExamquestionView extends ExamquestionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 科目的值
	*/
	@ColumnInfo(comment="科目的字典表值",type="varchar(200)")
	private String kemuValue;
	/**
	* 试题类型的值
	*/
	@ColumnInfo(comment="试题类型的字典表值",type="varchar(200)")
	private String examquestionValue;




	public ExamquestionView() {

	}

	public ExamquestionView(ExamquestionEntity examquestionEntity) {
		try {
			BeanUtils.copyProperties(this, examquestionEntity);
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
	* 获取： 试题类型的值
	*/
	public String getExamquestionValue() {
		return examquestionValue;
	}
	/**
	* 设置： 试题类型的值
	*/
	public void setExamquestionValue(String examquestionValue) {
		this.examquestionValue = examquestionValue;
	}




	@Override
	public String toString() {
		return "ExamquestionView{" +
			", kemuValue=" + kemuValue +
			", examquestionValue=" + examquestionValue +
			"} " + super.toString();
	}
}
