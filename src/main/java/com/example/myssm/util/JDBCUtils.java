package com.example.myssm.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

    public class JDBCUtils {
        private static DataSource source;
        static {
            try {
                Properties pros=new Properties();
                InputStream is=JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
                pros.load(is);
                source= DruidDataSourceFactory.createDataSource(pros);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public static Connection getConnection()throws  Exception{
            Connection conn= source.getConnection();
            return conn;
        }
        public static void closeResource(Connection conn, PreparedStatement ps, ResultSet rs){
            DbUtils.closeQuietly(conn);
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(ps);
        }
        public static void closeResource(Connection conn,PreparedStatement ps){
            DbUtils.closeQuietly(conn);
            DbUtils.closeQuietly(ps);
        }

    }


