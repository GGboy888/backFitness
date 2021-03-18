package com.igeek.wtfitness.service;

import com.igeek.wtfitness.dao.SubjectDao;
import com.igeek.wtfitness.entity.Subject;
import com.igeek.wtfitness.vo.PageVO;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description 课程管理service实现层
 * @Author wt
 * @Date 2021/3/17 18:47
 */
public class SubjectService {
    private SubjectDao dao =new SubjectDao();

    /**
     * @Description: 课程管理service实现层-分页查询
     * @Author: wt
     * @Date: 2021/3/1
     */
    public PageVO<Subject> query1(String query, int pageNow, int limit) {
        int counts = 0;
        PageVO<Subject> vo = null;
        try {
            counts = dao.selectCounts(query);

            //计算总页数
            int myPages = (int)(counts%limit==0?counts/limit:(counts/limit)+1);

            //计算起始值
            int begin = (pageNow - 1)*limit;

            //查询数据
            List<Subject> memberList = dao.selectAll(query, begin,limit);

            //封装PageVO
            vo = new PageVO<>(null,null,pageNow,counts,myPages,memberList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vo;
    }

    /**
     * @Description: 课程管理service实现层-添加课程
     * @Author: wt
     * @Date: 2021/3/1
     */
    public int add(Subject subject) throws SQLException {
        int i = dao.insert(subject);
        return i;
    }

    /**
     * @Description: 课程管理service实现层-根据id删除课程
     * @Author: wt
     * @Date: 2021/3/1
     */
    public int del(int id) throws SQLException {
        int i = dao.delete(id);
        return i;
    }
    /**
     * @Description: 课程管理service实现层-修改课程
     */
    public int upd(Subject subject) throws SQLException {
        int i = dao.update(subject);
        return i;
    }

    /**
     * @Description: 课程管理service实现层-根据name查询课程
     */
    public int selectOne(String name) throws SQLException {
        int i = dao.selectOne(name);
        return i;
    }
    /**
     * @Description: 课程管理service实现层-根据id查询课程
     */
    public Subject selectOne(int id) throws SQLException {
        Subject subject = dao.selectOne(id);
        return subject;
    }
}
