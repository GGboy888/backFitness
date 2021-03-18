package com.igeek.wtfitness.dao;

import com.igeek.wtfitness.entity.Member;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @Description 用户模块的数据交互类
 * @Author wt
 * @Date 2021/2/26 11:44
 */
public class MemberDao extends BasicDao<Member>{
    //插入用户数据
    public int insert(Member member) throws SQLException {
        String sql = "insert into member values(null,?,?,?,?,?,?,?,?,?,?,?)";
        int i = this.update(sql,member.getMemberName(),member.getMemberPassword(),member.getMemberPhone(),member.getMemberSex(),member.getMemberAge(),member.getBirthday(),member.getMemberTypes(),member.getNenberDate(),
                member.getMemberStatic(),member.getMemberBalance(),member.getMemberXufei());
        return i;
    }
    //通过id更新用户数据
    public int update(Member member) throws SQLException {
        String sql = "update member set memberName=?,memberPassword=?,memberPhone=?,memberSex=?,memberAge=?, birthday = ?,memberTypes = ?,nenberDate=?," +
                "memberStatic = ?,memberBalance=?,memberXufei=? where memberId = ?";
        int i = this.update(sql,member.getMemberName(),member.getMemberPassword(),member.getMemberPhone(),member.getMemberSex(),member.getMemberAge(),member.getBirthday(),member.getMemberTypes(),member.getNenberDate(),
                member.getMemberStatic(),member.getMemberBalance(),member.getMemberXufei(),member.getMemberId());
        return i;
    }
    //通过用户名查询
    public Long selectOne(String username) throws SQLException {
        String sql = "select * from member where memberName = ?";
        Long value = (Long)this.getSingleValue(sql,username);
        return value;
    }
    //通过id查询
    public Member selectId(int id) throws SQLException {
        String sql = "select * from member where memberid = ?";
        Member member = this.getBean(sql, Member.class, id);
        return member;
    }

    //通过姓名和密码查询用户信息
    public Member selectOne(String username,String password) throws SQLException {

        String sql = "select * from member where MemberName = ? and MemberPassword= ?";
        Member member= this.getBean(sql, Member.class, username, password);
        return member;
    }

    public List<Member> selectAll() throws SQLException {
        String sql = "select * from member";
        List<Member> list = this.getBeanList(sql, Member.class);
        return list;
    }

    //根据id删除用户
    public int delete(int id) throws SQLException {
        String sql = "delete from member where memberId = ?";
        int i = this.update(sql, id);
        return i;
    }

    //件模糊查询会员表的记录总数
    public int selectCounts(String query) throws SQLException {
        String sql = "select count(*) from member where MemberName like concat('%',?,'%')";
        Long singleValue = (Long)this.getSingleValue(sql, query);
        return singleValue.intValue();
    }


    //分页+条件模糊查询会员表的所有信息
    public List<Member> selectAll(String query , int begin,int limit) throws SQLException {
        String sql = "select * from member where MemberName like concat('%',?,'%') limit ?,?";
        List<Member> itemsList = this.getBeanList(sql, Member.class, query, begin,limit);
        return itemsList;
    }
    //搜索件查询会员表的记录总数
    public int selectCounts1(String name,String ktype) throws SQLException {
        String sql = "select count(*) from member where memberName = ? and memberTypes = ?";
        Long singleValue = (Long)this.getSingleValue(sql, name,ktype);
        return singleValue.intValue();
    }
    //分页条件查询
    public List<Member> query2(String name,String ktype,int begin,int limit) throws SQLException {
        String sql = "select * from MemberName where memberName = ? and memberTypes = ? limit ?,?";
        List<Member> memberList = this.getBeanList(sql, Member.class, name,ktype, begin, limit);
        return memberList;
    }

}
