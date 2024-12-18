package DAO;

import database.JDBCutil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cart;

public class DAOCart{

    public static List<Cart> LayTT(Integer idKhachHang) {        
        List<Cart> ds = new ArrayList<>();
        try {
            Connection cnt = JDBCutil.getConnection();

            String query = "SELECT * FROM GioHang WHERE idKhachHang = ?;";
            PreparedStatement pr = cnt.prepareStatement(query);
            pr.setInt(1, idKhachHang);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                Cart tmp = new Cart(rs.getInt("idGioHang"), rs.getInt("idKhachHang"), rs.getInt("id"), rs.getInt("soLuong"));
                ds.add(tmp);
            }
            JDBCutil.closeConection(cnt);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }
    
    public static void xoaSPCuaKH(Integer idKhachHang){
        try {
            Connection cnt = JDBCutil.getConnection();
            String query = "DELETE FROM GioHang WHERE idKhachHang = ?;";
            PreparedStatement pr = cnt.prepareStatement(query);
            pr.setInt(1, idKhachHang);
            pr.execute();
            JDBCutil.closeConection(cnt);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void themSP(Cart c){
        try {
            Connection cnt = JDBCutil.getConnection();
            String query = "INSERT INTO GioHang(idKhachHang, id, soLuong) VALUES (?, ?, ?);";
            PreparedStatement pr = cnt.prepareStatement(query);
            pr.setInt(1, c.getIdKhachHang());
            pr.setInt(2, c.getId());
            pr.setInt(3, c.getSoLuong());
            pr.execute();
            JDBCutil.closeConection(cnt);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
