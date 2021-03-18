package com.igeek.wtfitness.dao;

import com.igeek.wtfitness.entity.Event;
import com.igeek.wtfitness.entity.Site;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description 场地信息dao层接口
 * @Author wt
 * @Date 2021/3/18 9:41
 */
public class SiteDao extends BasicDao<Site> {
    //插入用户数据
    public int insert(Site site) throws SQLException {
        String sql = "insert into site values(null,?,?,?)";
        int i = this.update(sql,site.getAdName(),site.getAddress(),site.getAdText());
        return i;
    }

    //根据id删除用户
    public int delete(int id) throws SQLException {
        String sql = "delete from site where adId = ?";
        int i = this.update(sql, id);
        return i;
    }

    //通过用户id查询
    public Site selectOne(int id) throws SQLException {
        String sql = "select * from site where adId = ? ";
        Site site = this.getBean(sql, Site.class, id);
        return site;
    }


    //通过id更新数据
    public int update(Site site) throws SQLException {
        String sql = "update site set adName=?,address=?,adText=? where adId=?";
        int i = this.update(sql,site.getAdName(),site.getAddress(),site.getAdText(),site.getAdId());
        return i;
    }

    //件模糊查询会员表的记录总数
    public int selectCounts(String query) throws SQLException {
        String sql = "select count(*) from site where adName like concat('%',?,'%')";
        Long singleValue = (Long)this.getSingleValue(sql, query);
        return singleValue.intValue();
    }


    //分页+条件模糊查询会员表的所有信息
    public List<Site> selectAll(String query , int begin, int limit) throws SQLException {
        String sql = "select * from site where adName like concat('%',?,'%') limit ?,?";
        List<Site> itemsList = this.getBeanList(sql, Site.class, query, begin,limit);
        return itemsList;
    }
}
