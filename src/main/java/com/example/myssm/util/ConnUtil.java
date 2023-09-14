package com.example.myssm.util;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnUtil {
    private  static ThreadLocal<Connection> threadLocal=new ThreadLocal<>();
    public static Connection getConn() throws Exception {
        Connection conn=threadLocal.get();
        if(conn==null){
            conn=JDBCUtils.getConnection();
            threadLocal.set(conn);
        }
        return threadLocal.get();
    }
    public static  void closeConn() throws SQLException {
        Connection conn=threadLocal.get();
        if(conn==null){
            return;
        }
        if(!conn.isClosed()){
            conn.close();
            threadLocal.set(null);
        }
    }
}
