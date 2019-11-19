package com.briup.apps.cms.service;

import com.briup.apps.cms.bean.Log;
import com.briup.apps.cms.exception.CustomerException;

import java.util.List;

public interface ILogService {
    List<Log> findLogsByUserId(Long userId) throws CustomerException;

    void saveOrUpdate(Log log) throws CustomerException;

    void dropLogs(Long[] ids) throws CustomerException;
}
