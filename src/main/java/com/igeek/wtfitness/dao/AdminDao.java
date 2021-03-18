package com.igeek.wtfitness.dao;

import com.igeek.wtfitness.entity.Admin;

import java.sql.SQLException;

/**
* @methodName 管理员信息dao层接口
* @Description
* 
* @return 
*/
public class AdminDao extends BasicDao<Admin>{
    //通过姓名和密码查询用户信息
   public Admin selectOne(String adminId,String adminPassword) throws SQLException {
       String sql = "select * from admin where adminId= ? and adminPwd= ?";
       Admin admin = this.getBean(sql, Admin.class, adminId, adminPassword);
       return admin;
   }
   public int updatePassword(String adminId,String newPassword ) throws SQLException {
       String sql = "update admin admin set adminPwd = ? where adminId = ?";
       int i = this.update(sql, newPassword, adminId);
       return i;
   }
}
