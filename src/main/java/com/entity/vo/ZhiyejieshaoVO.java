package com.entity.vo;

import com.entity.ZhiyejieshaoEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 职业介绍
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("zhiyejieshao")
public class ZhiyejieshaoVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 职业名称
     */

    @TableField(value = "zhiyejieshao_name")
    private String zhiyejieshaoName;


    /**
     * 职业封面
     */

    @TableField(value = "zhiyejieshao_photo")
    private String zhiyejieshaoPhoto;


    /**
     * 职业
     */

    @TableField(value = "zhiyejieshao_types")
    private Integer zhiyejieshaoTypes;


    /**
     * 职业详情
     */

    @TableField(value = "zhiyejieshao_content")
    private String zhiyejieshaoContent;


    /**
     * 逻辑删除
     */

    @TableField(value = "zhiyejieshao_delete")
    private Integer zhiyejieshaoDelete;


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
	 * 设置：职业名称
	 */
    public String getZhiyejieshaoName() {
        return zhiyejieshaoName;
    }


    /**
	 * 获取：职业名称
	 */

    public void setZhiyejieshaoName(String zhiyejieshaoName) {
        this.zhiyejieshaoName = zhiyejieshaoName;
    }
    /**
	 * 设置：职业封面
	 */
    public String getZhiyejieshaoPhoto() {
        return zhiyejieshaoPhoto;
    }


    /**
	 * 获取：职业封面
	 */

    public void setZhiyejieshaoPhoto(String zhiyejieshaoPhoto) {
        this.zhiyejieshaoPhoto = zhiyejieshaoPhoto;
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
	 * 设置：职业详情
	 */
    public String getZhiyejieshaoContent() {
        return zhiyejieshaoContent;
    }


    /**
	 * 获取：职业详情
	 */

    public void setZhiyejieshaoContent(String zhiyejieshaoContent) {
        this.zhiyejieshaoContent = zhiyejieshaoContent;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getZhiyejieshaoDelete() {
        return zhiyejieshaoDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setZhiyejieshaoDelete(Integer zhiyejieshaoDelete) {
        this.zhiyejieshaoDelete = zhiyejieshaoDelete;
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
