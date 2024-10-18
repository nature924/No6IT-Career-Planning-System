package com.entity.vo;

import com.entity.ExampaperEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 试卷表
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("exampaper")
public class ExampaperVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 试卷名称
     */

    @TableField(value = "exampaper_name")
    private String exampaperName;


    /**
     * 答题时长(分钟)
     */

    @TableField(value = "exampaper_date")
    private Integer exampaperDate;


    /**
     * 试卷总分数
     */

    @TableField(value = "exampaper_myscore")
    private Integer exampaperMyscore;


    /**
     * 科目
     */

    @TableField(value = "kemu_types")
    private Integer kemuTypes;


    /**
     * 试卷状态
     */

    @TableField(value = "exampaper_types")
    private Integer exampaperTypes;


    /**
     * 组卷方式
     */

    @TableField(value = "zujuan_types")
    private Integer zujuanTypes;


    /**
     * 逻辑删除（1代表未删除 2代表已删除）
     */

    @TableField(value = "exampaper_delete")
    private Integer exampaperDelete;


    /**
     * 创建时间 show2 photoShow
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
	 * 设置：试卷名称
	 */
    public String getExampaperName() {
        return exampaperName;
    }


    /**
	 * 获取：试卷名称
	 */

    public void setExampaperName(String exampaperName) {
        this.exampaperName = exampaperName;
    }
    /**
	 * 设置：答题时长(分钟)
	 */
    public Integer getExampaperDate() {
        return exampaperDate;
    }


    /**
	 * 获取：答题时长(分钟)
	 */

    public void setExampaperDate(Integer exampaperDate) {
        this.exampaperDate = exampaperDate;
    }
    /**
	 * 设置：试卷总分数
	 */
    public Integer getExampaperMyscore() {
        return exampaperMyscore;
    }


    /**
	 * 获取：试卷总分数
	 */

    public void setExampaperMyscore(Integer exampaperMyscore) {
        this.exampaperMyscore = exampaperMyscore;
    }
    /**
	 * 设置：科目
	 */
    public Integer getKemuTypes() {
        return kemuTypes;
    }


    /**
	 * 获取：科目
	 */

    public void setKemuTypes(Integer kemuTypes) {
        this.kemuTypes = kemuTypes;
    }
    /**
	 * 设置：试卷状态
	 */
    public Integer getExampaperTypes() {
        return exampaperTypes;
    }


    /**
	 * 获取：试卷状态
	 */

    public void setExampaperTypes(Integer exampaperTypes) {
        this.exampaperTypes = exampaperTypes;
    }
    /**
	 * 设置：组卷方式
	 */
    public Integer getZujuanTypes() {
        return zujuanTypes;
    }


    /**
	 * 获取：组卷方式
	 */

    public void setZujuanTypes(Integer zujuanTypes) {
        this.zujuanTypes = zujuanTypes;
    }
    /**
	 * 设置：逻辑删除（1代表未删除 2代表已删除）
	 */
    public Integer getExampaperDelete() {
        return exampaperDelete;
    }


    /**
	 * 获取：逻辑删除（1代表未删除 2代表已删除）
	 */

    public void setExampaperDelete(Integer exampaperDelete) {
        this.exampaperDelete = exampaperDelete;
    }
    /**
	 * 设置：创建时间 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
