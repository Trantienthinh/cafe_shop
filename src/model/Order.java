/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;


public class Order {
    private Integer idHoaDon;
    private Integer idKhachHang;
    private Integer idNhanVien;
    private Double tongTien;
    private Date ngayLap;

    public Order() {
    }

    public Order(Integer idKhachHang, Integer idNhanVien, Double tongTien, Date ngayLap) {
        this.idKhachHang = idKhachHang;
        this.idNhanVien = idNhanVien;
        this.tongTien = tongTien;
        this.ngayLap = ngayLap;
    }

    public Order(Integer idHoaDon, Integer idKhachHang, Integer idNhanVien, Double tongTien, Date ngayLap) {
        this.idHoaDon = idHoaDon;
        this.idKhachHang = idKhachHang;
        this.idNhanVien = idNhanVien;
        this.tongTien = tongTien;
        this.ngayLap = ngayLap;
    }

    public Integer getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(Integer idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public Integer getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(Integer idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public Integer getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(Integer idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }
    
}
