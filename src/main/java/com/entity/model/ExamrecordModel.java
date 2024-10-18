package com.entity.model;

import com.entity.ExamrecordEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 答题记录表
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ExamrecordModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 答题编号
     */
    private String examrecordUuidNumber;


    /**
     * 答题学生
     */
    private Integer yonghuId;


    /**
     * 所属试卷id（外键）
     */
    private Integer exampaperId;


    /**
     * 所得总分
     */
    private Integer totalScore;


    /**
     * 答题时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


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
	 * 获取：答题编号
	 */
    public String getExamrecordUuidNumber() {
        return examrecordUuidNumber;
    }


    /**
	 * 设置：答题编号
	 */
    public void setExamrecordUuidNumber(String examrecordUuidNumber) {
        this.examrecordUuidNumber = examrecordUuidNumber;
    }
    /**
	 * 获取：答题学生
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：答题学生
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：所属试卷id（外键）
	 */
    public Integer getExampaperId() {
        return exampaperId;
    }


    /**
	 * 设置：所属试卷id（外键）
	 */
    public void setExampaperId(Integer exampaperId) {
        this.exampaperId = exampaperId;
    }
    /**
	 * 获取：所得总分
	 */
    public Integer getTotalScore() {
        return totalScore;
    }


    /**
	 * 设置：所得总分
	 */
    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }
    /**
	 * 获取：答题时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：答题时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
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
