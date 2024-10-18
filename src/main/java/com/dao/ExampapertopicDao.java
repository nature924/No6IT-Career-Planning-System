package com.dao;

import com.entity.ExampapertopicEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ExampapertopicView;

/**
 * 试卷选题 Dao 接口
 *
 * @author 
 */
public interface ExampapertopicDao extends BaseMapper<ExampapertopicEntity> {

   List<ExampapertopicView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
