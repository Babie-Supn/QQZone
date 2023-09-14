package com.example.qqzone.service.impl;

import com.example.qqzone.dao.UserBasicDAO;
import com.example.qqzone.pojo.UserBasic;
import com.example.qqzone.service.UserBasicService;

import java.util.ArrayList;
import java.util.List;

public class UserBasicServiceImpl implements UserBasicService {
    private UserBasicDAO userBasicDAO=null;
    @Override
    public UserBasic login(String loginID, String pwd) throws Exception {
        UserBasic userBasic = userBasicDAO.getUserBasic(loginID, pwd);
        return userBasic;
    }

    @Override
    public List<UserBasic> getFriendList(UserBasic userBasic) throws Exception {
       List<UserBasic> userBasicList= userBasicDAO.getUserBasicList(userBasic);//List列表为fid
        List<UserBasic> friends=new ArrayList<>(userBasicList.size());
       for(int i=0;i<userBasicList.size();i++){
           UserBasic friend=userBasicList.get(i);
           friend = userBasicDAO.getUserBasicByID(friend.getId());
           friends.add(friend);
       }

       return friends;

    }

    @Override
    public UserBasic getUserByID(Integer id) throws Exception {
        return userBasicDAO.getUserBasicByID(id);

    }
}
