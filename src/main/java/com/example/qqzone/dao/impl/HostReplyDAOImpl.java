package com.example.qqzone.dao.impl;

import com.example.myssm.baseDAO.BaseDAO;
import com.example.qqzone.dao.HostReplyDAO;
import com.example.qqzone.pojo.HostReply;

public class HostReplyDAOImpl extends BaseDAO<HostReply> implements HostReplyDAO {
    @Override
    public HostReply getHostReplyByReplyByID(Integer replyID) throws Exception {
        String sql="select * from q_hostReply where reply=?";
        return super.getInstance(sql,replyID);
    }

    @Override
    public void delHostReplyById(Integer hostID) throws Exception {
        String sql="delete from q_hostReply where id=?";
        super.updateOrder(sql,hostID);
    }
}
