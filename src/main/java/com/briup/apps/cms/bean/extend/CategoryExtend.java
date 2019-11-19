package com.briup.apps.cms.bean.extend;

import com.briup.apps.cms.bean.Category;

import java.util.List;

public class CategoryExtend extends Category {
    private List<CategoryExtend> categoryExtends;
    private  List<ArticleExtend> articleExtends;

    public List<CategoryExtend> getCategoryExtends() {
        return categoryExtends;
    }

    public void setCategoryExtends(List<CategoryExtend> categoryExtends) {
        this.categoryExtends = categoryExtends;
    }

    public List<ArticleExtend> getArticleExtends() {
        return articleExtends;
    }

    public void setArticleExtends(List<ArticleExtend> articleExtends) {
        this.articleExtends = articleExtends;
    }
}
