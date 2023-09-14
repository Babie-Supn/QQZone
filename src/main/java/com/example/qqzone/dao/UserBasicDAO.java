package com.example.qqzone.dao;

import com.example.qqzone.pojo.UserBasic;

import java.util.List;

public interface UserBasicDAO {
    //根据账号 密码 获取我们的特定用户信息
    public UserBasic getUserBasic(String loginID,String pwd) throws Exception;

    //获取指定用户的所有好友列表
    public List<UserBasic> getUserBasicList(UserBasic userBasic) throws Exception;

    //根据id查询userBasic的信息
    public  UserBasic getUserBasicByID(Integer id) throws Exception;


}
