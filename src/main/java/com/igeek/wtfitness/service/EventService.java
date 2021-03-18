package com.igeek.wtfitness.service;

import com.igeek.wtfitness.dao.EventDao;
import com.igeek.wtfitness.entity.Event;
import com.igeek.wtfitness.entity.Subject;
import com.igeek.wtfitness.vo.PageVO;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description  赛事管理service实现层
 * @Author wt
 * @Date 2021/3/17 21:32
 */
public class EventService {
    private EventDao dao = new EventDao();
    /**
     * @Description: 赛事管理service实现层-分页查询
     * @Author: wt
     * @Date: 2021/3/1
     */
    public PageVO<Event> query1(String query, int pageNow, int limit) {
        int counts = 0;
        PageVO<Event> vo = null;
        try {
            counts = dao.selectCounts(query);

            //计算总页数
            int myPages = (int)(counts%limit==0?counts/limit:(counts/limit)+1);

            //计算起始值
            int begin = (pageNow - 1)*limit;

            //查询数据
            List<Event> memberList = dao.selectAll(query, begin,limit);

            //封装PageVO
            vo = new PageVO<>(null,null,pageNow,counts,myPages,memberList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vo;
    }

    /**
     * @Description: 赛事管理service实现层-添加赛事
     * @Author: wt
     * @Date: 2021/3/1
     */
    public int add(Event event) throws SQLException {
        int i = dao.insert(event);
        return i;
    }

    /**
     * @Description: 赛事管理service实现层-根据id删除赛事
     * @Author: wt
     * @Date: 2021/3/1
     */
    public int del(int id) throws SQLException {
        int i = dao.delete(id);
        return i;
    }
    /**
     * @Description: 赛事管理service实现层-修改赛事
     */
    public int upd(Event event) throws SQLException {
        int i = dao.update(event);
        return i;
    }

    /**
     * @Description: 赛事管理service实现层-根据id查询赛事
     */
    public Event selectOne(int id) throws SQLException {
        Event event = dao.selectOne(id);
        return event;
    }
}
