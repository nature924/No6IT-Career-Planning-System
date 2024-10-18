package com.entity.vo;

import com.entity.ExamredetailsEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 答题详情表
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("examredetails")
public class ExamredetailsVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 试卷编号
     */

    @TableField(value = "examredetails_uuid_number")
    private String examredetailsUuidNumber;


    /**
     * 学生id
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 试题id（外键）
     */

    @TableField(value = "examquestion_id")
    private Integer examquestionId;


    /**
     * 考生答案
     */

    @TableField(value = "examredetails_myanswer")
    private String examredetailsMyanswer;


    /**
     * 试题得分
     */

    @TableField(value = "examredetails_myscore")
    private Integer examredetailsMyscore;


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
	 * 设置：试卷编号
	 */
    public String getExamredetailsUuidNumber() {
        return examredetailsUuidNumber;
    }


    /**
	 * 获取：试卷编号
	 */

    public void setExamredetailsUuidNumber(String examredetailsUuidNumber) {
        this.examredetailsUuidNumber = examredetailsUuidNumber;
    }
    /**
	 * 设置：学生id
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：学生id
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：试题id（外键）
	 */
    public Integer getExamquestionId() {
        return examquestionId;
    }


    /**
	 * 获取：试题id（外键）
	 */

    public void setExamquestionId(Integer examquestionId) {
        this.examquestionId = examquestionId;
    }
    /**
	 * 设置：考生答案
	 */
    public String getExamredetailsMyanswer() {
        return examredetailsMyanswer;
    }


    /**
	 * 获取：考生答案
	 */

    public void setExamredetailsMyanswer(String examredetailsMyanswer) {
        this.examredetailsMyanswer = examredetailsMyanswer;
    }
    /**
	 * 设置：试题得分
	 */
    public Integer getExamredetailsMyscore() {
        return examredetailsMyscore;
    }


    /**
	 * 获取：试题得分
	 */

    public void setExamredetailsMyscore(Integer examredetailsMyscore) {
        this.examredetailsMyscore = examredetailsMyscore;
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
