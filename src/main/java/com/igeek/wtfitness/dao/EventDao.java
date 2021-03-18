package com.igeek.wtfitness.dao;

import com.igeek.wtfitness.entity.Event;
import com.igeek.wtfitness.entity.Subject;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description 赛事信息dao层接口
 * @Author wt
 * @Date 2021/3/17 21:23
 */
public class EventDao extends BasicDao<Event> {
    //插入用户数据
    public int insert(Event event) throws SQLException {
        String sql = "insert into event values(null,?,?,?,?,?)";
        int i = this.update(sql,event.getEventName(),event.getEventAddress(),event.getDate(),event.getMoney(),event.getEventText());
        return i;
    }

    //根据id删除用户
    public int delete(int id) throws SQLException {
        String sql = "delete from event where eventId = ?";
        int i = this.update(sql, id);
        return i;
    }

    //通过用户id查询
    public Event selectOne(int id) throws SQLException {
        String sql = "select * from event where eventId = ? ";
        Event event = this.getBean(sql, Event.class, id);
        return event;
    }


    //通过id更新数据
    public int update(Event event) throws SQLException {
        String sql = "update event set eventName=?,eventAddress=?,date=?,money=?,eventText=? where eventId=?";
        int i = this.update(sql,event.getEventName(),event.getEventAddress(),event.getDate(),event.getMoney(),event.getEventText(),event.getEventId());
        return i;
    }

    //件模糊查询会员表的记录总数
    public int selectCounts(String query) throws SQLException {
        String sql = "select count(*) from event where eventName like concat('%',?,'%')";
        Long singleValue = (Long)this.getSingleValue(sql, query);
        return singleValue.intValue();
    }


    //分页+条件模糊查询会员表的所有信息
    public List<Event> selectAll(String query , int begin, int limit) throws SQLException {
        String sql = "select * from event where eventName like concat('%',?,'%') limit ?,?";
        List<Event> itemsList = this.getBeanList(sql, Event.class, query, begin,limit);
        return itemsList;
    }
}
