package com.briup.apps.cms.service;

import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.exception.CustomerException;

import java.util.List;

public interface ICategoryService {

    List<Category> findAllCategorys();

    void saveOrUpdate(Category category) throws CustomerException;

    void dropCategory(Long categoryId) throws  CustomerException;

    void dropCategorys(Long[] categoryIds) throws  CustomerException;
}
