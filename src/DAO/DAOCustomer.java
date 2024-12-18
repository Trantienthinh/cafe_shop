package DAO;

import database.JDBCutil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;

public class DAOCustomer {

    public static DAOCustomer createInstance() {
        return new DAOCustomer();
    }
    
    public boolean dangNhap(String taiKhoan, String matKhau) {
        boolean check = false;
        try {
            Connection cnt = JDBCutil.getConnection();
            String query = "SELECT taiKhoan, matKhau FROM KhachHang WHERE taiKhoan = ? AND matKhau = ?;";
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

    public boolean kiemTraMaKH(Integer ma) {
        boolean check = false;
        try {
            Connection cnt = JDBCutil.getConnection();
            String query = "SELECT idKhachHang FROM KhachHang WHERE idKhachHang = ?;";
            PreparedStatement pr = cnt.prepareStatement(query);
            pr.setInt(1, ma);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                check = true;
            }
            JDBCutil.closeConection(cnt);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check;
    }

    public boolean kiemTraMa(String ma) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Integer layRaSoLuongKH() {
        Integer soLuong = 0;
        try {
            Connection cnt = JDBCutil.getConnection();
            String query = "SELECT COUNT(idKhachHang) FROM KhachHang;";
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

    public Integer layMaKhachHang(String taiKhoan) {
        Integer ma = 0;
        try {
            Connection cnt = JDBCutil.getConnection();
            String qery = "SELECT idKhachHang FROM KhachHang WHERE taiKhoan = ?;";
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

    public boolean kiemTraMK(String taiKhoan, String matKhau) {
        boolean check = false;
        try {
            Connection cnt = JDBCutil.getConnection();
            String qery = "SELECT taiKhoan, matKhau FROM KhachHang WHERE taiKhoan = ? AND matKhau = ?;";
            PreparedStatement pr = cnt.prepareStatement(qery);
            pr.setString(1, taiKhoan);
            pr.setString(2, matKhau);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                check = true;
            }
            JDBCutil.closeConection(cnt);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

    public void capNhatMK(String taiKhoan, String matKhau) {
        try {
            Connection cnt = JDBCutil.getConnection();
            String qery = "UPDATE KhachHang SET matKhau = ? WHERE taiKhoan = ?;";
            PreparedStatement pr = cnt.prepareStatement(qery);
            pr.setString(1, matKhau);
            pr.setString(2, taiKhoan);
            pr.execute();
            JDBCutil.closeConection(cnt);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Customer layTTKH(String taiKhoan) {
        Customer tmp = new Customer();
        try {
            Connection cnt = JDBCutil.getConnection();
            String query = "SELECT * FROM KhachHang WHERE taiKhoan = ?;";
            PreparedStatement pr = cnt.prepareStatement(query);
            pr.setString(1, taiKhoan);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                tmp.setHoVaTen(rs.getString("hoVaTen"));
                tmp.setDiaChi(rs.getString("diaChi"));
                tmp.setGioiTinh(rs.getString("gioiTinh"));
                tmp.setNgaySinh(rs.getString("ngaySinh"));
                tmp.setSoDienThoai(rs.getString("soDienThoai"));
            }
            JDBCutil.closeConection(cnt);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tmp;
    }

    public void CapNhatTTKH(String taiKhoan, Customer c) {
        try {
            Connection cnt = JDBCutil.getConnection();
            String query = "UPDATE KhachHang SET hoVaTen = ?, soDienThoai = ?, diaChi = ?, gioiTinh = ?, ngaySinh = ? WHERE taiKhoan = ?;";
            PreparedStatement pr = cnt.prepareStatement(query);
            pr.setString(1, c.getHoVaTen());
            pr.setString(2, c.getSoDienThoai());
            pr.setString(3, c.getDiaChi());
            pr.setString(4, c.getGioiTinh());
            pr.setString(5, c.getNgaySinh());
            pr.setString(6, taiKhoan);

            pr.execute();
            JDBCutil.closeConection(cnt);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Integer LayRaSOLuongSPDaMua(Integer idKhachHang) {
        Integer soLuong = 0;
        try {
            Connection cnt = JDBCutil.getConnection();
            String query = "SELECT SUM(cthd.soLuong) AS tongSoLuong"
                    + " FROM KhachHang kh JOIN HoaDon hd ON kh.idKhachHang = hd.idKhachHang"
                    + " JOIN ChiTietHoaDon cthd ON cthd.idHoaDon = hd.idHoaDon"
                    + " WHERE hd.idKhachHang = ?;";
            PreparedStatement pr = cnt.prepareStatement(query);
            pr.setInt(1, idKhachHang);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                soLuong = rs.getInt("tongSoLuong");
            }
            JDBCutil.closeConection(cnt);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return soLuong;
    }

    public Double LayRaTongTienDaChi(Integer idKhachHang) {        
            Double tongTien = 0.0;
            LocalDateTime currentDateTime = LocalDateTime.now();
            int currentMonth = currentDateTime.getMonthValue();
        try {    
            Connection cnt = JDBCutil.getConnection();
            String query = "SELECT SUM(tongTien) AS tongTienDaChi FROM HoaDon WHERE idKhachHang = ? AND MONTH(ngayLap) = ?;";
            PreparedStatement pr = cnt.prepareStatement(query);
            pr.setInt(1, idKhachHang);
            pr.setInt(2, currentMonth);
            ResultSet rs = pr.executeQuery();
            if(rs.next()) tongTien = rs.getDouble("tongTienDaChi");
            JDBCutil.closeConection(cnt);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tongTien;
    }
}
