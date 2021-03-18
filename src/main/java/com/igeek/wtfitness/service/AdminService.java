package com.igeek.wtfitness.service;

import com.igeek.wtfitness.dao.AdminDao;
import com.igeek.wtfitness.entity.Admin;

import java.sql.SQLException;

/**
 * @Description 管理员模块的业务逻辑类
 * @Author wt
 * @Date 2021/2/26 8:58
 */
public class AdminService {
    private AdminDao dao = new AdminDao();

    public Admin login(String adminId,String password){
        try {
            Admin admin = dao.selectOne(adminId, password);
            return admin;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int updatePassword(String admin,String password){
        try {
            int i = dao.update(admin, password);
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
