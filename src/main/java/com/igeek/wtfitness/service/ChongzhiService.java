package com.igeek.wtfitness.service;

import com.igeek.wtfitness.dao.ChongzhiDao;
import com.igeek.wtfitness.entity.Member;
import com.igeek.wtfitness.vo.PageVO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @Description 充值管理service实现层
 * @Author wt
 * @Date 2021/3/17 10:59
 */
public class ChongzhiService {
    private ChongzhiDao chongzhiDao= new ChongzhiDao();

    /**
     * @Description: 会员管理service实现层-分页查询
     * @Author: wt
     * @Date: 2021/3/1
     */

    public PageVO<Map<String,Object>> query1(String query, int pageNow, int limit) {
        int counts = 0;
        PageVO<Map<String,Object>> vo = null;
        try {
            counts = chongzhiDao.selectCounts(query);


            //计算总页数
            int myPages = (int)(counts%limit==0?counts/limit:(counts/limit)+1);

            //计算起始值
            int begin = (pageNow - 1)*limit;

            //查询数据
            List<Map<String, Object>> data = chongzhiDao.findAll(query, begin, limit);

            //封装PageVO
            vo = new PageVO<>(null,null,pageNow,counts,myPages,data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vo;
    }
    public int tongji(String data) throws SQLException {
        int i = chongzhiDao.tongji(data);
        return i;
    }
}
