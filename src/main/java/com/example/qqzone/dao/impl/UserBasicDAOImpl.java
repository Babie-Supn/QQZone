package com.example.qqzone.dao.impl;

import com.example.myssm.baseDAO.BaseDAO;
import com.example.qqzone.dao.UserBasicDAO;
import com.example.qqzone.pojo.UserBasic;

import java.util.List;

public class UserBasicDAOImpl extends BaseDAO<UserBasic> implements UserBasicDAO {

    @Override
    public UserBasic getUserBasic(String loginID, String pwd) throws Exception {
        String sql="select * from q_user_basic where loginID=? and pwd=?";
        return super.getInstance(sql,loginID,pwd);
    }

    @Override
    public List<UserBasic> getUserBasicList(UserBasic userBasic) throws Exception {
        String sql="select fid as 'id' from q_friend where uid=?";
        return super.getForList(sql,userBasic.getId());
    }

    @Override
    public UserBasic getUserBasicByID(Integer id) throws Exception {
        String sql="select * from q_user_basic where id=?";
        return super.getInstance(sql,id);
    }
}
