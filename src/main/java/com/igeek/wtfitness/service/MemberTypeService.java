package com.igeek.wtfitness.service;

import com.igeek.wtfitness.dao.MemberTypeDao;
import com.igeek.wtfitness.entity.Member;
import com.igeek.wtfitness.entity.MemberType;
import com.igeek.wtfitness.vo.PageVO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @Description 会员卡类型service实现层
 * @Author wt
 * @Date 2021/3/10 11:30
 */
public class MemberTypeService {
    private MemberTypeDao dao = new MemberTypeDao();

    /**
     * @Description: 会员卡类型service实现层-查询所有会员卡类型

     */
    public List<MemberType> findAll() throws SQLException {
       List<MemberType> list = dao.selectAll();
       return list;
    }
    /**
     * @Description: 会员卡类型service实现层-添加会员卡类型
     */
    public int insert (MemberType memberType) throws SQLException {
        int i = dao.insert(memberType);
        return i;
    }
    /**
     * @Description: 会员卡类型service实现层-修改会员卡类型
     */
    public int updata (MemberType memberType) throws SQLException {
        int i = dao.update(memberType);
        return i;
    }

    /**
     * @Description: 会员卡类型service实现层-根据id查询会员卡类型信息

     */

    public MemberType findById(int id) throws SQLException {
        MemberType memberType = dao.selectOne(id);
        return memberType;
    }
    /**
     * @Description: 会员卡类型service实现层-根据id删除会员卡类型信息

     */

    public int del(int id) throws SQLException {
        int i = dao.delete(id);
        return i;
    }

    /**
     * @Description: 会员卡类型service实现层-分页查询
     */
    public PageVO<MemberType> query1(String query, int pageNow, int limit) {
        int counts = 0;
        PageVO<MemberType> vo = null;
        try {
            counts = dao.selectCounts(query);

            //计算总页数
            int myPages = (int)(counts%limit==0?counts/limit:(counts/limit)+1);

            //计算起始值
            int begin = (pageNow - 1)*limit;

            //查询数据
            List<MemberType> memberList = dao.selectAll(query, begin,limit);

            //封装PageVO
            vo = new PageVO<>(null,null,pageNow,counts,myPages,memberList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vo;
    }

}
