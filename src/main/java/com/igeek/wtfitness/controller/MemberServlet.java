package com.igeek.wtfitness.controller;


import com.igeek.wtfitness.dao.ChongzhiDao;
import com.igeek.wtfitness.entity.Chongzhi;
import com.igeek.wtfitness.entity.Member;
import com.igeek.wtfitness.service.MemberService;
import com.igeek.wtfitness.service.MemberTypeService;
import com.igeek.wtfitness.vo.PageVO;
import com.igeek.wtfitness.vo.RespWriterUtil;
import com.igeek.wtfitness.vo.Result;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description
 * @Author wt
 * @Date 2021/2/26 11:31
 */
@WebServlet(name = "MemberServlet",urlPatterns = "/member")
public class MemberServlet extends BasicServlet{

    private MemberService service = new MemberService();
    private ChongzhiDao chongzhiDao = new ChongzhiDao();
    private MemberTypeService memberTypeService = new MemberTypeService();


    /**
     * @Description 会员列表-分页查询
     * @Author wt
     * @Date 2021/2/26 11:31
     */
    public void viewAll(HttpServletRequest request, HttpServletResponse response){
        String pageNow = request.getParameter("page");
        String limit = request.getParameter("limit");
        String query = request.getParameter("query");
        if(query==null){
            query="";
        }
        PageVO<Member> pageVo = service.query1(query,Integer.parseInt(pageNow),Integer.parseInt(limit));
        //request.setAttribute("vo",pageVo);
        Result result = new Result(pageVo);
        RespWriterUtil.writer(response,result);

    }
    /**
     * @Description 会员搜索-分页查询
     */
    public void query (HttpServletRequest request,HttpServletResponse response){
        String pageNow = request.getParameter("page");
        String limit = request.getParameter("limit");
        String name = request.getParameter("name");
        String ktype = request.getParameter("ktype");
        PageVO<Member> pageVo = service.query2(name,ktype,Integer.parseInt(pageNow),Integer.parseInt(limit));
        //request.setAttribute("vo",pageVo);
        Result result = new Result(pageVo);
        RespWriterUtil.writer(response,result);
    }

    /**
     * @Description 会员到期-分页查询
     * @Author wt
     * @Date 2021/2/26 11:31
     */

    /**
     * @Description 会员到期-根据会员id查询会员信息
     * @Author wt
     * @Date 2021/2/26 11:31
     */
    public void findById(HttpServletRequest request,HttpServletResponse response) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        Member member = service.cha(id);
        System.out.println(member);
        Result result = new Result(member);
        RespWriterUtil.writer(response,result);
    }

    /**
     * @Description 会员到期-根据会员id删除
     */

    public void delById(HttpServletRequest request,HttpServletResponse response) throws SQLException {
        String id = request.getParameter("id");
        System.out.println(id);
        int i = service.del(Integer.parseInt(id));
        Result result = new Result(i);
        RespWriterUtil.writer(response,result);
    }

    /**
     * @Description 会员到期-根据会员批量删除
     */
    public void batchDel(HttpServletRequest request,HttpServletResponse response) throws SQLException {
        String[] strs = request.getParameterValues("id");
        Integer[] ids = new Integer[strs.length];
        for (int i = 0; i < ids.length; i++) {
            ids[i]= Integer.parseInt(strs[i]);
        }
        int i = service.batchDel(ids);
        System.out.println(i);
        Result result = new Result(i);
        RespWriterUtil.writer(response,result);

    }

    /**
     * @Description 会员到期-添加新会员
     */
    public void insert( final HttpServletRequest request,HttpServletResponse response){
        String memberName = request.getParameter("name");
        System.out.println(memberName);
        String memberPassword = request.getParameter("password");
        String memberPhone = request.getParameter("phone");
        int memberSex =Integer.parseInt(request.getParameter("sex"));
        int memberAge = Integer.parseInt(request.getParameter("age"));
        String birthday = request.getParameter("srdata");
        String nenberDate = request.getParameter("data");
        int membertypes = Integer.parseInt(request.getParameter("optype"));
        String memberxufei = request.getParameter("memberxufei");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(birthday);
            Date date2 = sdf.parse(nenberDate);
            Date date1 = sdf.parse(memberxufei);
            Member member = new Member(0,memberName,memberPassword,memberPhone
                    ,memberSex,memberAge,date,date2,membertypes,1,0,date1);
            //给member对象设置ID
            System.out.println(member);

            //执行用户注册
            int i = service.insert(member);
            Result result = new Result(i);
            RespWriterUtil.writer(response,result);

        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * @Description 会员到期-修改会员信息
     * @Author wt
     * @Date 2021/2/26 11:31
     */
    public void updata( final HttpServletRequest request,HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        String memberName = request.getParameter("name");
        String memberPassword = request.getParameter("password");
        String memberPhone = request.getParameter("phone");
        int memberSex =Integer.parseInt(request.getParameter("sex"));
        int memberAge = Integer.parseInt(request.getParameter("age"));
        String birthday = request.getParameter("srdata");
        String nenberDate = request.getParameter("data");
        int membertypes = Integer.parseInt(request.getParameter("optype"));
        String memberxufei = request.getParameter("memberxufei");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(birthday);
            Date date2 = sdf.parse(nenberDate);
            Date date1 = sdf.parse(memberxufei);
            System.out.println(date1);
            Member member = new Member(id,memberName,memberPassword,memberPhone
                    ,memberSex,memberAge,date,date2,membertypes,1,0,date1);
            //给member对象设置ID

            //执行用户修改
            int i = service.update(member);
            Result result = new Result(i);
            RespWriterUtil.writer(response,result);

        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * @Description 会员到期-添加会员卡续费记录
     * @Author wt
     * @Date 2021/2/26 11:31
     */
    public void ins(HttpServletRequest request,HttpServletResponse response) throws SQLException, ParseException {
        int id =Integer.parseInt( request.getParameter("id"));
        int xztype = Integer.parseInt(request.getParameter("xztype"));
        int jine = Integer.parseInt(request.getParameter("jine"));
        int ssmoney =Integer.parseInt(request.getParameter("ssmoney")) ;
        int zhaoqian = Integer.parseInt(request.getParameter("zhaoqian"));
        String beizhu = request.getParameter("beizhu");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Chongzhi chongzhi = new Chongzhi(0,id,xztype,jine,ssmoney,zhaoqian,date,0,beizhu,2L);
        chongzhiDao.insert(chongzhi);
        Member member = service.cha(id);
        Date date2 = member.getMemberXufei();
        int day = (int)memberTypeService.findById(xztype).getTypeDay();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date2);
        calendar.add(Calendar.DAY_OF_YEAR,day);
        Date time = calendar.getTime();
        String format = df.format(time);
        Date date1 = df.parse(format);
        member.setMemberStatic(1);
        member.setMemberTypes(xztype);
        member.setMemberXufei(date1);
        System.out.println(member);
        int i = service.update(member);
        Result result = new Result(i);
        RespWriterUtil.writer(response,result);
    }


}
