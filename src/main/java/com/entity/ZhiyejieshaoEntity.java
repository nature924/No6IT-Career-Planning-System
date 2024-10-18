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
 * 职业介绍
 *
 * @author 
 * @email
 */
@TableName("zhiyejieshao")
public class ZhiyejieshaoEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ZhiyejieshaoEntity() {

	}

	public ZhiyejieshaoEntity(T t) {
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
     * 职业名称
     */
    @ColumnInfo(comment="职业名称",type="varchar(200)")
    @TableField(value = "zhiyejieshao_name")

    private String zhiyejieshaoName;


    /**
     * 职业封面
     */
    @ColumnInfo(comment="职业封面",type="varchar(200)")
    @TableField(value = "zhiyejieshao_photo")

    private String zhiyejieshaoPhoto;


    /**
     * 职业
     */
    @ColumnInfo(comment="职业",type="int(11)")
    @TableField(value = "zhiyejieshao_types")

    private Integer zhiyejieshaoTypes;


    /**
     * 职业详情
     */
    @ColumnInfo(comment="职业详情",type="text")
    @TableField(value = "zhiyejieshao_content")

    private String zhiyejieshaoContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "zhiyejieshao_delete")

    private Integer zhiyejieshaoDelete;


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
	 * 获取：职业名称
	 */
    public String getZhiyejieshaoName() {
        return zhiyejieshaoName;
    }
    /**
	 * 设置：职业名称
	 */

    public void setZhiyejieshaoName(String zhiyejieshaoName) {
        this.zhiyejieshaoName = zhiyejieshaoName;
    }
    /**
	 * 获取：职业封面
	 */
    public String getZhiyejieshaoPhoto() {
        return zhiyejieshaoPhoto;
    }
    /**
	 * 设置：职业封面
	 */

    public void setZhiyejieshaoPhoto(String zhiyejieshaoPhoto) {
        this.zhiyejieshaoPhoto = zhiyejieshaoPhoto;
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
	 * 获取：职业详情
	 */
    public String getZhiyejieshaoContent() {
        return zhiyejieshaoContent;
    }
    /**
	 * 设置：职业详情
	 */

    public void setZhiyejieshaoContent(String zhiyejieshaoContent) {
        this.zhiyejieshaoContent = zhiyejieshaoContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getZhiyejieshaoDelete() {
        return zhiyejieshaoDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setZhiyejieshaoDelete(Integer zhiyejieshaoDelete) {
        this.zhiyejieshaoDelete = zhiyejieshaoDelete;
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
        return "Zhiyejieshao{" +
            ", id=" + id +
            ", zhiyejieshaoName=" + zhiyejieshaoName +
            ", zhiyejieshaoPhoto=" + zhiyejieshaoPhoto +
            ", zhiyejieshaoTypes=" + zhiyejieshaoTypes +
            ", zhiyejieshaoContent=" + zhiyejieshaoContent +
            ", zhiyejieshaoDelete=" + zhiyejieshaoDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
