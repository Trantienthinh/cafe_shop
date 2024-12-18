package DAO;

import java.sql.*;
import database.JDBCutil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;

public class DAOProduct implements DAOInterface<Product> {

    public static DAOProduct createInstance() {
        return new DAOProduct();
    }

    @Override
    public void Them(Product p) {
        try {
            Connection cnt = JDBCutil.getConnection();

            String query = "INSERT INTO SanPham(maSP, tenSP, loaiSP, soLuong, giaCa, trangThai, hinhAnh, ngayNhap)"
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement pr = cnt.prepareStatement(query);
            pr.setString(1, p.getMaSanPham());
            pr.setString(2, p.getTenSanPham());
            pr.setString(3, p.getLoai());
            pr.setInt(4, p.getSoLuong());
            pr.setDouble(5, p.getGiaCa());
            pr.setString(6, p.getTrangThai());
            pr.setString(7, p.getHinhAnh());
            pr.setDate(8, p.getNgay());

            pr.execute();

            JDBCutil.closeConection(cnt);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Sua(Product p, Integer id) {
        try {
            Connection cnt = JDBCutil.getConnection();

            String query = "UPDATE SanPham SET maSP = ?, tenSP = ?, loaiSP = ?, soLuong = ?, giaCa = ?, trangThai = ?, ngayNhap = ? WHERE id = ?;";
            PreparedStatement pr = cnt.prepareStatement(query);
            pr.setString(1, p.getMaSanPham());
            pr.setString(2, p.getTenSanPham());
            pr.setString(3, p.getLoai());
            pr.setInt(4, p.getSoLuong());
            pr.setDouble(5, p.getGiaCa());
            pr.setString(6, p.getTrangThai());
            pr.setDate(7, p.getNgay());
            pr.setInt(8, id);
            pr.execute();

            JDBCutil.closeConection(cnt);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Xoa(Integer id) {
        try {
            String query = "DELETE FROM SanPham WHERE id = ?;";
            Connection cnt = JDBCutil.getConnection();
            PreparedStatement pr = cnt.prepareStatement(query);
            pr.setInt(1, id);
            pr.execute();
            JDBCutil.closeConection(cnt);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Product> LayTT() {
        List<Product> ds = new ArrayList<>();
        try {
            Connection cnt = JDBCutil.getConnection();

            String query = "SELECT * FROM SanPham;";
            PreparedStatement pr = cnt.prepareStatement(query);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                Product tmp = new Product();
                tmp.setSoThuTu(rs.getInt(1));
                tmp.setMaSanPham(rs.getString(2));
                tmp.setTenSanPham(rs.getString(3));
                tmp.setLoai(rs.getString(4));
                tmp.setSoLuong(rs.getInt(5));
                tmp.setGiaCa(rs.getDouble(6));
                tmp.setTrangThai(rs.getString(7));
                tmp.setHinhAnh(rs.getString(8));
                tmp.setNgay(rs.getDate(9));

                ds.add(tmp);
            }
            JDBCutil.closeConection(cnt);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }

    @Override
    public boolean kiemTraMa(String ma) {
        boolean check = false;
        try {
            Connection cnt = JDBCutil.getConnection();
            String query = "SELECT maSP FROM SanPham WHERE maSP = '"
                    + ma + "';";
            PreparedStatement pr = cnt.prepareStatement(query);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                check = true;
            }
            JDBCutil.closeConection(cnt);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

    @Override
    public Product TimKiem(String ma) {
        Product tmp = new Product();
        try {
            String query = "SELECT * FROM SanPham WHERE maSP = ?";
            Connection cnt = JDBCutil.getConnection();
            PreparedStatement pr = cnt.prepareStatement(query);
            pr.setString(1, ma);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                tmp.setSoThuTu(rs.getInt(1));
                tmp.setMaSanPham(rs.getString(2));
                tmp.setTenSanPham(rs.getString(3));
                tmp.setLoai(rs.getString(4));
                tmp.setSoLuong(rs.getInt(5));
                tmp.setGiaCa(rs.getDouble(6));
                tmp.setTrangThai(rs.getString(7));
                tmp.setHinhAnh(rs.getString(8));
                tmp.setNgay(rs.getDate(9));
            }
            JDBCutil.closeConection(cnt);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tmp;
    }
    
    public Product TimKiem(Integer ma) {
        Product tmp = new Product();
        try {
            String query = "SELECT * FROM SanPham WHERE id = ?";
            Connection cnt = JDBCutil.getConnection();
            PreparedStatement pr = cnt.prepareStatement(query);
            pr.setInt(1, ma);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                tmp.setSoThuTu(rs.getInt(1));
                tmp.setMaSanPham(rs.getString(2));
                tmp.setTenSanPham(rs.getString(3));
                tmp.setLoai(rs.getString(4));
                tmp.setSoLuong(rs.getInt(5));
                tmp.setGiaCa(rs.getDouble(6));
                tmp.setTrangThai(rs.getString(7));
                tmp.setHinhAnh(rs.getString(8));
                tmp.setNgay(rs.getDate(9));
            }
            JDBCutil.closeConection(cnt);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tmp;
    }
    
    public void capNhatSoLuongThem(Integer id, Integer soLuongThem){
        try {
            Connection cnt = JDBCutil.getConnection();
            String query = "UPDATE SanPham SET soLuong = soLuong + ? WHERE id = ?;";
            PreparedStatement pr = cnt.prepareStatement(query);
            pr.setInt(1, soLuongThem);
            pr.setInt(2, id);
            pr.execute();
            JDBCutil.closeConection(cnt);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void capNhatSoLuongBot(Integer id, Integer soLuongBot){
        try {
            Connection cnt = JDBCutil.getConnection();
            String query = "UPDATE SanPham SET soLuong = soLuong - ? WHERE id = ?;";
            PreparedStatement pr = cnt.prepareStatement(query);
            pr.setInt(1, soLuongBot);
            pr.setInt(2, id);
            pr.execute();
            JDBCutil.closeConection(cnt);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Integer layRaSoLuongKH(){
        Integer soLuong = 0;
        try {
            Connection cnt = JDBCutil.getConnection();
            String query = "SELECT COUNT(id) FROM SanPham;";
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
    
    public List<Product> LaySanPhamNoiBat() {
        List<Product> ds = new ArrayList<>();
        try {
            Connection cnt = JDBCutil.getConnection();

            String query = """
                           SELECT sp.maSP, sp.tenSP, sp.loaiSP, sp.soLuong, sp.giaCa, sp.trangThai, sp.hinhAnh, sp.ngayNhap
                           FROM SanPham sp
                           JOIN ChiTietHoaDon cthd ON sp.id = cthd.id
                           GROUP BY sp.maSP, sp.tenSP, sp.loaiSP, sp.soLuong, sp.giaCa, sp.trangThai, sp.hinhAnh, sp.ngayNhap
                           ORDER BY SUM(cthd.id) DESC
                           LIMIT 5;""";
            PreparedStatement pr = cnt.prepareStatement(query);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                Product tmp = new Product(rs.getString("maSP"), rs.getString("tenSP"), rs.getString("loaiSP")
                                        , rs.getInt("soLuong"), rs.getDouble("giaCa"), rs.getString("trangThai")
                                        , rs.getString("hinhAnh"), rs.getDate("ngayNhap"));
                ds.add(tmp);
            }
            JDBCutil.closeConection(cnt);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }
    
    public List<Product> TimSanPham(String tenSP) {
        List<Product> ds = new ArrayList<>();
        try {
            Connection cnt = JDBCutil.getConnection();

            String query = "SELECT * FROM SanPham WHERE tenSP LIKE ?";
            PreparedStatement pr = cnt.prepareStatement(query);
            pr.setString(1, "%" + tenSP + "%");
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                Product tmp = new Product(rs.getString("maSP"), rs.getString("tenSP"), rs.getString("loaiSP")
                                        , rs.getInt("soLuong"), rs.getDouble("giaCa"), rs.getString("trangThai")
                                        , rs.getString("hinhAnh"), rs.getDate("ngayNhap"));
                ds.add(tmp);
            }
            JDBCutil.closeConection(cnt);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }   
    
    public List<Product> LayRaSPDaMuaCuaKH(Integer idKhachHang) {
        List<Product> ds = new ArrayList<>();
        try {
            Connection cnt = JDBCutil.getConnection();

            String query = "SELECT * FROM SanPham WHERE id IN (SELECT CTHD.id FROM ChiTietHoaDon cthd JOIN HoaDon hd ON cthd.idHoaDon = hd.idHoaDon JOIN KhachHang kh ON hd.idKhachHang = kh.idKhachHang WHERE kh.idKhachHang = ?);";
            PreparedStatement pr = cnt.prepareStatement(query);
            pr.setInt(1, idKhachHang);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                Product tmp = new Product(rs.getString("maSP"), rs.getString("tenSP"), rs.getString("loaiSP")
                                        , rs.getInt("soLuong"), rs.getDouble("giaCa"), rs.getString("trangThai")
                                        , rs.getString("hinhAnh"), rs.getDate("ngayNhap"));
                ds.add(tmp);
            }
            JDBCutil.closeConection(cnt);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }   
}
