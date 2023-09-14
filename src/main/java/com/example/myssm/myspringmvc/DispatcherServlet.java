package com.example.myssm.myspringmvc;

import com.example.myssm.ioc.BeanFactory;
import com.example.myssm.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet{
    private BeanFactory beanFactory;
   public void init() throws ServletException {
       super.init();
       //之前是在此处主动创建IOC容器的
       //现在优化为从application作用域去获取

//      beanFactory=new ClassPathXmlApplicationContext();
       ServletContext application=getServletContext();
       Object beanFactoryObj = application.getAttribute("beanFactory");
       if(beanFactoryObj!=null){
           beanFactory=(BeanFactory) beanFactoryObj;
       }else{
           throw new RuntimeException("IOC容器获取失败");
       }
   }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String servletPath = req.getServletPath(); //servletPath 是:/hello.do
        servletPath = servletPath.substring(1);
        int lastIndexOf = servletPath.lastIndexOf(".do");
        servletPath=servletPath.substring(0,lastIndexOf);//这样 servletPath就成为了 hello
        System.out.println(servletPath);
        Object controllerBeanObj = beanFactory.getBeanByID(servletPath);
        String operate=req.getParameter("operate");
        if(StringUtils.isEmpty(operate)){
            operate="index";
        }
        //获取当前类中所有的方法
        try {
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
            for(Method method:methods){
                if(operate.equals(method.getName())){
                    //1.统一获取请求参数

                    //获取当前方法的参数，返回参数数组
                   Parameter[] parameters= method.getParameters();
                   //用parameterValues用来承载参数的值
                   Object[] parameterValues=new Object[parameters.length];

                   for(int i=0;i< parameters.length;i++){
                       Parameter parameter=parameters[i];

                      String parameterName= parameter.getName();
                       if ("req".equals(parameterName)) {
                           parameterValues[i]=req;
                       } else if ("resp".equals(parameterName)) {
                           parameterValues[i]=resp;
                       } else if ("session".equals(parameterName)) {
                           parameterValues[i]=req.getSession();
                       }else {
                           String parameterValue = req.getParameter(parameterName);
                           String typeName = parameter.getType().getName();
                           Object parameterObj=parameterValue;
                           if(parameterObj!=null) {
                               if ("java.lang.Integer".equals(typeName)) {
                                   parameterObj = Integer.parseInt(parameterValue);
                               } else if ("double".equals(typeName)) {
                                   parameterObj = Double.parseDouble(parameterValue);
                               }
                           }
                           parameterValues[i]=parameterObj;
                       }
                   }
                   //2.controller组件中方法调用
                    method.setAccessible(true);
                    Object returnObj=method.invoke(controllerBeanObj,parameterValues);

                    String mrstr=(String) returnObj;
                    //3.视图处理
                    if(mrstr.startsWith("redirect:")){  //redirect:fruit.do
                        String redirectStr = mrstr.substring("redirect:".length());
                        resp.sendRedirect(redirectStr);
                    }else{
                        super.processTemplate(mrstr,req,resp);
                    }
                }
            }
                //获取当前方法的参数，返回参数数组
//            }else {
//                throw new RuntimeException("operate值非法！");
//            }
        } catch ( Exception e) {
            e.printStackTrace();
            throw new DispatcherServletException("DispatcherServlet 出错了···");
        }
    }
}
//常见错误：IllegalArgumentException: argument type mismatch：获取到的参数不匹配
/**
 *
 *
 */
