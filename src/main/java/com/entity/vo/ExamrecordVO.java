package com.entity.vo;

import com.entity.ExamrecordEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 答题记录表
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("examrecord")
public class ExamrecordVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 答题编号
     */

    @TableField(value = "examrecord_uuid_number")
    private String examrecordUuidNumber;


    /**
     * 答题学生
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 所属试卷id（外键）
     */

    @TableField(value = "exampaper_id")
    private Integer exampaperId;


    /**
     * 所得总分
     */

    @TableField(value = "total_score")
    private Integer totalScore;


    /**
     * 答题时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


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
	 * 设置：答题编号
	 */
    public String getExamrecordUuidNumber() {
        return examrecordUuidNumber;
    }


    /**
	 * 获取：答题编号
	 */

    public void setExamrecordUuidNumber(String examrecordUuidNumber) {
        this.examrecordUuidNumber = examrecordUuidNumber;
    }
    /**
	 * 设置：答题学生
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：答题学生
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：所属试卷id（外键）
	 */
    public Integer getExampaperId() {
        return exampaperId;
    }


    /**
	 * 获取：所属试卷id（外键）
	 */

    public void setExampaperId(Integer exampaperId) {
        this.exampaperId = exampaperId;
    }
    /**
	 * 设置：所得总分
	 */
    public Integer getTotalScore() {
        return totalScore;
    }


    /**
	 * 获取：所得总分
	 */

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }
    /**
	 * 设置：答题时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：答题时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
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
