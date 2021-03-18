package com.igeek.wtfitness.service;

import com.igeek.wtfitness.dao.EquipmentDao;
import com.igeek.wtfitness.entity.Equipment;
import com.igeek.wtfitness.vo.PageVO;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description 器材模块的业务逻辑类
 * @Author wt
 * @Date 2021/2/27 10:16
 */
public class EquipmentService {
  private EquipmentDao dao = new EquipmentDao();
    /**
     * @Description: 器材管理service实现层-分页查询
     * @Author: wt
     * @Date: 2021/3/1
     */
    public PageVO<Equipment> query1(String query, int pageNow, int limit) {
        int counts = 0;
        PageVO<Equipment> vo = null;
        try {
            counts = dao.selectCounts(query);

            //计算总页数
            int myPages = (int)(counts%limit==0?counts/limit:(counts/limit)+1);

            //计算起始值
            int begin = (pageNow - 1)*limit;

            //查询数据
            List<Equipment> memberList = dao.selectAll(query, begin,limit);

            //封装PageVO
            vo = new PageVO<>(null,null,pageNow,counts,myPages,memberList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vo;
    }

    /**
     * @Description: 器材管理service实现层-添加器材
     * @Author: wt
     * @Date: 2021/3/1
     */
    public int insert(Equipment equipment) throws SQLException {
        int i = dao.insert(equipment);
        return i;
    }

    /**
     * @Description: 器材管理service实现层-根据id删除器材
     * @Author: wt
     * @Date: 2021/3/1
     */
    public int del(int id) throws SQLException {
        int i = dao.delete(id);
        return i;
    }
}
