package com.briup.apps.cms.service;

import com.briup.apps.cms.bean.Comment;
import com.briup.apps.cms.exception.CustomerException;

import java.util.List;

public interface ICommentService {

    void save(Comment comment) throws CustomerException;

    void dropComments(Long[] ids) throws  CustomerException;
}
