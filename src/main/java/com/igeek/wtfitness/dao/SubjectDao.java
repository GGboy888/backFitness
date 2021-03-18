package com.igeek.wtfitness.dao;

import com.igeek.wtfitness.entity.Equipment;
import com.igeek.wtfitness.entity.MemberType;
import com.igeek.wtfitness.entity.Subject;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description 课程信息dao层接口
 * @Author wt
 * @Date 2021/2/25 14:59
 */
public class SubjectDao extends BasicDao<Subject> {
    //插入用户数据
    public int insert(Subject subject) throws SQLException {
        String sql = "insert into subject values(null,?,?)";
        int i = this.update(sql,subject.getSubName(),subject.getSubPrice());
        return i;
    }

    //根据id删除用户
    public int delete(int id) throws SQLException {
        String sql = "delete from subject where subId = ?";
        int i = this.update(sql, id);
        return i;
    }

    //通过用户名查询
    public Subject selectOne(int id) throws SQLException {
        String sql = "select * from subject where subId = ? ";
        Subject subject = this.getBean(sql, Subject.class, id);
        return subject;
    }

    //通过用户名查询
    public int selectOne(String name) throws SQLException {
        String sql = "select count(*) from subject where subname = ? ";
        Long singleValue = (Long) this.getSingleValue(sql, name);
        return singleValue.intValue();
    }

    //通过id更新数据
    public int update(Subject subject) throws SQLException {
        String sql = "update subject set subname=?,sellingPrice=? where subId=?";
        int i = this.update(sql,subject.getSubName(),subject.getSubPrice());
        return i;
    }

    //件模糊查询会员表的记录总数
    public int selectCounts(String query) throws SQLException {
        String sql = "select count(*) from subject where subname like concat('%',?,'%')";
        Long singleValue = (Long)this.getSingleValue(sql, query);
        return singleValue.intValue();
    }


    //分页+条件模糊查询会员表的所有信息
    public List<Subject> selectAll(String query , int begin, int limit) throws SQLException {
        String sql = "select * from subject where subname like concat('%',?,'%') limit ?,?";
        List<Subject> itemsList = this.getBeanList(sql, Subject.class, query, begin,limit);
        return itemsList;
    }
}
