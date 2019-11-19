package com.briup.apps.cms.service.impl;

import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.bean.CategoryExample;
import com.briup.apps.cms.bean.extend.CategoryExtend;
import com.briup.apps.cms.dao.CategoryMapper;
import com.briup.apps.cms.dao.extend.CategoryExtendMapper;
import com.briup.apps.cms.exception.CustomerException;
import com.briup.apps.cms.service.ICategoryService;
import org.apache.el.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <h3>cms_jd1908</h3>
 * <p>栏目接口实现类</p>
 *
 * @author : yunze
 * @date : 2019-11-14 14:28
 **/

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private CategoryExtendMapper categoryExtendMapper;

    @Override
    public List<Category> findAllCategorys() {
        return categoryExtendMapper.selectAllCategorys();
    }

    @Override
    public void saveOrUpdate(Category category) throws CustomerException {
        if (category.getId()!=null) {
            categoryMapper.updateByPrimaryKey(category);
        }else {
            CategoryExample categoryExample = new CategoryExample();
            categoryExample.createCriteria().andNameEqualTo(category.getName());
            List<Category> list = categoryMapper.selectByExample(categoryExample);
            if (list.size()>0) {
                throw new CustomerException("栏目标题重复!");
            }
            categoryMapper.insert(category);
        }
    }

    @Override
    public void dropCategory(Long categoryId) throws CustomerException {
        if (categoryMapper.selectByPrimaryKey(categoryId)==null) {
            throw new CustomerException("栏目不存在!");
        }
        categoryMapper.deleteByPrimaryKey(categoryId);
    }

    @Override
    public void dropCategorys(Long[] categoryIds) throws CustomerException {
        categoryExtendMapper.deleteCategoryIds(categoryIds);
    }
}
