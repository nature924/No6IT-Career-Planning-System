package com.dao;

import com.entity.ZhiyejieshaoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ZhiyejieshaoView;

/**
 * 职业介绍 Dao 接口
 *
 * @author 
 */
public interface ZhiyejieshaoDao extends BaseMapper<ZhiyejieshaoEntity> {

   List<ZhiyejieshaoView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
