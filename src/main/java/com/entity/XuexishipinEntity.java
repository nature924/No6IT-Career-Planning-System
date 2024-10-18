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
 * 课程学习
 *
 * @author 
 * @email
 */
@TableName("xuexishipin")
public class XuexishipinEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public XuexishipinEntity() {

	}

	public XuexishipinEntity(T t) {
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
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 课程名称
     */
    @ColumnInfo(comment="课程名称",type="varchar(200)")
    @TableField(value = "xuexishipin_name")

    private String xuexishipinName;


    /**
     * 课程封面
     */
    @ColumnInfo(comment="课程封面",type="varchar(200)")
    @TableField(value = "xuexishipin_photo")

    private String xuexishipinPhoto;


    /**
     * 学习视频
     */
    @ColumnInfo(comment="学习视频",type="varchar(255)")
    @TableField(value = "xuexishipin_video")

    private String xuexishipinVideo;


    /**
     * 课程类型
     */
    @ColumnInfo(comment="课程类型",type="int(11)")
    @TableField(value = "xuexishipin_types")

    private Integer xuexishipinTypes;


    /**
     * 职业
     */
    @ColumnInfo(comment="职业",type="int(11)")
    @TableField(value = "zhiyejieshao_types")

    private Integer zhiyejieshaoTypes;


    /**
     * 课程详情
     */
    @ColumnInfo(comment="课程详情",type="text")
    @TableField(value = "xuexishipin_content")

    private String xuexishipinContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "xuexishipin_delete")

    private Integer xuexishipinDelete;


    /**
     * 添加时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="添加时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


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
	 * 获取：课程名称
	 */
    public String getXuexishipinName() {
        return xuexishipinName;
    }
    /**
	 * 设置：课程名称
	 */

    public void setXuexishipinName(String xuexishipinName) {
        this.xuexishipinName = xuexishipinName;
    }
    /**
	 * 获取：课程封面
	 */
    public String getXuexishipinPhoto() {
        return xuexishipinPhoto;
    }
    /**
	 * 设置：课程封面
	 */

    public void setXuexishipinPhoto(String xuexishipinPhoto) {
        this.xuexishipinPhoto = xuexishipinPhoto;
    }
    /**
	 * 获取：学习视频
	 */
    public String getXuexishipinVideo() {
        return xuexishipinVideo;
    }
    /**
	 * 设置：学习视频
	 */

    public void setXuexishipinVideo(String xuexishipinVideo) {
        this.xuexishipinVideo = xuexishipinVideo;
    }
    /**
	 * 获取：课程类型
	 */
    public Integer getXuexishipinTypes() {
        return xuexishipinTypes;
    }
    /**
	 * 设置：课程类型
	 */

    public void setXuexishipinTypes(Integer xuexishipinTypes) {
        this.xuexishipinTypes = xuexishipinTypes;
    }
    /**
	 * 获取：职业
	 */
    public Integer getZhiyejieshaoTypes() {
        return zhiyejieshaoTypes;
    }
    /**
	 * 设置：职业
	 */

    public void setZhiyejieshaoTypes(Integer zhiyejieshaoTypes) {
        this.zhiyejieshaoTypes = zhiyejieshaoTypes;
    }
    /**
	 * 获取：课程详情
	 */
    public String getXuexishipinContent() {
        return xuexishipinContent;
    }
    /**
	 * 设置：课程详情
	 */

    public void setXuexishipinContent(String xuexishipinContent) {
        this.xuexishipinContent = xuexishipinContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getXuexishipinDelete() {
        return xuexishipinDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setXuexishipinDelete(Integer xuexishipinDelete) {
        this.xuexishipinDelete = xuexishipinDelete;
    }
    /**
	 * 获取：添加时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：添加时间
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

    @Override
    public String toString() {
        return "Xuexishipin{" +
            ", id=" + id +
            ", xuexishipinName=" + xuexishipinName +
            ", xuexishipinPhoto=" + xuexishipinPhoto +
            ", xuexishipinVideo=" + xuexishipinVideo +
            ", xuexishipinTypes=" + xuexishipinTypes +
            ", zhiyejieshaoTypes=" + zhiyejieshaoTypes +
            ", xuexishipinContent=" + xuexishipinContent +
            ", xuexishipinDelete=" + xuexishipinDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
