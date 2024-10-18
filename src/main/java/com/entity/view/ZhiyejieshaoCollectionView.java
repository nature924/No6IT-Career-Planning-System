package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.ZhiyejieshaoCollectionEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 职业介绍收藏
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("zhiyejieshao_collection")
public class ZhiyejieshaoCollectionView extends ZhiyejieshaoCollectionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 类型的值
	*/
	@ColumnInfo(comment="类型的字典表值",type="varchar(200)")
	private String zhiyejieshaoCollectionValue;

	//级联表 用户
		/**
		* 用户姓名
		*/

		@ColumnInfo(comment="用户姓名",type="varchar(200)")
		private String yonghuName;
		/**
		* 头像
		*/

		@ColumnInfo(comment="头像",type="varchar(255)")
		private String yonghuPhoto;
		/**
		* 用户手机号
		*/

		@ColumnInfo(comment="用户手机号",type="varchar(200)")
		private String yonghuPhone;
		/**
		* 用户身份证号
		*/

		@ColumnInfo(comment="用户身份证号",type="varchar(200)")
		private String yonghuIdNumber;
		/**
		* 邮箱
		*/

		@ColumnInfo(comment="邮箱",type="varchar(200)")
		private String yonghuEmail;
		/**
		* 假删
		*/

		@ColumnInfo(comment="假删",type="int(11)")
		private Integer yonghuDelete;
	//级联表 职业介绍
		/**
		* 职业名称
		*/

		@ColumnInfo(comment="职业名称",type="varchar(200)")
		private String zhiyejieshaoName;
		/**
		* 职业封面
		*/

		@ColumnInfo(comment="职业封面",type="varchar(200)")
		private String zhiyejieshaoPhoto;
		/**
		* 职业
		*/
		@ColumnInfo(comment="职业",type="int(11)")
		private Integer zhiyejieshaoTypes;
			/**
			* 职业的值
			*/
			@ColumnInfo(comment="职业的字典表值",type="varchar(200)")
			private String zhiyejieshaoValue;
		/**
		* 职业详情
		*/

		@ColumnInfo(comment="职业详情",type="text")
		private String zhiyejieshaoContent;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer zhiyejieshaoDelete;



	public ZhiyejieshaoCollectionView() {

	}

	public ZhiyejieshaoCollectionView(ZhiyejieshaoCollectionEntity zhiyejieshaoCollectionEntity) {
		try {
			BeanUtils.copyProperties(this, zhiyejieshaoCollectionEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 类型的值
	*/
	public String getZhiyejieshaoCollectionValue() {
		return zhiyejieshaoCollectionValue;
	}
	/**
	* 设置： 类型的值
	*/
	public void setZhiyejieshaoCollectionValue(String zhiyejieshaoCollectionValue) {
		this.zhiyejieshaoCollectionValue = zhiyejieshaoCollectionValue;
	}


	//级联表的get和set 用户

		/**
		* 获取： 用户姓名
		*/
		public String getYonghuName() {
			return yonghuName;
		}
		/**
		* 设置： 用户姓名
		*/
		public void setYonghuName(String yonghuName) {
			this.yonghuName = yonghuName;
		}

		/**
		* 获取： 头像
		*/
		public String getYonghuPhoto() {
			return yonghuPhoto;
		}
		/**
		* 设置： 头像
		*/
		public void setYonghuPhoto(String yonghuPhoto) {
			this.yonghuPhoto = yonghuPhoto;
		}

		/**
		* 获取： 用户手机号
		*/
		public String getYonghuPhone() {
			return yonghuPhone;
		}
		/**
		* 设置： 用户手机号
		*/
		public void setYonghuPhone(String yonghuPhone) {
			this.yonghuPhone = yonghuPhone;
		}

		/**
		* 获取： 用户身份证号
		*/
		public String getYonghuIdNumber() {
			return yonghuIdNumber;
		}
		/**
		* 设置： 用户身份证号
		*/
		public void setYonghuIdNumber(String yonghuIdNumber) {
			this.yonghuIdNumber = yonghuIdNumber;
		}

		/**
		* 获取： 邮箱
		*/
		public String getYonghuEmail() {
			return yonghuEmail;
		}
		/**
		* 设置： 邮箱
		*/
		public void setYonghuEmail(String yonghuEmail) {
			this.yonghuEmail = yonghuEmail;
		}

		/**
		* 获取： 假删
		*/
		public Integer getYonghuDelete() {
			return yonghuDelete;
		}
		/**
		* 设置： 假删
		*/
		public void setYonghuDelete(Integer yonghuDelete) {
			this.yonghuDelete = yonghuDelete;
		}
	//级联表的get和set 职业介绍

		/**
		* 获取： 职业名称
		*/
		public String getZhiyejieshaoName() {
			return zhiyejieshaoName;
		}
		/**
		* 设置： 职业名称
		*/
		public void setZhiyejieshaoName(String zhiyejieshaoName) {
			this.zhiyejieshaoName = zhiyejieshaoName;
		}

		/**
		* 获取： 职业封面
		*/
		public String getZhiyejieshaoPhoto() {
			return zhiyejieshaoPhoto;
		}
		/**
		* 设置： 职业封面
		*/
		public void setZhiyejieshaoPhoto(String zhiyejieshaoPhoto) {
			this.zhiyejieshaoPhoto = zhiyejieshaoPhoto;
		}
		/**
		* 获取： 职业
		*/
		public Integer getZhiyejieshaoTypes() {
			return zhiyejieshaoTypes;
		}
		/**
		* 设置： 职业
		*/
		public void setZhiyejieshaoTypes(Integer zhiyejieshaoTypes) {
			this.zhiyejieshaoTypes = zhiyejieshaoTypes;
		}


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

		/**
		* 获取： 职业详情
		*/
		public String getZhiyejieshaoContent() {
			return zhiyejieshaoContent;
		}
		/**
		* 设置： 职业详情
		*/
		public void setZhiyejieshaoContent(String zhiyejieshaoContent) {
			this.zhiyejieshaoContent = zhiyejieshaoContent;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getZhiyejieshaoDelete() {
			return zhiyejieshaoDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setZhiyejieshaoDelete(Integer zhiyejieshaoDelete) {
			this.zhiyejieshaoDelete = zhiyejieshaoDelete;
		}


	@Override
	public String toString() {
		return "ZhiyejieshaoCollectionView{" +
			", zhiyejieshaoCollectionValue=" + zhiyejieshaoCollectionValue +
			", zhiyejieshaoName=" + zhiyejieshaoName +
			", zhiyejieshaoPhoto=" + zhiyejieshaoPhoto +
			", zhiyejieshaoContent=" + zhiyejieshaoContent +
			", zhiyejieshaoDelete=" + zhiyejieshaoDelete +
			", yonghuName=" + yonghuName +
			", yonghuPhoto=" + yonghuPhoto +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuEmail=" + yonghuEmail +
			", yonghuDelete=" + yonghuDelete +
			"} " + super.toString();
	}
}
