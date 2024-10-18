package com.entity.vo;

import com.entity.ExampapertopicEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 试卷选题
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("exampapertopic")
public class ExampapertopicVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 试卷
     */

    @TableField(value = "exampaper_id")
    private Integer exampaperId;


    /**
     * 试题
     */

    @TableField(value = "examquestion_id")
    private Integer examquestionId;


    /**
     * 试题分数
     */

    @TableField(value = "exampapertopic_number")
    private Integer exampapertopicNumber;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：试卷
	 */
    public Integer getExampaperId() {
        return exampaperId;
    }


    /**
	 * 获取：试卷
	 */

    public void setExampaperId(Integer exampaperId) {
        this.exampaperId = exampaperId;
    }
    /**
	 * 设置：试题
	 */
    public Integer getExamquestionId() {
        return examquestionId;
    }


    /**
	 * 获取：试题
	 */

    public void setExamquestionId(Integer examquestionId) {
        this.examquestionId = examquestionId;
    }
    /**
	 * 设置：试题分数
	 */
    public Integer getExampapertopicNumber() {
        return exampapertopicNumber;
    }


    /**
	 * 获取：试题分数
	 */

    public void setExampapertopicNumber(Integer exampapertopicNumber) {
        this.exampapertopicNumber = exampapertopicNumber;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
