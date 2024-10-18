package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.ExamrecordEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 答题记录表
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("examrecord")
public class ExamrecordView extends ExamrecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表

	//级联表 试卷表
		/**
		* 试卷名称
		*/

		@ColumnInfo(comment="试卷名称",type="varchar(200)")
		private String exampaperName;
		/**
		* 答题时长(分钟)
		*/

		@ColumnInfo(comment="答题时长(分钟)",type="int(11)")
		private Integer exampaperDate;
		/**
		* 试卷总分数
		*/

		@ColumnInfo(comment="试卷总分数",type="int(20)")
		private Integer exampaperMyscore;
		/**
		* 科目
		*/
		@ColumnInfo(comment="科目",type="int(11)")
		private Integer kemuTypes;
			/**
			* 科目的值
			*/
			@ColumnInfo(comment="科目的字典表值",type="varchar(200)")
			private String kemuValue;
		/**
		* 试卷状态
		*/
		@ColumnInfo(comment="试卷状态",type="int(11)")
		private Integer exampaperTypes;
			/**
			* 试卷状态的值
			*/
			@ColumnInfo(comment="试卷状态的字典表值",type="varchar(200)")
			private String exampaperValue;
		/**
		* 组卷方式
		*/
		@ColumnInfo(comment="组卷方式",type="int(11)")
		private Integer zujuanTypes;
			/**
			* 组卷方式的值
			*/
			@ColumnInfo(comment="组卷方式的字典表值",type="varchar(200)")
			private String zujuanValue;
		/**
		* 逻辑删除（1代表未删除 2代表已删除）
		*/

		@ColumnInfo(comment="逻辑删除（1代表未删除 2代表已删除）",type="int(255)")
		private Integer exampaperDelete;
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



	public ExamrecordView() {

	}

	public ExamrecordView(ExamrecordEntity examrecordEntity) {
		try {
			BeanUtils.copyProperties(this, examrecordEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}





	//级联表的get和set 试卷表

		/**
		* 获取： 试卷名称
		*/
		public String getExampaperName() {
			return exampaperName;
		}
		/**
		* 设置： 试卷名称
		*/
		public void setExampaperName(String exampaperName) {
			this.exampaperName = exampaperName;
		}

		/**
		* 获取： 答题时长(分钟)
		*/
		public Integer getExampaperDate() {
			return exampaperDate;
		}
		/**
		* 设置： 答题时长(分钟)
		*/
		public void setExampaperDate(Integer exampaperDate) {
			this.exampaperDate = exampaperDate;
		}

		/**
		* 获取： 试卷总分数
		*/
		public Integer getExampaperMyscore() {
			return exampaperMyscore;
		}
		/**
		* 设置： 试卷总分数
		*/
		public void setExampaperMyscore(Integer exampaperMyscore) {
			this.exampaperMyscore = exampaperMyscore;
		}
		/**
		* 获取： 科目
		*/
		public Integer getKemuTypes() {
			return kemuTypes;
		}
		/**
		* 设置： 科目
		*/
		public void setKemuTypes(Integer kemuTypes) {
			this.kemuTypes = kemuTypes;
		}


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
		/**
		* 获取： 试卷状态
		*/
		public Integer getExampaperTypes() {
			return exampaperTypes;
		}
		/**
		* 设置： 试卷状态
		*/
		public void setExampaperTypes(Integer exampaperTypes) {
			this.exampaperTypes = exampaperTypes;
		}


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
		/**
		* 获取： 组卷方式
		*/
		public Integer getZujuanTypes() {
			return zujuanTypes;
		}
		/**
		* 设置： 组卷方式
		*/
		public void setZujuanTypes(Integer zujuanTypes) {
			this.zujuanTypes = zujuanTypes;
		}


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

		/**
		* 获取： 逻辑删除（1代表未删除 2代表已删除）
		*/
		public Integer getExampaperDelete() {
			return exampaperDelete;
		}
		/**
		* 设置： 逻辑删除（1代表未删除 2代表已删除）
		*/
		public void setExampaperDelete(Integer exampaperDelete) {
			this.exampaperDelete = exampaperDelete;
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


	@Override
	public String toString() {
		return "ExamrecordView{" +
			", yonghuName=" + yonghuName +
			", yonghuPhoto=" + yonghuPhoto +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuEmail=" + yonghuEmail +
			", yonghuDelete=" + yonghuDelete +
			", exampaperName=" + exampaperName +
			", exampaperDate=" + exampaperDate +
			", exampaperMyscore=" + exampaperMyscore +
			", exampaperDelete=" + exampaperDelete +
			"} " + super.toString();
	}
}
