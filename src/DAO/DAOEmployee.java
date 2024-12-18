/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import database.JDBCutil;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class DAOEmployee {

    public static DAOEmployee createInstance() {
        return new DAOEmployee();
    }

    public Integer layMaNhanVien(String taiKhoan) {
        Integer ma = 0;
        try {
            Connection cnt = JDBCutil.getConnection();
            String qery = "SELECT idNhanVien FROM NhanVien WHERE taiKhoan = ?;";
            PreparedStatement pr = cnt.prepareStatement(qery);
            pr.setString(1, taiKhoan);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                ma = rs.getInt(1);
            }
            JDBCutil.closeConection(cnt);
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ma;
    }

    public boolean dangNhap(String taiKhoan, String matKhau) {
        boolean check = false;
        try {
            Connection cnt = JDBCutil.getConnection();
            String query = "SELECT taiKhoan, matKhau FROM NhanVien WHERE taiKhoan = ? AND matKhau = ?;";
            PreparedStatement pr = cnt.prepareStatement(query);
            pr.setString(1,taiKhoan);
            pr.setString(2, matKhau);
            ResultSet rs = pr.executeQuery();
            if(rs.next()) check = true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }
}
