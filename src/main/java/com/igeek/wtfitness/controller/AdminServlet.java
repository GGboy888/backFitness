package com.igeek.wtfitness.controller;

import com.igeek.wtfitness.entity.Admin;
import com.igeek.wtfitness.service.AdminService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "AdminServlet",urlPatterns = "/admin")
public class AdminServlet extends BasicServlet {
    private AdminService service = new AdminService();

    //用户登录
    public void login(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        //获得请求参数
        String adminId = request.getParameter("username");
        String password = request.getParameter("password");
        Admin admin = service.login(adminId, password);
        if (admin!=null){
            //将当前查询到的用户信息存储值会话中
            HttpSession session = request.getSession();
            session.setAttribute("admin",admin);
            request.getRequestDispatcher("jsp/index.jsp").forward(request,response);
        }else {
            request.setAttribute("msg","用户名与密码不匹配");
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }

    }

    //用户登出
    public void logout(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //销毁会话
        session.invalidate();

        request.getRequestDispatcher("login.jsp").forward(request,response);

    }

    public void upPassword(HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String newPasswordAgain = request.getParameter("newPasswordAgain");
        Pattern p = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!.%*#?&])[A-Za-z\\d$@$!.%*#?&]{8,}$");
        Matcher m = p.matcher(newPassword);
        if(!m.matches()){
            request.setAttribute("msg","新密码最少为8位并为字母+数字+特殊字符");
            request.getRequestDispatcher("/jsp/updPassword.jsp");
        }
        if(!newPassword.equals(newPasswordAgain)){
            request.setAttribute("msg","两次输入新密码不一致,请重新输入");
            request.getRequestDispatcher("/jsp/updPassword.jsp");
        }
        Admin admin = (Admin)httpSession.getAttribute("admin");
        if(admin!=null){
            if(!admin.getAdminPassword().equals(oldPassword)){
                request.setAttribute("msg","原密码不正确,请重新输入");
                request.getRequestDispatcher("/jsp/updPassword.jsp");
            }
        }
        int i = service.updatePassword(admin.getAdminName(), newPassword);
        if(i>0){
            request.getRequestDispatcher("login.jsp");
        }else {
            request.setAttribute("msg","修改失败");
            request.getRequestDispatcher("/jsp/updPassword.jsp");
        }
    }

}
