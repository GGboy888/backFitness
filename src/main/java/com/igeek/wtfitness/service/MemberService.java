package com.igeek.wtfitness.service;

import com.igeek.wtfitness.dao.ChongzhiDao;
import com.igeek.wtfitness.dao.MemberDao;
import com.igeek.wtfitness.dao.PrivateCoachInfoDao;
import com.igeek.wtfitness.entity.Chongzhi;
import com.igeek.wtfitness.entity.Member;
import com.igeek.wtfitness.entity.PrivateCoachInfo;
import com.igeek.wtfitness.vo.PageVO;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 用户模块的业务逻辑类
 * @Author wt
 * @Date 2021/2/27 10:16
 */

public class MemberService {
    private MemberDao memberDao = new MemberDao();
    private ChongzhiDao chongzhiDao;


    /**
     * @Description: 会员管理service实现层-分页查询
     * @Author: wt
     * @Date: 2021/3/1
     */

    public PageVO<Member> query1(String query,int pageNow,int limit) {
        int counts = 0;
        PageVO<Member> vo = null;
        try {
            counts = memberDao.selectCounts(query);

            //计算总页数
            int myPages = (int)(counts%limit==0?counts/limit:(counts/limit)+1);

            //计算起始值
            int begin = (pageNow - 1)*limit;

            //查询数据
            List<Member> memberList = memberDao.selectAll(query, begin,limit);

            //封装PageVO
            vo = new PageVO<>(null,null,pageNow,counts,myPages,memberList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vo;
    }
    /**
     * @Description: 会员管理service实现层-搜索分页查询
     */
    public PageVO<Member> query2(String name,String ktype,int pageNow,int limit) {
        int counts = 0;
        PageVO<Member> vo = null;
        try {
            counts = memberDao.selectCounts1(name,ktype);

            //计算总页数
            int myPages = (int)(counts%limit==0?counts/limit:(counts/limit)+1);

            //计算起始值
            int begin = (pageNow - 1)*limit;

            //查询数据
            List<Member> memberList = memberDao.query2(name,ktype, begin,limit);

            //封装PageVO
            vo = new PageVO<>(null,null,pageNow,counts,myPages,memberList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vo;
    }
/**
     * @Description: 会员管理service实现层-会员到期分页查询
     * @Author: wt
     * @Date: 2021/3/1
     */


/**
     * @Description: 会员管理service实现层-根据会员id删除
     * @Author: wt
     * @Date: 2021/3/1
 * @param id
     */

    public int del(int id) throws SQLException {
        //最后删除此会员
            int i = memberDao.delete(id);
            return i;
    }

    /**
     * @Description: 会员管理service实现层-根据会员id批量删除
     * @Author: wt
     * @Date: 2021/3/1
     */
     public int batchDel(Integer[] ids) throws SQLException {
         int i = 0;
         for (Integer id : ids) {
             i += memberDao.delete(id);
         }
         return i;
     }

    /**
     * @Description: 会员管理service实现层-添加会员
     * @Author: wt
     * @Date: 2021/3/1
     */
    public int insert(Member member) throws SQLException {
        member.setMemberStatic(1);
        int i = memberDao.insert(member);
        return i;
    }

    /**
     * @Description: 会员管理service实现层-修改会员信息
     * @Author: wt
     * @Date: 2021/3/1
     */
    public int update(Member member) throws SQLException {
        int i = memberDao.update(member);
        return i;
    }

    /**
     * @Description: 会员管理service实现层-根据id查询会员信息
     * @Author: wt
     * @Date: 2021/3/1
     */
    public Member cha(int id) throws SQLException {
        Long lo = new Long(id);
        Member member = memberDao.selectId(id);
        return member;
    }

    /**
     * @Description: 会员管理service实现层-修改会员信息
     * @Author: wt
     * @Date: 2021/3/1
     */
    public int upd(Member member) throws SQLException {
        Member member1 = memberDao.selectId(member.getMemberId());
        member1.setMemberBalance(member.getMemberBalance());
        int i = memberDao.insert(member1);
        return i;
    }

}
