package model;

public class Cart {
    private Integer idGiohang, idKhachHang, id, soLuong;

    public Cart() {
    }

    public Cart(Integer idGiohang, Integer idKhachHang, Integer id, Integer soLuong) {
        this.idGiohang = idGiohang;
        this.idKhachHang = idKhachHang;
        this.id = id;
        this.soLuong = soLuong;
    }

    public Cart(Integer idKhachHang, Integer id, Integer soLuong) {
        this.idKhachHang = idKhachHang;
        this.id = id;
        this.soLuong = soLuong;
    }

    public Integer getIdGiohang() {
        return idGiohang;
    }

    public void setIdGiohang(Integer idGiohang) {
        this.idGiohang = idGiohang;
    }

    public Integer getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(Integer idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }
}
