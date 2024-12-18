/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import database.JDBCutil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Order;
import model.OrderDetail;

public class DAOOrderDetail implements DAOInterface<Order> {
    public static DAOOrderDetail createInstance() {
        return new DAOOrderDetail();
    }

    public void Them(OrderDetail o) {
        try {
            Connection cnt = JDBCutil.getConnection();

            String query = "INSERT INTO ChiTietHoaDon(id, idHoaDon, soLuong)"
                    + "VALUES(?, ?, ?);";
            PreparedStatement pr = cnt.prepareStatement(query);
            pr.setInt(1, o.getId());
            pr.setInt(2, o.getIdHoaDon());
            pr.setInt(3, o.getSoLuong());

            pr.execute();

            JDBCutil.closeConection(cnt);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Sua(Order t, Integer id) {
    }

    @Override
    public void Xoa(Integer id) {
    }

    @Override
    public List<Order> LayTT() {
        return null;
    }

    @Override
    public Order TimKiem(String ma) {
        return null;
    }

    @Override
    public boolean kiemTraMa(String ma) {
        return false;
    }

    @Override
    public void Them(Order t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
