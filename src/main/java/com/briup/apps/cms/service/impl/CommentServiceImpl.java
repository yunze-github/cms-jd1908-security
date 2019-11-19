package com.briup.apps.cms.service.impl;

import com.briup.apps.cms.bean.Comment;
import com.briup.apps.cms.dao.CommentMapper;
import com.briup.apps.cms.dao.extend.CommentExtendMapper;
import com.briup.apps.cms.exception.CustomerException;
import com.briup.apps.cms.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h3>cms_jd1908</h3>
 * <p>评论Service层接口实现类</p>
 *
 * @author : yunze
 * @date : 2019-11-16 10:28
 **/
@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentExtendMapper commentExtendMapper;

    //添加评论
    @Override
    public void save(Comment comment) throws CustomerException {
        commentMapper.insert(comment);
    }

    //删除评论
    @Override
    public void dropComments(Long[] ids) throws CustomerException {
        commentExtendMapper.deleteComments(ids);
    }
}
