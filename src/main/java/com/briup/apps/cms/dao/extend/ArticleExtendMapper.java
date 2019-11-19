package com.briup.apps.cms.dao.extend;

import com.briup.apps.cms.bean.extend.ArticleExtend;

import java.util.List;

public interface ArticleExtendMapper {

    //查询栏目下面的所有文章
    List<ArticleExtend> selectAllArticleWithCategoryByCategoryId(long categoryId);

    //查询所有文章(没有栏目，正在审核的文章)
    List<ArticleExtend> selectAllArticle();

    //详看某篇文章
    ArticleExtend selectArtiacleWithCateGoryAndCommentByID(long articleId);
}
