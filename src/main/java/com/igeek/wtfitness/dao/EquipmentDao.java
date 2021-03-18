package com.igeek.wtfitness.dao;

import com.igeek.wtfitness.entity.Equipment;
import com.igeek.wtfitness.entity.Member;

import java.sql.SQLException;
import java.util.List;

/**
* @methodName 器材信息dao层接口
* @Description
* 
* @return 
*/
public class EquipmentDao extends BasicDao<Equipment>{
    //插入用户数据
    public int insert(Equipment equipment) throws SQLException {
        String sql = "insert into equipment values(null,?,?)";
        int i = this.update(sql,equipment.getEqName(),equipment.getEqText());
        return i;
    }

    //根据id删除用户
    public int delete(int id) throws SQLException {
        String sql = "delete from equipment where eqId = ?";
        int i = this.update(sql, id);
        return i;
    }

    //件模糊查询会员表的记录总数
    public int selectCounts(String query) throws SQLException {
        String sql = "select count(*) from equipment where eqName like concat('%',?,'%')";
        Long singleValue = (Long)this.getSingleValue(sql, query);
        return singleValue.intValue();
    }


    //分页+条件模糊查询会员表的所有信息
    public List<Equipment> selectAll(String query , int begin, int limit) throws SQLException {
        String sql = "select * from equipment where eqName like concat('%',?,'%') limit ?,?";
        List<Equipment> itemsList = this.getBeanList(sql, Equipment.class, query, begin,limit);
        return itemsList;
    }
}
