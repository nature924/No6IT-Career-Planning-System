package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 答题详情表
 *
 * @author 
 * @email
 */
@TableName("examredetails")
public class ExamredetailsEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ExamredetailsEntity() {

	}

	public ExamredetailsEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(20)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 试卷编号
     */
    @ColumnInfo(comment="试卷编号",type="varchar(200)")
    @TableField(value = "examredetails_uuid_number")

    private String examredetailsUuidNumber;


    /**
     * 学生id
     */
    @ColumnInfo(comment="学生id",type="int(20)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 试题id（外键）
     */
    @ColumnInfo(comment="试题id（外键）",type="int(20)")
    @TableField(value = "examquestion_id")

    private Integer examquestionId;


    /**
     * 考生答案
     */
    @ColumnInfo(comment="考生答案",type="varchar(200)")
    @TableField(value = "examredetails_myanswer")

    private String examredetailsMyanswer;


    /**
     * 试题得分
     */
    @ColumnInfo(comment="试题得分",type="int(20)")
    @TableField(value = "examredetails_myscore")

    private Integer examredetailsMyscore;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }
    /**
	 * 设置：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：试卷编号
	 */
    public String getExamredetailsUuidNumber() {
        return examredetailsUuidNumber;
    }
    /**
	 * 设置：试卷编号
	 */

    public void setExamredetailsUuidNumber(String examredetailsUuidNumber) {
        this.examredetailsUuidNumber = examredetailsUuidNumber;
    }
    /**
	 * 获取：学生id
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }
    /**
	 * 设置：学生id
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：试题id（外键）
	 */
    public Integer getExamquestionId() {
        return examquestionId;
    }
    /**
	 * 设置：试题id（外键）
	 */

    public void setExamquestionId(Integer examquestionId) {
        this.examquestionId = examquestionId;
    }
    /**
	 * 获取：考生答案
	 */
    public String getExamredetailsMyanswer() {
        return examredetailsMyanswer;
    }
    /**
	 * 设置：考生答案
	 */

    public void setExamredetailsMyanswer(String examredetailsMyanswer) {
        this.examredetailsMyanswer = examredetailsMyanswer;
    }
    /**
	 * 获取：试题得分
	 */
    public Integer getExamredetailsMyscore() {
        return examredetailsMyscore;
    }
    /**
	 * 设置：试题得分
	 */

    public void setExamredetailsMyscore(Integer examredetailsMyscore) {
        this.examredetailsMyscore = examredetailsMyscore;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Examredetails{" +
            ", id=" + id +
            ", examredetailsUuidNumber=" + examredetailsUuidNumber +
            ", yonghuId=" + yonghuId +
            ", examquestionId=" + examquestionId +
            ", examredetailsMyanswer=" + examredetailsMyanswer +
            ", examredetailsMyscore=" + examredetailsMyscore +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
