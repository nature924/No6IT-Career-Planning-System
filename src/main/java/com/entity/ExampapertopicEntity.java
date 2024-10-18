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
 * 试卷选题
 *
 * @author 
 * @email
 */
@TableName("exampapertopic")
public class ExampapertopicEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ExampapertopicEntity() {

	}

	public ExampapertopicEntity(T t) {
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
     * 试卷
     */
    @ColumnInfo(comment="试卷",type="int(20)")
    @TableField(value = "exampaper_id")

    private Integer exampaperId;


    /**
     * 试题
     */
    @ColumnInfo(comment="试题",type="int(20)")
    @TableField(value = "examquestion_id")

    private Integer examquestionId;


    /**
     * 试题分数
     */
    @ColumnInfo(comment="试题分数",type="int(20)")
    @TableField(value = "exampapertopic_number")

    private Integer exampapertopicNumber;


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
	 * 获取：试卷
	 */
    public Integer getExampaperId() {
        return exampaperId;
    }
    /**
	 * 设置：试卷
	 */

    public void setExampaperId(Integer exampaperId) {
        this.exampaperId = exampaperId;
    }
    /**
	 * 获取：试题
	 */
    public Integer getExamquestionId() {
        return examquestionId;
    }
    /**
	 * 设置：试题
	 */

    public void setExamquestionId(Integer examquestionId) {
        this.examquestionId = examquestionId;
    }
    /**
	 * 获取：试题分数
	 */
    public Integer getExampapertopicNumber() {
        return exampapertopicNumber;
    }
    /**
	 * 设置：试题分数
	 */

    public void setExampapertopicNumber(Integer exampapertopicNumber) {
        this.exampapertopicNumber = exampapertopicNumber;
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
        return "Exampapertopic{" +
            ", id=" + id +
            ", exampaperId=" + exampaperId +
            ", examquestionId=" + examquestionId +
            ", exampapertopicNumber=" + exampapertopicNumber +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
