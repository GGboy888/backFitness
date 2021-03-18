package com.igeek.wtfitness.controller;

import com.igeek.wtfitness.entity.Event;
import com.igeek.wtfitness.entity.Site;
import com.igeek.wtfitness.service.SiteService;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "SiteServlet",urlPatterns = "/site")
public class SiteServlet extends BasicServlet {
    private SiteService service = new SiteService();
    /**
     * @Description: 场地-删除
     */
    public void del(HttpServletRequest request,HttpServletResponse response) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        int i = service.del(id);
        Result result = new Result(i);
        RespWriterUtil.writer(response,result);
    }

    /**
     * @Description: 添加场地
     */
    public void add(HttpServletRequest request,HttpServletResponse response) throws SQLException, ParseException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String beizhu = request.getParameter("beizhu");
        Site site = new Site(0,name,address,beizhu);
        int i = service.add(site);
        Result result = new Result(i);
        RespWriterUtil.writer(response,result);
    }

    /**
     * @Description: 修改场地
     */
    public void upd(HttpServletRequest request,HttpServletResponse response) throws SQLException, ParseException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String beizhu = request.getParameter("beizhu");
        Site site = service.selectOne(id);
        site.setAdName(name);
        site.setAddress(address);
        site.setAdText(beizhu);
        int i = service.upd(site);
        Result result = new Result(i);
        RespWriterUtil.writer(response,result);
    }

    /**
     * @Description: 场地管理-分页查询
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
        PageVO<Site> pageVo = service.query1(query,Integer.parseInt(pageNow),Integer.parseInt(limit));
        //request.setAttribute("vo",pageVo);
        Result result = new Result(pageVo);
        RespWriterUtil.writer(response,result);

    }
}
