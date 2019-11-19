package com.briup.apps.cms.service;

import com.briup.apps.cms.bean.Role;
import com.briup.apps.cms.exception.CustomerException;

import java.util.List;

public interface IRoleService {
    List<Role> findAll() throws CustomerException;

    void saveOrUpdate(Role role) throws CustomerException;

    void drop(long[] ids) throws CustomerException;
}
