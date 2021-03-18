package com.igeek.wtfitness.dao;

import com.igeek.wtfitness.entity.Member;
import com.igeek.wtfitness.entity.MemberType;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description 会员卡类型信息Dao层接口
 * @Author wt
 * @Date 2021/3/10 11:28
 */
public class MemberTypeDao extends BasicDao<MemberType>{

    //插入
    public int insert(MemberType memberType) throws SQLException {
        String sql = "insert into membertype values(null,?,?,?)";
        int i = this.update(sql, memberType.getTypeName(), memberType.getTypeDay(), memberType.getTypemoney());
        return i;
    }
    //根据id删除用户
    public int delete(int id) throws SQLException {
        String sql = "delete from membertype where TypeId = ?";
        int i = this.update(sql, id);
        return i;
    }

    public List<MemberType> selectAll() throws SQLException {
        String sql = "select * from membertype";
        List<MemberType> list = this.getBeanList(sql, MemberType.class);
        return list;
    }

    public MemberType selectOne(int id) throws SQLException {
      String sql = "select * from membertype where TypeId = ?";
        MemberType memberType = this.getBean(sql, MemberType.class, id);
        return memberType;
    }

    //件模糊查询会员表的记录总数
    public int selectCounts(String query) throws SQLException {
        String sql = "select count(*) from membertype where TypeName like concat('%',?,'%')";
        Long singleValue = (Long)this.getSingleValue(sql, query);
        return singleValue.intValue();
    }


    //分页+条件模糊查询会员表的所有信息
    public List<MemberType> selectAll(String query , int begin, int limit) throws SQLException {
        String sql = "select * from membertype where TypeName like concat('%',?,'%') limit ?,?";
        List<MemberType> itemsList = this.getBeanList(sql, MemberType.class, query, begin,limit);
        return itemsList;
    }

    //通过id更新数据
    public int update(MemberType memberType) throws SQLException {
        String sql = "update membertype set TypeName=?,TypeDay=?,Typemoney=? where TypeId=?";
        int i = this.update(sql,memberType.getTypeName(),memberType.getTypeDay(),memberType.getTypemoney(),memberType.getTypeId());
        return i;
    }

}
