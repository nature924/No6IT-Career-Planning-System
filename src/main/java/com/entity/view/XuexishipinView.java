package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.XuexishipinEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 课程学习
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("xuexishipin")
public class XuexishipinView extends XuexishipinEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 课程类型的值
	*/
	@ColumnInfo(comment="课程类型的字典表值",type="varchar(200)")
	private String xuexishipinValue;
	/**
	* 职业的值
	*/
	@ColumnInfo(comment="职业的字典表值",type="varchar(200)")
	private String zhiyejieshaoValue;




	public XuexishipinView() {

	}

	public XuexishipinView(XuexishipinEntity xuexishipinEntity) {
		try {
			BeanUtils.copyProperties(this, xuexishipinEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 课程类型的值
	*/
	public String getXuexishipinValue() {
		return xuexishipinValue;
	}
	/**
	* 设置： 课程类型的值
	*/
	public void setXuexishipinValue(String xuexishipinValue) {
		this.xuexishipinValue = xuexishipinValue;
	}
	//当前表的
	/**
	* 获取： 职业的值
	*/
	public String getZhiyejieshaoValue() {
		return zhiyejieshaoValue;
	}
	/**
	* 设置： 职业的值
	*/
	public void setZhiyejieshaoValue(String zhiyejieshaoValue) {
		this.zhiyejieshaoValue = zhiyejieshaoValue;
	}




	@Override
	public String toString() {
		return "XuexishipinView{" +
			", xuexishipinValue=" + xuexishipinValue +
			", zhiyejieshaoValue=" + zhiyejieshaoValue +
			"} " + super.toString();
	}
}
