package com.igeek.wtfitness.controller;

import com.igeek.wtfitness.entity.MemberType;
import com.igeek.wtfitness.service.MemberTypeService;
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
 * @Description: 会员卡类型信息Controller控制层
 * @Author: wt
 * @Date: 2021/3/4
 */
@WebServlet(name = "MetypeServlet",urlPatterns = "/metype")
public class MetypeServlet extends BasicServlet {
    private MemberTypeService service = new MemberTypeService();
    /**
     * @Description: 会员卡类型-删除
     */
    public void del(HttpServletRequest request,HttpServletResponse response) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        int i = service.del(id);

        Result result = new Result(i);
        RespWriterUtil.writer(response,result);
    }

    /**
     * @Description: 会员卡类型-添加会员卡类型
     */
    public void add(HttpServletRequest request,HttpServletResponse response) throws SQLException {
        String typeName = request.getParameter("typeName");
        int typeDay = Integer.parseInt(request.getParameter("typeDay"));
        int money = Integer.parseInt(request.getParameter("money"));
        MemberType memberType = new MemberType(0, typeName, typeDay, money);
        int i = service.insert(memberType);
        Result result = new Result(i);
        RespWriterUtil.writer(response,result);
    }

    /**
     * @Description: 会员卡类型-修改会员卡类型
     */
    public void upd(HttpServletRequest request,HttpServletResponse response) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int tianshu = Integer.parseInt(request.getParameter("tianshu"));
        int money = Integer.parseInt(request.getParameter("money"));
        MemberType memberType = service.findById(id);
        memberType.setTypeName(name);
        memberType.setTypeDay(tianshu);
        memberType.setTypemoney(money);
        int i = service.updata(memberType);
        Result result = new Result(i);
        RespWriterUtil.writer(response,result);
    }

}
