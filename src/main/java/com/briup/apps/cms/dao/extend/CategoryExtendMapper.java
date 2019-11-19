package com.briup.apps.cms.dao.extend;

import com.briup.apps.cms.bean.Category;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * <h3>cms_jd1908</h3>
 * <p>栏目映射接口</p>
 *
 * @author : yunze
 * @date : 2019-11-14 14:43
 **/
public interface CategoryExtendMapper {

    List<Category> selectAllCategorys();

    void deleteCategoryIds(@Param("categoryIds") Long[] categoryIds);
}
