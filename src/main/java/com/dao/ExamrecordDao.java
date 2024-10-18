package com.dao;

import com.entity.ExamrecordEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ExamrecordView;

/**
 * 答题记录表 Dao 接口
 *
 * @author 
 */
public interface ExamrecordDao extends BaseMapper<ExamrecordEntity> {

   List<ExamrecordView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
