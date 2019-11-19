package com.briup.apps.cms.service.impl;

import com.briup.apps.cms.bean.Log;
import com.briup.apps.cms.bean.LogExample;
import com.briup.apps.cms.bean.extend.LogExtend;
import com.briup.apps.cms.dao.LogMapper;
import com.briup.apps.cms.dao.extend.LogExtendMapper;
import com.briup.apps.cms.exception.CustomerException;
import com.briup.apps.cms.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h3>cms_jd1908</h3>
 * <p>日志模块Service层实现接口</p>
 *
 * @author : yunze
 * @date : 2019-11-16 08:40
 **/
@Service
public class LogServiceImpl implements ILogService {

    @Autowired
    private LogMapper logMapper;
    @Autowired
    private LogExtendMapper logExtendMapper;

    //日志记录查询
    @Override
    public List<Log> findLogsByUserId(Long userId) {
        LogExample logExample = new LogExample();
        logExample.createCriteria().andUserIdEqualTo(userId);
        List<Log> list = logMapper.selectByExample(logExample);
        if (list.size()==0){
            throw new CustomerException("不存在用户操作日志记录!");
        }
        return list;
    }

    //日志记录修改或者查询
    @Override
    public void saveOrUpdate(Log log) throws CustomerException {
        System.out.println(log.getId());
        if (log.getId()==null){
            logMapper.insert(log);
        }else {
            LogExample logExample = new LogExample();
            logExample.createCriteria().andIdEqualTo(log.getId());
            if (logMapper.selectByExample(logExample).size()==0){
                throw new CustomerException("修改日志失败,不存在该日志！");
            }
            logMapper.updateByExample(log,logExample);
        }
    }

    //日志记录删除
    @Override
    public void dropLogs(Long[] ids) throws CustomerException {
        logExtendMapper.deleteLogs(ids);
    }


}
