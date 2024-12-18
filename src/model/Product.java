/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


import java.io.Serializable;
import java.sql.Date;

public class Product implements Serializable{ 

    private static final long serialVersionUID = 1L;
    private Integer soThuTu;
    private String maSanPham;
    private String tenSanPham;
    private String loai;
    private Integer soLuong;
    private Double giaCa;
    private String trangThai;
    private String hinhAnh;
    private Date ngay;

    public Product() {
    }

    public Product(String maSanPham, String tenSanPham, String loai, Integer soLuong, Double giaCa, String trangThai, String hinhAnh, Date ngay) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.loai = loai;
        this.soLuong = soLuong;
        this.giaCa = giaCa;
        this.trangThai = trangThai;
        this.hinhAnh = hinhAnh;
        this.ngay = ngay;
    }

    public Product(Integer soThuTu, String maSanPham, String tenSanPham, Double giaCa, String hinhAnh) {
        this.soThuTu = soThuTu;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.giaCa = giaCa;
        this.hinhAnh = hinhAnh;
    }
    

    @Override
    public String toString() {
        return "Product{" + "soThuTu=" + soThuTu + ", maSanPham=" + maSanPham + ", tenSanPham=" + tenSanPham + ", loai=" + loai + ", soLuong=" + soLuong + ", giaCa=" + giaCa + ", trangThai=" + trangThai + ", hinhAnh=" + hinhAnh + ", ngay=" + ngay + '}';
    }

    public Integer getSoThuTu() {
        return soThuTu;
    }

    public void setSoThuTu(Integer soThuTu) {
        this.soThuTu = soThuTu;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Double getGiaCa() {
        return giaCa;
    }

    public void setGiaCa(Double giaCa) {
        this.giaCa = giaCa;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

}
