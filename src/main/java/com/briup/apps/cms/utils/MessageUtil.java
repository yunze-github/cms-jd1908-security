package com.briup.apps.cms.utils;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;

public class MessageUtil {

    public static final int STATUS_ERROR = 50000;
    public static final int STATUS_SUCCESS_QUERY_ONE = 20000;
    public static final int STATUS_SUCCESS_QUERY_ALL = 20001;
    public static final int STATUS_SUCCESS_SAVE = 20010;
    public static final int STATUS_SUCCESS_UPDATE = 20020;
    public static final int STATUS_SUCCESS_DELETE_ONE= 20030;
    public static final int STATUS_SUCCESS_DELETE_ALL= 20031;
    public static final int STATUS_SUCCESS_CHECK_NAME= 20040;
    public static final int STATUS_SUCCESS_CHECK_PASS= 20041;
    public static final int STATUS_SUCCESS_USER_ALTER= 20050;
    public static final int STATUS_SUCCESS_USER_REGISTER= 20050;

    public static final String MESSAGE_SUCCESS_QUERY_ONE ="成功查询一条!";
    public static final String MESSAGE_SUCCESS_QUERY_ALL ="成功查询多条!";
    public static final String MESSAGE_SUCCESS_SAVE ="成功保存!";
    public static final String MESSAGE_SUCCESS_UPDATE ="成功修改!";
    public static final String MESSAGE_SUCCESS_DELETE_ONE ="成功删除一条!";
    public static final String MESSAGE_SUCCESS_DELETE_ALL ="成功删除多条!";
    public static final String MESSAGE_SUCCESS_CHECK_NAME ="姓名检测通过!";
    public static final String MESSAGE_SUCCESS_CHECK_PASS ="用户登录成功!";
    public static final String MESSAGE_SUCCESS_USER_ALTER ="用户注册成功!";
    public static final String MESSAGE_SUCCESS_USER_REGISTER ="用户信息修改成功!";

    /**
     * 返回失败消息，一般用于增删改操作的结果返回
     * */
    public static Message error(String msg){
        Message message = new Message();
        message.setStatus(STATUS_ERROR);
        message.setMessage(msg);
        message.setTimestamp(new Date().getTime());
        return message;
    }

    /**
     * 返回成功消息，一般用于增删改操作的结果返回
     * */
    public static Message success(String msg,int status){
        Message message = new Message();
        message.setStatus(status);
        message.setMessage(msg);
        message.setTimestamp(new Date().getTime());
        return message;
    }
    /**
     * 返回成功的消息，一般用于查询操作的结果返回
     * */
    public static Message success(String msg,int status,Object data){
        Message message = new Message();
        message.setStatus(status);
        message.setMessage(msg);
        message.setData(data);
        message.setTimestamp(new Date().getTime());
        return message;
    }

}
