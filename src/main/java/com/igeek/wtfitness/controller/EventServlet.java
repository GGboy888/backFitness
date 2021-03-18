package com.igeek.wtfitness.controller;

import com.igeek.wtfitness.entity.Event;
import com.igeek.wtfitness.entity.Subject;
import com.igeek.wtfitness.service.EventService;
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

@WebServlet(name = "EventServlet",urlPatterns = "/event")
public class EventServlet extends BasicServlet {
    private EventService service = new EventService();
    /**
     * @Description: 赛事-删除
     */
    public void del(HttpServletRequest request,HttpServletResponse response) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        int i = service.del(id);
        Result result = new Result(i);
        RespWriterUtil.writer(response,result);
    }

    /**
     * @Description: 添加赛事
     */
    public void add(HttpServletRequest request,HttpServletResponse response) throws SQLException, ParseException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String strdate = request.getParameter("strdate");
        String beizhu = request.getParameter("beizhu");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(strdate);
        int money = Integer.parseInt(request.getParameter("money"));
        Event event = new Event(0, name, address, date, money, beizhu);
        int i = service.add(event);
        Result result = new Result(i);
        RespWriterUtil.writer(response,result);
    }

    /**
     * @Description: 修改赛事
     */
    public void upd(HttpServletRequest request,HttpServletResponse response) throws SQLException, ParseException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String strdate = request.getParameter("date");
        String beizhu = request.getParameter("xgbeizhu");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(strdate);
        int money = Integer.parseInt(request.getParameter("money"));
        Event event = service.selectOne(id);
        event.setEventName(name);
        event.setEventAddress(address);
        event.setDate(date);
        event.setMoney(money);
        event.setEventText(beizhu);
        int i = service.upd(event);
        Result result = new Result(i);
        RespWriterUtil.writer(response,result);
    }

    /**
     * @Description: 赛事管理-分页查询
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
        PageVO<Event> pageVo = service.query1(query,Integer.parseInt(pageNow),Integer.parseInt(limit));
        //request.setAttribute("vo",pageVo);
        Result result = new Result(pageVo);
        RespWriterUtil.writer(response,result);

    }
}
