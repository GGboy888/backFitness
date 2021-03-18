package com.igeek.wtfitness.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebServlet(name = "BasicServlet")
public class BasicServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //获取执行的方法名称
        String methodName = request.getParameter("method");
        Class<? extends BasicServlet> clazz = this.getClass();

        try{
            Method method = clazz.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

            method.invoke(this,request,response);

        } catch (NoSuchMethodException |IllegalAccessException |InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
