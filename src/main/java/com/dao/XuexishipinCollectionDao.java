package com.dao;

import com.entity.XuexishipinCollectionEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.XuexishipinCollectionView;

/**
 * 课程收藏 Dao 接口
 *
 * @author 
 */
public interface XuexishipinCollectionDao extends BaseMapper<XuexishipinCollectionEntity> {

   List<XuexishipinCollectionView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
