package com.igeek.wtfitness.dao;

import com.igeek.wtfitness.entity.Chongzhi;


import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description 充值信息Dao层接口
 * @Author wt
 * @Date 2021/3/11 19:32
 */
public class ChongzhiDao extends BasicDao<Chongzhi> {

    //插入
    public int insert(Chongzhi chongzhi) throws SQLException {
        String sql = "insert into chongzhi values(null,?,?,?,?,?,?,?,?,?)";
        int i = this.update(sql, chongzhi.getMemberId(), chongzhi.getMembertype(), chongzhi.getMoney(), chongzhi.getSsmoney()
                , chongzhi.getZlmoney(), chongzhi.getDate(), chongzhi.getCzjine(), chongzhi.getBeizhu(), chongzhi.getCzStatic());
        return i;

    }
    //件模糊查询会员表的记录总数
    public int selectCounts(String query) throws SQLException {
        String sql = " select count(*) from chongzhi where beizhu like concat('%',?,'%')";
        Long singleValue = (Long)this.getSingleValue(sql, query);
        return singleValue.intValue();
    }
    //查询
    public List<Map<String, Object>> findAll(String query, int begin, int limit) throws SQLException {
        String sql = "select * from chongzhi c inner join member m on c.memberId=m.memberId  where beizhu like concat('%',?,'%') limit ?,?";
        List<Map<String, Object>> mapList = this.getMapList(sql, query,begin,limit);
        return mapList;
    }
    //统计查询
    public int tongji(String query) throws SQLException {
        String sql = "select sum(a.money) from chongzhi as a where Date like concat('%',?,'%')";
        Object singleValue = this.getSingleValue(sql, query);
        if(singleValue==null){
            return 0;
        }
        return Integer.parseInt(singleValue.toString());
    }

}
