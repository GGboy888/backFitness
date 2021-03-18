package com.igeek.wtfitness.controller;

import com.igeek.wtfitness.dao.ChongzhiDao;
import com.igeek.wtfitness.entity.Chongzhi;
import com.igeek.wtfitness.entity.Member;
import com.igeek.wtfitness.service.ChongzhiService;
import com.igeek.wtfitness.service.MemberService;
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
import java.util.Date;
import java.util.Map;
/**
 * @Description: 充值管理Controller控制层
 * @Author: wt
 * @Date: 2021/3/12
 */
@WebServlet(name = "ChongzhiServlet",urlPatterns = "/cz")
public class ChongzhiServlet extends BasicServlet {
    private ChongzhiDao chongzhiDao = new ChongzhiDao();
    private ChongzhiService chongzhiService = new ChongzhiService();
    private MemberService service = new MemberService();
    /**
     * @Description: 会员余额充值
     */
    public void xin(HttpServletRequest request,HttpServletResponse response) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        int jine = Integer.parseInt(request.getParameter("jine"));
        int ssjine = Integer.parseInt(request.getParameter("ssjine"));
        int zhaoqian = Integer.parseInt(request.getParameter("zhaoqian"));
        String bz = request.getParameter("bz");
        Member member = service.cha(id);
        member.setMemberBalance(member.getMemberBalance()+jine);
        service.update(member);
        Date date = new Date();
       Chongzhi chongzhi = new Chongzhi(0,id,0,jine,ssjine,zhaoqian,date,0,bz,1L);
        int i = chongzhiDao.insert(chongzhi);
        Result result = new Result(i);
        RespWriterUtil.writer(response,result);
    }
    /**
     * @Description: 续费续卡记录-根据所选日期分页查询数据记录
     */
    public void viewAll(HttpServletRequest request, HttpServletResponse response){
        String pageNow = request.getParameter("page");
        String limit = request.getParameter("limit");
        String query = request.getParameter("query");
        if(query==null){
            query="";
        }
        PageVO<Map<String,Object>> pageVo = chongzhiService.query1(query,Integer.parseInt(pageNow),Integer.parseInt(limit));
        //request.setAttribute("vo",pageVo);
        Result result = new Result(pageVo);
        RespWriterUtil.writer(response,result);

    }

    /**
     * @Description: 信息统计-进入收入统计界面
     */
    public void tongji1(HttpServletRequest request,HttpServletResponse response) throws SQLException {
        String[] array={"2020-01","2020-02","2020-03","2020-04","2020-05","2020-06","2020-07","2020-08","2020-09","2020-10","2020-11","2020-12"};
        int[] sum = new int[12];
        for (int i = 0; i < array.length; i++) {
            int tongji = chongzhiService.tongji(array[i]);
            sum[i]=tongji;
        }
        Result result = new Result(sum);
        RespWriterUtil.writer(response,result);
    }
    public void tongji2(HttpServletRequest request,HttpServletResponse response) throws SQLException {
        String[] array={"2021-01","2021-02","2021-03","2021-04","2021-05","2021-06","2021-07","2021-08","2021-09","2021-10","2021-11","2021-12"};
        int[] sum = new int[12];
        for (int i = 0; i < array.length; i++) {
            int tongji = chongzhiService.tongji(array[i]);
            sum[i]=tongji;
        }
        Result result = new Result(sum);
        RespWriterUtil.writer(response,result);
    }
}
