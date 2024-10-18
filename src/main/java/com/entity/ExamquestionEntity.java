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
 * 试题表
 *
 * @author 
 * @email
 */
@TableName("examquestion")
public class ExamquestionEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ExamquestionEntity() {

	}

	public ExamquestionEntity(T t) {
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
     * 试题名称
     */
    @ColumnInfo(comment="试题名称",type="varchar(200)")
    @TableField(value = "examquestion_name")

    private String examquestionName;


    /**
     * 科目
     */
    @ColumnInfo(comment="科目",type="int(11)")
    @TableField(value = "kemu_types")

    private Integer kemuTypes;


    /**
     * 选项，json字符串
     */
    @ColumnInfo(comment="选项，json字符串",type="longtext")
    @TableField(value = "examquestion_options")

    private String examquestionOptions;


    /**
     * 正确答案
     */
    @ColumnInfo(comment="正确答案",type="varchar(200)")
    @TableField(value = "examquestion_answer")

    private String examquestionAnswer;


    /**
     * 答案解析
     */
    @ColumnInfo(comment="答案解析",type="longtext")
    @TableField(value = "examquestion_analysis")

    private String examquestionAnalysis;


    /**
     * 试题类型
     */
    @ColumnInfo(comment="试题类型",type="int(20)")
    @TableField(value = "examquestion_types")

    private Integer examquestionTypes;


    /**
     * 试题排序，值越大排越前面
     */
    @ColumnInfo(comment="试题排序，值越大排越前面",type="int(20)")
    @TableField(value = "examquestion_sequence")

    private Integer examquestionSequence;


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
	 * 获取：试题名称
	 */
    public String getExamquestionName() {
        return examquestionName;
    }
    /**
	 * 设置：试题名称
	 */

    public void setExamquestionName(String examquestionName) {
        this.examquestionName = examquestionName;
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
	 * 获取：选项，json字符串
	 */
    public String getExamquestionOptions() {
        return examquestionOptions;
    }
    /**
	 * 设置：选项，json字符串
	 */

    public void setExamquestionOptions(String examquestionOptions) {
        this.examquestionOptions = examquestionOptions;
    }
    /**
	 * 获取：正确答案
	 */
    public String getExamquestionAnswer() {
        return examquestionAnswer;
    }
    /**
	 * 设置：正确答案
	 */

    public void setExamquestionAnswer(String examquestionAnswer) {
        this.examquestionAnswer = examquestionAnswer;
    }
    /**
	 * 获取：答案解析
	 */
    public String getExamquestionAnalysis() {
        return examquestionAnalysis;
    }
    /**
	 * 设置：答案解析
	 */

    public void setExamquestionAnalysis(String examquestionAnalysis) {
        this.examquestionAnalysis = examquestionAnalysis;
    }
    /**
	 * 获取：试题类型
	 */
    public Integer getExamquestionTypes() {
        return examquestionTypes;
    }
    /**
	 * 设置：试题类型
	 */

    public void setExamquestionTypes(Integer examquestionTypes) {
        this.examquestionTypes = examquestionTypes;
    }
    /**
	 * 获取：试题排序，值越大排越前面
	 */
    public Integer getExamquestionSequence() {
        return examquestionSequence;
    }
    /**
	 * 设置：试题排序，值越大排越前面
	 */

    public void setExamquestionSequence(Integer examquestionSequence) {
        this.examquestionSequence = examquestionSequence;
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
        return "Examquestion{" +
            ", id=" + id +
            ", examquestionName=" + examquestionName +
            ", kemuTypes=" + kemuTypes +
            ", examquestionOptions=" + examquestionOptions +
            ", examquestionAnswer=" + examquestionAnswer +
            ", examquestionAnalysis=" + examquestionAnalysis +
            ", examquestionTypes=" + examquestionTypes +
            ", examquestionSequence=" + examquestionSequence +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
