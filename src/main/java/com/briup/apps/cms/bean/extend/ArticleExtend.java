package com.briup.apps.cms.bean.extend;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.bean.User;

import java.util.List;

public class ArticleExtend extends Article {

    public static final String STATUS_UNCHECK="未审核";
    public static final String STATUS_CHECK_PASS="审核未通过";
    public static final String STATUS_CHECK_UNPASS="审核通过";


    private User author;
    private Category category;
    private List<CommentExtend> commentExtends;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category categoryExtend) {
        this.category = category;
    }

    public List<CommentExtend> getCommentExtends() {
        return commentExtends;
    }

    public void setCommentExtends(List<CommentExtend> commentExtends) {
        this.commentExtends = commentExtends;
    }
}
