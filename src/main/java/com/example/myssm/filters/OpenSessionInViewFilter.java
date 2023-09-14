package com.example.myssm.filters;

import com.example.myssm.trans.TransactionManager;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter("*.do")
public class OpenSessionInViewFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            TransactionManager.beginTrans();
            System.out.println("开启事务···");
            filterChain.doFilter(servletRequest,servletResponse);
            System.out.println("提交事务···");
            TransactionManager.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("回滚事务···");
                TransactionManager.rollback();
            } catch (Exception ex) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {

    }
}
