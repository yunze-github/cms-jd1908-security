package com.briup.apps.cms.dao.extend;

import org.apache.ibatis.annotations.Param;

/**
 * <h3>cms_jd1908</h3>
 * <p>日志复杂映射接口</p>
 *
 * @author : yunze
 * @date : 2019-11-16 09:32
 **/
public interface LogExtendMapper {
    void deleteLogs(@Param("ids") Long[] ids);
}
