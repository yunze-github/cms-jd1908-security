package com.briup.apps.cms.service;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.extend.ArticleExtend;
import com.briup.apps.cms.exception.CustomerException;
import com.briup.apps.cms.utils.Message;

import java.util.List;

public interface IArticleService {

//级联
    List<ArticleExtend> findAllArticleInCategoryByCategoryId(long categoryId);

    List<ArticleExtend> findAllArticle();

    ArticleExtend findArtiacleInCateGoryWithCommentByID(long articleId);

    //根据articleId删除一篇文章
    void dropOne(Long articleId);

//单表

    //添加文章
    void saveOrUpdate(Article article) throws CustomerException;
}
