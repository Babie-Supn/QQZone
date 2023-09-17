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
        System.out.println(topic.getId());
        return super.getForList(sql,topic.getId());
    }

    @Override
    public void addReply(Reply reply) throws Exception {
      String sql="insert into q_reply values(0,?,?,?,?)";
      super.updateTable(sql,reply.getContent(),reply.getReplyDate(),reply.getAuthor().getId(),reply.getTopic().getId());
    }

    @Override
    public void delReplyByID(Integer id) throws Exception {
     String sql="delete from q_reply where id=?";
     super.updateTable(sql,id);
    }

    @Override
    public Reply getReplyById(Integer id) throws Exception {
        String sql="select * from q_reply where id=?";
        return super.getInstance(sql,id);
    }
    //
}
