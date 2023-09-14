package com.example.qqzone.pojo;

import java.sql.Date;

public class UserDetail {
    private Integer id;
    private String realName;
    private String tel;
    private String email;
    private Date birth;
    private String star;

    public UserDetail() {
    }
}
//父类：java.util.Date 年月日时分秒
//子类：java.sql.Date 年月日
//子类：java.sql.Time 时分秒
