package model;

import java.util.Date;

public class Notification {
    private Integer idThongBao, idKhachHang;
    private String tieuDe, noiDUng;
    private Date ngayTao;

    public Notification() {
    }

    public Notification(Integer idThongBao, Integer idKhachHang, String tieuDe, String noiDUng, Date ngayTao) {
        this.idThongBao = idThongBao;
        this.idKhachHang = idKhachHang;
        this.tieuDe = tieuDe;
        this.noiDUng = noiDUng;
        this.ngayTao = ngayTao;
    }

    public Notification(Integer idKhachHang, String tieuDe, String noiDUng, Date ngayTao) {
        this.idKhachHang = idKhachHang;
        this.tieuDe = tieuDe;
        this.noiDUng = noiDUng;
        this.ngayTao = ngayTao;
    }

    @Override
    public String toString() {
        return "Notification{" + "idThongBao=" + idThongBao + ", idKhachHang=" + idKhachHang + ", tieuDe=" + tieuDe + ", noiDUng=" + noiDUng + ", ngayTao=" + ngayTao + '}';
    }

    public Integer getIdThongBao() {
        return idThongBao;
    }

    public void setIdThongBao(Integer idThongBao) {
        this.idThongBao = idThongBao;
    }

    public Integer getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(Integer idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDUng() {
        return noiDUng;
    }

    public void setNoiDUng(String noiDUng) {
        this.noiDUng = noiDUng;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

}
