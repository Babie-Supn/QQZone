package com.example.myssm.trans;

import com.example.myssm.util.ConnUtil;

import java.sql.Connection;

public class TransactionManager {

    //开启事务
    public static void beginTrans() throws Exception {
        Connection conn = ConnUtil.getConn();
        conn.setAutoCommit(false);

    }
    //提交事务
    public static void commit() throws Exception {
        Connection conn = ConnUtil.getConn();
        conn.commit();
        ConnUtil.closeConn();
    }
    //回滚事务
    public static void rollback() throws Exception {
        Connection conn = ConnUtil.getConn();
        conn.rollback();
        ConnUtil.closeConn();
    }
}
