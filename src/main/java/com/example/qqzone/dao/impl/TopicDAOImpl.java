package com.example.qqzone.dao.impl;

import com.example.myssm.baseDAO.BaseDAO;
import com.example.qqzone.dao.TopicDAO;
import com.example.qqzone.pojo.Topic;
import com.example.qqzone.pojo.UserBasic;

import java.util.List;

public class TopicDAOImpl extends BaseDAO<Topic> implements TopicDAO {
    @Override
    public List<Topic> getTopicList(UserBasic userBasic) throws Exception {
        String sql="select * from q_topic where author=?";
        return super.getForList(sql,userBasic.getId());
    }

    @Override
    public void addTopic(Topic topic) {
        String sql="insert ";
    }

    @Override
    public void delTopic(Topic topic) {

    }

    @Override
    public Topic getTopic(Integer id) throws Exception {
        String sql="select * from q_topic where id=?";
        return super.getInstance(sql,id);
    }
}
