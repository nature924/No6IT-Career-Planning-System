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
 * 试卷表
 *
 * @author 
 * @email
 */
@TableName("exampaper")
public class ExampaperEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ExampaperEntity() {

	}

	public ExampaperEntity(T t) {
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
     * 试卷名称
     */
    @ColumnInfo(comment="试卷名称",type="varchar(200)")
    @TableField(value = "exampaper_name")

    private String exampaperName;


    /**
     * 答题时长(分钟)
     */
    @ColumnInfo(comment="答题时长(分钟)",type="int(11)")
    @TableField(value = "exampaper_date")

    private Integer exampaperDate;


    /**
     * 试卷总分数
     */
    @ColumnInfo(comment="试卷总分数",type="int(20)")
    @TableField(value = "exampaper_myscore")

    private Integer exampaperMyscore;


    /**
     * 科目
     */
    @ColumnInfo(comment="科目",type="int(11)")
    @TableField(value = "kemu_types")

    private Integer kemuTypes;


    /**
     * 试卷状态
     */
    @ColumnInfo(comment="试卷状态",type="int(11)")
    @TableField(value = "exampaper_types")

    private Integer exampaperTypes;


    /**
     * 组卷方式
     */
    @ColumnInfo(comment="组卷方式",type="int(11)")
    @TableField(value = "zujuan_types")

    private Integer zujuanTypes;


    /**
     * 逻辑删除（1代表未删除 2代表已删除）
     */
    @ColumnInfo(comment="逻辑删除（1代表未删除 2代表已删除）",type="int(255)")
    @TableField(value = "exampaper_delete")

    private Integer exampaperDelete;


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
	 * 获取：试卷名称
	 */
    public String getExampaperName() {
        return exampaperName;
    }
    /**
	 * 设置：试卷名称
	 */

    public void setExampaperName(String exampaperName) {
        this.exampaperName = exampaperName;
    }
    /**
	 * 获取：答题时长(分钟)
	 */
    public Integer getExampaperDate() {
        return exampaperDate;
    }
    /**
	 * 设置：答题时长(分钟)
	 */

    public void setExampaperDate(Integer exampaperDate) {
        this.exampaperDate = exampaperDate;
    }
    /**
	 * 获取：试卷总分数
	 */
    public Integer getExampaperMyscore() {
        return exampaperMyscore;
    }
    /**
	 * 设置：试卷总分数
	 */

    public void setExampaperMyscore(Integer exampaperMyscore) {
        this.exampaperMyscore = exampaperMyscore;
    }
    /**
	 * 获取：科目
	 */
    public Integer getKemuTypes() {
        return kemuTypes;
    }
    /**
	 * 设置：科目
	 */

    public void setKemuTypes(Integer kemuTypes) {
        this.kemuTypes = kemuTypes;
    }
    /**
	 * 获取：试卷状态
	 */
    public Integer getExampaperTypes() {
        return exampaperTypes;
    }
    /**
	 * 设置：试卷状态
	 */

    public void setExampaperTypes(Integer exampaperTypes) {
        this.exampaperTypes = exampaperTypes;
    }
    /**
	 * 获取：组卷方式
	 */
    public Integer getZujuanTypes() {
        return zujuanTypes;
    }
    /**
	 * 设置：组卷方式
	 */

    public void setZujuanTypes(Integer zujuanTypes) {
        this.zujuanTypes = zujuanTypes;
    }
    /**
	 * 获取：逻辑删除（1代表未删除 2代表已删除）
	 */
    public Integer getExampaperDelete() {
        return exampaperDelete;
    }
    /**
	 * 设置：逻辑删除（1代表未删除 2代表已删除）
	 */

    public void setExampaperDelete(Integer exampaperDelete) {
        this.exampaperDelete = exampaperDelete;
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
        return "Exampaper{" +
            ", id=" + id +
            ", exampaperName=" + exampaperName +
            ", exampaperDate=" + exampaperDate +
            ", exampaperMyscore=" + exampaperMyscore +
            ", kemuTypes=" + kemuTypes +
            ", exampaperTypes=" + exampaperTypes +
            ", zujuanTypes=" + zujuanTypes +
            ", exampaperDelete=" + exampaperDelete +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
