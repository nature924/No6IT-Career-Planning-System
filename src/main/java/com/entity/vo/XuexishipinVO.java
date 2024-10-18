package com.entity.vo;

import com.entity.XuexishipinEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 课程学习
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("xuexishipin")
public class XuexishipinVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 课程名称
     */

    @TableField(value = "xuexishipin_name")
    private String xuexishipinName;


    /**
     * 课程封面
     */

    @TableField(value = "xuexishipin_photo")
    private String xuexishipinPhoto;


    /**
     * 课程类型
     */

    @TableField(value = "xuexishipin_types")
    private Integer xuexishipinTypes;


    /**
     * 职业
     */

    @TableField(value = "zhiyejieshao_types")
    private Integer zhiyejieshaoTypes;


    /**
     * 课程详情
     */

    @TableField(value = "xuexishipin_content")
    private String xuexishipinContent;


    /**
     * 逻辑删除
     */

    @TableField(value = "xuexishipin_delete")
    private Integer xuexishipinDelete;


    /**
     * 添加时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间 show1 show2 photoShow
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
	 * 设置：课程名称
	 */
    public String getXuexishipinName() {
        return xuexishipinName;
    }


    /**
	 * 获取：课程名称
	 */

    public void setXuexishipinName(String xuexishipinName) {
        this.xuexishipinName = xuexishipinName;
    }
    /**
	 * 设置：课程封面
	 */
    public String getXuexishipinPhoto() {
        return xuexishipinPhoto;
    }


    /**
	 * 获取：课程封面
	 */

    public void setXuexishipinPhoto(String xuexishipinPhoto) {
        this.xuexishipinPhoto = xuexishipinPhoto;
    }
    /**
	 * 设置：课程类型
	 */
    public Integer getXuexishipinTypes() {
        return xuexishipinTypes;
    }


    /**
	 * 获取：课程类型
	 */

    public void setXuexishipinTypes(Integer xuexishipinTypes) {
        this.xuexishipinTypes = xuexishipinTypes;
    }
    /**
	 * 设置：职业
	 */
    public Integer getZhiyejieshaoTypes() {
        return zhiyejieshaoTypes;
    }


    /**
	 * 获取：职业
	 */

    public void setZhiyejieshaoTypes(Integer zhiyejieshaoTypes) {
        this.zhiyejieshaoTypes = zhiyejieshaoTypes;
    }
    /**
	 * 设置：课程详情
	 */
    public String getXuexishipinContent() {
        return xuexishipinContent;
    }


    /**
	 * 获取：课程详情
	 */

    public void setXuexishipinContent(String xuexishipinContent) {
        this.xuexishipinContent = xuexishipinContent;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getXuexishipinDelete() {
        return xuexishipinDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setXuexishipinDelete(Integer xuexishipinDelete) {
        this.xuexishipinDelete = xuexishipinDelete;
    }
    /**
	 * 设置：添加时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：添加时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间 show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
