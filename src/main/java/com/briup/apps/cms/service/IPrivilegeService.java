package com.briup.apps.cms.service;

import com.briup.apps.cms.bean.Privilege;
import com.briup.apps.cms.exception.CustomerException;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IPrivilegeService {
    List<Privilege> findByParentId(long userId) throws CustomerException;
}
