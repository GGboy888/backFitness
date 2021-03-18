package com.igeek.wtfitness.service;

import com.igeek.wtfitness.dao.SiteDao;
import com.igeek.wtfitness.entity.Event;
import com.igeek.wtfitness.entity.Site;
import com.igeek.wtfitness.vo.PageVO;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description 场地管理service实现层
 * @Author wt
 * @Date 2021/3/18 9:47
 */
public class SiteService {
    private SiteDao dao = new SiteDao();
    /**
     * @Description: 场地管理service实现层-分页查询
     * @Author: wt
     * @Date: 2021/3/1
     */
    public PageVO<Site> query1(String query, int pageNow, int limit) {
        int counts = 0;
        PageVO<Site> vo = null;
        try {
            counts = dao.selectCounts(query);

            //计算总页数
            int myPages = (int)(counts%limit==0?counts/limit:(counts/limit)+1);

            //计算起始值
            int begin = (pageNow - 1)*limit;

            //查询数据
            List<Site> memberList = dao.selectAll(query, begin,limit);

            //封装PageVO
            vo = new PageVO<>(null,null,pageNow,counts,myPages,memberList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vo;
    }

    /**
     * @Description: 场地管理service实现层-添加赛事
     * @Author: wt
     * @Date: 2021/3/1
     */
    public int add(Site site) throws SQLException {
        int i = dao.insert(site);
        return i;
    }

    /**
     * @Description: 场地管理service实现层-根据id删除赛事
     * @Author: wt
     * @Date: 2021/3/1
     */
    public int del(int id) throws SQLException {
        int i = dao.delete(id);
        return i;
    }
    /**
     * @Description: 场地管理service实现层-修改赛事
     */
    public int upd(Site site) throws SQLException {
        int i = dao.update(site);
        return i;
    }

    /**
     * @Description: 场地管理service实现层-根据id查询赛事
     */
    public Site selectOne(int id) throws SQLException {
        Site site = dao.selectOne(id);
        return site;
    }
}
