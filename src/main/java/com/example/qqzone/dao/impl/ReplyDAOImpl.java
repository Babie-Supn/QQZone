package com.example.qqzone.dao.impl;

import com.example.myssm.baseDAO.BaseDAO;
import com.example.qqzone.dao.ReplyDAO;
import com.example.qqzone.pojo.Reply;
import com.example.qqzone.pojo.Topic;

import java.util.List;

public class ReplyDAOImpl extends BaseDAO<Reply> implements ReplyDAO {
    @Override
    public List<Reply> getReplyList(Topic topic) throws Exception {
      String sql="select * from q_reply where topic=?";
        return super.getForList(sql,topic);
    }

    @Override
    public void addReply(Reply reply) {

    }

    @Override
    public void delReply(Integer id) {

    }
}
