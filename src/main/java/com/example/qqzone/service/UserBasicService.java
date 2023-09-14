package com.example.qqzone.service;

import com.example.qqzone.pojo.UserBasic;

import java.util.List;

public interface UserBasicService {
    UserBasic login(String loginID,String pwd) throws Exception;

    List<UserBasic> getFriendList(UserBasic userBasic) throws Exception;

    //根据id获取指定用户信息
    UserBasic getUserByID(Integer id) throws Exception;

}
