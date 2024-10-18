package com.dao;

import com.entity.ZhiyejieshaoCollectionEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ZhiyejieshaoCollectionView;

/**
 * 职业介绍收藏 Dao 接口
 *
 * @author 
 */
public interface ZhiyejieshaoCollectionDao extends BaseMapper<ZhiyejieshaoCollectionEntity> {

   List<ZhiyejieshaoCollectionView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
