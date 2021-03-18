package com.igeek.wtfitness.controller;

import com.igeek.wtfitness.entity.Member;
import com.igeek.wtfitness.entity.MemberType;
import com.igeek.wtfitness.service.MemberTypeService;
import com.igeek.wtfitness.vo.PageVO;
import com.igeek.wtfitness.vo.RespWriterUtil;
import com.igeek.wtfitness.vo.Result;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;


@WebServlet(name = "MemberTypeServlet",urlPatterns = "/memberType")
public class MemberTypeServlet extends BasicServlet{
    private MemberTypeService service = new MemberTypeService();

    /**
     * @Description: 会员卡类型-查询所有数据
     * @Author: wt
     * @Date: 2021/3/1
     */
    public void findAll(HttpServletRequest request,HttpServletResponse response) throws SQLException {
        List<MemberType> list = service.findAll();
        Result result = new Result(list);
        RespWriterUtil.writer(response,result);
    }

    /**
     * @Description: 会员卡类型-根据id查询
     * @Author: wt
     * @Date: 2021/3/1
     */
    public void findById(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String id = request.getParameter("id");
        MemberType memberType = service.findById(Integer.parseInt(id));
        Result result = new Result(memberType);
        RespWriterUtil.writer(response,result);

    }

    /**
     * @Description: 会员卡类型-分页查询
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
        PageVO<MemberType> pageVo = service.query1(query,Integer.parseInt(pageNow),Integer.parseInt(limit));
        //request.setAttribute("vo",pageVo);
        Result result = new Result(pageVo);
        RespWriterUtil.writer(response,result);

    }

}
