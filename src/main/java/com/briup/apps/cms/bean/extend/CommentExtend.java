package com.briup.apps.cms.bean.extend;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.Comment;
import com.briup.apps.cms.bean.User;

import java.util.List;

public class CommentExtend extends Comment {

    private UserExtend userExtend;
    private Article article;
    private List<CommentExtend> commentExtends;

    public UserExtend getUserExtend() {
        return userExtend;
    }

    public void setUserExtend(UserExtend userExtend) {
        this.userExtend = userExtend;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public List<CommentExtend> getCommentExtends() {
        return commentExtends;
    }

    public void setCommentExtends(List<CommentExtend> commentExtends) {
        this.commentExtends = commentExtends;
    }
}
