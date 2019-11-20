package com.briup.apps.cms.service;

import com.briup.apps.cms.bean.Role;
import com.briup.apps.cms.bean.User;
import com.briup.apps.cms.bean.extend.UserExtend;
import com.briup.apps.cms.bean.security.UserSimple;
import com.briup.apps.cms.exception.CustomerException;

import java.util.List;


public interface IUserService {

    User findOne(Long id);

    void checkName(String username) throws CustomerException;

    User login(UserSimple userSimple) throws CustomerException;

    void save(User user) throws  CustomerException;

    void update(User user) throws CustomerException;

    void drop(Long id) throws CustomerException;

    List<UserExtend> findRoles(Long id);
}
