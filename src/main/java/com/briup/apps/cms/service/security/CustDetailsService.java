package com.briup.apps.cms.service.security;

import com.briup.apps.cms.bean.Role;
import com.briup.apps.cms.bean.extend.RoleExtend;
import com.briup.apps.cms.bean.extend.UserExtend;
import com.briup.apps.cms.bean.security.UserSimple;
import com.briup.apps.cms.dao.extend.UserExtendMapper;
import com.briup.apps.cms.dao.extend.UserSimpleMapper;
import com.briup.apps.cms.exception.CustomerException;
import com.briup.apps.cms.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.protobuf.ProtobufEncoder;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import sun.misc.CEFormatException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IUserService userService;
    @Autowired
    private UserSimpleMapper userSimpleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

        //监测用户姓名是否存在
        userService.checkName(username);

        // TODO 根据用户名，查找到对应的密码，与权限
        UserSimple userSimple = userSimpleMapper.selectUserSimpleByName(username);

        logger.info("用户的用户名: {}", userSimple.getUsername());
        logger.info("用户匹配的密码: {}", userSimple.getPassword());
        logger.info("用户匹配的密码: {}", new BCryptPasswordEncoder().encode(userSimple.getPassword()));
        if (userSimple.getRoles().size()==0) {
            throw new CustomerException("该用户没有权限!");
        }
        // 封装用户信息，并返回。参数分别是：用户名，密码，用户权限
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
        for(Role role:userSimple.getRoles())
        {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(userSimple.getUsername(),
                new BCryptPasswordEncoder().encode(userSimple.getPassword()), authorities);

  }
}
