package com.example.myssm.baseDAO;

import com.example.myssm.util.ConnUtil;
import com.example.myssm.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public abstract class BaseDAO<T> {

    private Class<T> clazz = null;
    protected Connection conn;

    //    public BaseDAO(){
//
//    }
    //静态代码块里不能调非静态代码
    {
        //获取当前BaseDAO的子类继承的父类中的泛型
        Type genericSuperclass=this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType=(ParameterizedType) genericSuperclass;
        Type[] typeArguments=parameterizedType.getActualTypeArguments();//获取了父类的泛型参数
        clazz=(Class<T>)typeArguments[0];//泛型的第一个参数 即BaseDAO<T>的<T>
    }
//   public T getInstance( String sql, Object ...args) throws Exception{
//             conn = ConnUtil.getConn();
//            QueryRunner runner=new QueryRunner();
//            BeanHandler<T> handler=new BeanHandler<T>(clazz);
//            T query = runner.query(conn, sql, handler, args);
//            return query;
//
//    }
//
//    public List<T> getForList(String sql, Object...args) throws Exception {
//            conn=ConnUtil.getConn();
//            QueryRunner runner=new QueryRunner();
//            BeanListHandler<T> handler=new BeanListHandler<>(clazz);
//            List<T> list=runner.query(conn,sql,handler,args);
//            return list;
//    }
public T getInstance(String sql,Object...args) throws Exception {
    conn = ConnUtil.getConn();
    PreparedStatement ps = conn.prepareStatement(sql);
    for (int i = 0; i < args.length; i++) {
        ps.setObject(i + 1, args[i]);
    }

    ResultSet rs = ps.executeQuery();
    ResultSetMetaData rsmd = rs.getMetaData();
    int count = rsmd.getColumnCount();
   T t=null;
    if (rs.next()) {
        t = clazz.newInstance();
        for (int i = 0; i < count; i++) {
            Object object = rs.getObject(i + 1);
            String columnLabel = rsmd.getColumnLabel(i + 1);
            setValue(t, columnLabel, object);

        }
    }
    return t;
}
    public List<T> getForList(String sql, Object...args) throws Exception {
       conn= ConnUtil.getConn();
        PreparedStatement ps = conn.prepareStatement(sql);
        ArrayList<T> list=new ArrayList<>();
        for (int i=0;i< args.length;i++){
            ps.setObject(i+1,args[i]);
        }
        ResultSet rs = ps.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int count = rsmd.getColumnCount();
        while (rs.next()){
            T t=clazz.newInstance();
            for(int i=0;i<count;i++){
                Object object=rs.getObject(i+1);
                String columnLabel = rsmd.getColumnLabel(i+1);
                setValue(t,columnLabel,object);
            }
            list.add(t);
        }

        return list;
    }

    private void setValue(Object t,String label,Object obj) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException, ClassNotFoundException, NoSuchMethodException {

            Class clazz = t.getClass();
            Field field = clazz.getDeclaredField(label);
            if (field != null) {
                //先获取当前字段的名称
                String typeName = field.getType().getName();
                //判断如果是自定义类型，则需要调用这个自定义类的带一个参数的构造方法，创建出这个自定义的实例对象，然后将实例对象赋值给这个属性

                if (isMyType(typeName)) {
                    Class typeNameClass = Class.forName(typeName);
                    Constructor constructor = typeNameClass.getDeclaredConstructor(java.lang.Integer.class);
                    obj = constructor.newInstance(obj);
                }
                field.setAccessible(true);
                field.set(t, obj);

        }
    }
    private boolean isMyType(String typeName){
        if("java.lang.Integer".equals(typeName)){
            return false;
        } else if ("java.lang.String".equals(typeName)) {
            return false;
        }else if("java.lang.Date".equals(typeName)){
            return false;
        } else if ("java.sql.Date".equals(typeName)) {
            return false;
        } else if ("java.util.Date".equals(typeName)) {
            return false;
        } else if ("java.time.LocalDateTime".equals(typeName)) {
            return false;
        } else {
            return true;
        }
    }
    public int updateOrder(String sql,Object...args) throws Exception {
            conn=ConnUtil.getConn();
            QueryRunner runner=new QueryRunner();
            int count= runner.update(conn,sql,args);
            return count;

    }
    public <E> E getValue(String sql,Object...args) throws Exception {
            conn=ConnUtil.getConn();
           PreparedStatement ps=conn.prepareStatement(sql);
            for(int i=0;i<args.length;i++){
                ps.setObject(i+1,args[i]);
            }
           ResultSet rs =ps.executeQuery();
            if(rs.next()){
                return (E) rs.getObject(1);
            }
            JDBCUtils.closeResource(null,ps,rs);
       return null;
    }
    public void insertTable(String sql,Object ...args) throws Exception {
        conn=ConnUtil.getConn();
        PreparedStatement ps= conn.prepareStatement(sql);
        for (int i=0;i< args.length;i++){
            ps.setObject(i+1,args[i]);
        }
        ps.executeUpdate();
    }
}


