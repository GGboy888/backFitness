package com.igeek.wtfitness.controller;

import com.igeek.wtfitness.entity.Equipment;
import com.igeek.wtfitness.entity.Member;
import com.igeek.wtfitness.service.EquipmentService;
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
 * @Description: 器材管理Controller控制层
 * @Author: wt
 * @Date: 2021/3/12
 */
@WebServlet(name = "EquipmentServlet",urlPatterns = "/qc")
public class EquipmentServlet extends BasicServlet {
    private EquipmentService service = new EquipmentService();
    /**
     * @Description: 器材管理-根据器材名称分页查询
     */
    public void viewAll(HttpServletRequest request, HttpServletResponse response){
        String pageNow = request.getParameter("page");
        String limit = request.getParameter("limit");
        String query = request.getParameter("query");
        if(query==null){
            query="";
        }
        PageVO<Equipment> pageVo = service.query1(query,Integer.parseInt(pageNow),Integer.parseInt(limit));
        //request.setAttribute("vo",pageVo);
        Result result = new Result(pageVo);
        RespWriterUtil.writer(response,result);

    }
    /**
     * @Description: 器材管理-添加器材
     */
    public void insert(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String eqName = request.getParameter("eqName");
        String eqText = request.getParameter("eqText");
        Equipment equipment = new Equipment(0, eqName, eqText);
        int i = service.insert(equipment);
        Result result = new Result(i);
        RespWriterUtil.writer(response,result);
    }

    /**
     * @Description: 器材管理-根据器材id删除
     */
    public void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        int i = service.del(id);
        Result result = new Result(i);
        RespWriterUtil.writer(response,result);
    }
}
