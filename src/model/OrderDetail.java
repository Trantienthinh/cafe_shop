package model;

public class OrderDetail {
    private Integer idCTHoaDon;
    private Integer id;
    private Integer idHoaDon;
    private Integer soLuong;
    private Double gia;

    public OrderDetail() {
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "idCTHoaDon=" + idCTHoaDon + ", id=" + id + ", idHoaDon=" + idHoaDon + ", soLuong=" + soLuong + '}';
    }

    public OrderDetail(Integer id, Integer idHoaDon, Integer soLuong, Double gia) {
        this.id = id;
        this.idHoaDon = idHoaDon;
        this.soLuong = soLuong;
        this.gia = gia;
    }

    public OrderDetail(Integer id, Integer idHoaDon, Integer soLuong) {
        this.id = id;
        this.idHoaDon = idHoaDon;
        this.soLuong = soLuong;
    }

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }

    public Integer getIdCTHoaDon() {
        return idCTHoaDon;
    }

    public void setIdCTHoaDon(Integer idCTHoaDon) {
        this.idCTHoaDon = idCTHoaDon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(Integer idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }
    
    
}
