/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import database.JDBCutil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Order;

public class DAOOrder implements DAOInterface<Order> {
    public static DAOOrder createInstance() {
        return new DAOOrder();
    }

    @Override
    public void Them(Order o) {
        try {
            Connection cnt = JDBCutil.getConnection();

            String query = "INSERT INTO HoaDon(idKhachhang, idNhanVien, tongTien, ngayLap)"
                    + "VALUES(?, ?, ?, ?);";
            PreparedStatement pr = cnt.prepareStatement(query);
            pr.setInt(1, o.getIdKhachHang());
            pr.setInt(2, o.getIdNhanVien());
            pr.setDouble(3, o.getTongTien());
            pr.setDate(4, (Date) o.getNgayLap());

            pr.execute();

            JDBCutil.closeConection(cnt);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Integer layMaHoaDonMoiLuu(){
        Integer ma = -1;
        try {
            String query = "SELECT idHoaDon FROM HoaDon ORDER BY idHoaDon DESC LIMIT 1;";
            Connection cnt = JDBCutil.getConnection();
            PreparedStatement pr = cnt.prepareStatement(query);
            ResultSet rs = pr.executeQuery();
            if(rs.next())
                ma = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ma;
    }

    @Override
    public void Sua(Order t, Integer id) {
    }

    @Override
    public void Xoa(Integer id) {
    }

    @Override
    public List<Order> LayTT() {
        List<Order> ds = new ArrayList<>();
        try {
            Connection cnt = JDBCutil.getConnection();

            String query = "SELECT * FROM HoaDon;";
            PreparedStatement pr = cnt.prepareStatement(query);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                Order tmp = new Order(rs.getInt("idHoaDon"), rs.getInt("idKhachHang"), rs.getInt("idNhanVien"), rs.getDouble("tongTien"), rs.getDate("ngayLap"));
                ds.add(tmp);
            }
            JDBCutil.closeConection(cnt);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }

    @Override
    public Order TimKiem(String ma) {
        return null;
    }

    @Override
    public boolean kiemTraMa(String ma) {
        return false;
    }
    
    public Integer layRaSoLuongDH(){
        Integer soLuong = 0;
        try {
            Connection cnt = JDBCutil.getConnection();
            String query = "SELECT COUNT(idHoaDon) FROM HoaDon;";
            PreparedStatement pr = cnt.prepareStatement(query);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                soLuong = rs.getInt(1);
            }
            JDBCutil.closeConection(cnt);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return soLuong;
    }
    
    public List<Order> LayDonHangTrongNgay(Date ngay) {
        List<Order> ds = new ArrayList<>();
        try {
            Connection cnt = JDBCutil.getConnection();

            String query = "SELECT * FROM HoaDon WHERE ngayLap = ?;";
            PreparedStatement pr = cnt.prepareStatement(query);
            pr.setDate(1, ngay);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                Order tmp = new Order(rs.getInt("idHoaDon"), rs.getInt("idKhachHang"), rs.getInt("idNhanVien"), rs.getDouble("tongTien"), rs.getDate("ngayLap"));
                ds.add(tmp);
            }
            JDBCutil.closeConection(cnt);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }

}
