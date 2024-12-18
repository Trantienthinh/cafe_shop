package DAO;

import database.JDBCutil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Notification;

public class DAONotification {
    public static List<Notification> layTT(Integer idKhachHang){
        List<Notification> ds = new ArrayList<>();
        try {
            Connection cnt = JDBCutil.getConnection();
            String query = "SELECT * FROM ThongBao WHERE idKhachHang = ?;";
            PreparedStatement pr = cnt.prepareStatement(query);
            pr.setInt(1, idKhachHang);
            ResultSet rs = pr.executeQuery();
            while(rs.next()){
                Notification tmp  = new Notification(rs.getInt("idThongBao"), rs.getInt("idKhachHang"), rs.getString("tieuDe"), rs.getString("noiDung"), rs.getDate("ngayTao"));
                ds.add(tmp);
            }
            JDBCutil.closeConection(cnt);
        } catch (SQLException ex) {
            Logger.getLogger(DAONotification.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }
}
