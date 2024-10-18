package com.entity.model;

import com.entity.ZhiyejieshaoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 职业介绍
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ZhiyejieshaoModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 职业名称
     */
    private String zhiyejieshaoName;


    /**
     * 职业封面
     */
    private String zhiyejieshaoPhoto;


    /**
     * 职业
     */
    private Integer zhiyejieshaoTypes;


    /**
     * 职业详情
     */
    private String zhiyejieshaoContent;


    /**
     * 逻辑删除
     */
    private Integer zhiyejieshaoDelete;


    /**
     * 添加时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间 show1 show2 photoShow
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
	 * 获取：创建时间 show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
