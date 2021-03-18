package com.igeek.wtfitness.controller;

import com.igeek.wtfitness.entity.MemberType;
import com.igeek.wtfitness.entity.Subject;
import com.igeek.wtfitness.service.SubjectService;
import com.igeek.wtfitness.vo.PageVO;
import com.igeek.wtfitness.vo.RespWriterUtil;
import com.igeek.wtfitness.vo.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @Description: 课程管理Controller控制层
 * @Author: wt
 * @Date: 2021/3/12
 */
@WebServlet(name = "SubjectServlet",urlPatterns = "/subject")
public class SubjectServlet extends BasicServlet {
   private SubjectService service = new SubjectService();
    /**
     * @Description: 课程-删除
     */
    public void del(HttpServletRequest request,HttpServletResponse response) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        int i = service.del(id);
        Result result = new Result(i);
        RespWriterUtil.writer(response,result);
    }

    /**
     * @Description: 添加课程
     */
    public void add(HttpServletRequest request,HttpServletResponse response) throws SQLException {
        String subname = request.getParameter("subname");
        int money = Integer.parseInt(request.getParameter("sellingPrice"));
        Subject subject = new Subject(0, subname, money);
        int i = service.add(subject);
        Result result = new Result(i);
        RespWriterUtil.writer(response,result);
    }

    /**
     * @Description: 修改课程
     */
    public void upd(HttpServletRequest request,HttpServletResponse response) throws SQLException {
        int id = Integer.parseInt(request.getParameter("subId"));
        String name = request.getParameter("subname");
        int money = Integer.parseInt(request.getParameter("sellingPrice"));
        Subject subject = service.selectOne(id);
        subject.setSubName(name);
        subject.setSubPrice(money);
        int i = service.upd(subject);
        Result result = new Result(i);
        RespWriterUtil.writer(response,result);
    }
    /**
     * @Description: 通过name查询课程
     */
    public void count(HttpServletRequest request,HttpServletResponse response) throws SQLException {
        String subname = request.getParameter("subname");
        int i = service.selectOne(subname);
        Result result = new Result(i);
        RespWriterUtil.writer(response,result);
    }

    /**
     * @Description: 课程管理-分页查询
     * @Author: wt
     * @Date: 2021/3/1
     */
    public void viewAll(HttpServletRequest request, HttpServletResponse response){
        String pageNow = request.getParameter("page");
        String limit = request.getParameter("limit");
        String query = request.getParameter("query");
        if(query==null){
            query="";
        }
        PageVO<Subject> pageVo = service.query1(query,Integer.parseInt(pageNow),Integer.parseInt(limit));
        //request.setAttribute("vo",pageVo);
        Result result = new Result(pageVo);
        RespWriterUtil.writer(response,result);

    }
}
