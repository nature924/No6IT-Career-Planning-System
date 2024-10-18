package com.entity.model;

import com.entity.ExampapertopicEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 试卷选题
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ExampapertopicModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 试卷
     */
    private Integer exampaperId;


    /**
     * 试题
     */
    private Integer examquestionId;


    /**
     * 试题分数
     */
    private Integer exampapertopicNumber;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
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

    }
