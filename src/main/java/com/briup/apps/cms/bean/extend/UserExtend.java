package com.briup.apps.cms.bean.extend;

import com.briup.apps.cms.bean.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserExtend extends User{
    private List<RoleExtend> roleExtends;
    private List<Log> logs;
    private List<CommentExtend> commentExtends;
    private List<ArticleExtend> articleExtends;

    public List<RoleExtend> getRoleExtends() {
        return roleExtends;
    }

    public void setRoleExtends(List<RoleExtend> roleExtends) {
        this.roleExtends = roleExtends;
    }

    public List<Log> getLogs() {
        return logs;
    }

    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }

    public List<CommentExtend> getCommentExtends() {
        return commentExtends;
    }

    public void setCommentExtends(List<CommentExtend> commentExtends) {
        this.commentExtends = commentExtends;
    }

    public List<ArticleExtend> getArticleExtends() {
        return articleExtends;
    }

    public void setArticleExtends(List<ArticleExtend> articleExtends) {
        this.articleExtends = articleExtends;
    }


}
