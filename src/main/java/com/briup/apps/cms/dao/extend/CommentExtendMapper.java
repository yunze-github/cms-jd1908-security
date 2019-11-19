package com.briup.apps.cms.dao.extend;

import com.briup.apps.cms.bean.extend.CommentExtend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentExtendMapper {

    //查看文章的所有评论
    List<CommentExtend> selectCommentsByArticleId(Long articleId);


    void deleteComments(@Param("ids") Long[] ids);
}
