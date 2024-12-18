package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Data;
import model.OrderDetail;
import model.Product;


public class ProductContainerController implements Initializable { // đổi từ "cardProductController"
    
    @FXML
    private AnchorPane card_form;

    @FXML
    private AnchorPane containHinhAnh;

    @FXML
    private Button nutThem;

    @FXML
    private Label sp_gia;

    @FXML
    private ImageView sp_hinhAnh;

    @FXML
    private Spinner<Integer> sp_soLuong;

    @FXML
    private Label sp_ten;
    
    private Product duLieuSP;
    
    private Alert alert;
    private String maSanPham; 
    private String loai; 
    private String ngaySanPham; 
    private String hinhAnhSanPhamDuLieu;   
    private Image img;   
    private SpinnerValueFactory<Integer> nutTangGiamSL; 

    public void setDuLieu(Product duLieuSP) {
        this.duLieuSP = duLieuSP;

        hinhAnhSanPhamDuLieu = duLieuSP.getHinhAnh(); 
        ngaySanPham = String.valueOf(duLieuSP.getNgay()); 
        loai = duLieuSP.getLoai(); 
        maSanPham = duLieuSP.getMaSanPham(); 
        sp_ten.setText(duLieuSP.getTenSanPham()); 
        sp_gia.setText("$" + String.valueOf(duLieuSP.getGiaCa())); 
        String chuoiTmp = duLieuSP.getHinhAnh().substring(0, 6);
        String duongDan;
        if(chuoiTmp.equals("/image")){
            duongDan = duLieuSP.getHinhAnh();
        }else{
            duongDan = "File:" + duLieuSP.getHinhAnh(); 
        } 
        img = new Image(duongDan, 192, 198, false, true); 
        sp_hinhAnh.setImage(img); 
//        gia = duLieuSP.getGiaCa()get; 
    }

    private int soLuong; 
    public void datSoLuong() {
        nutTangGiamSL = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        sp_soLuong.setValueFactory(nutTangGiamSL); 
    }
    private double tongGia; 
    private double gia; 

    public void btnThemSanPham() { 
        
        HomeAdminController t = new HomeAdminController();
//        t.layMaHoaDon();
        soLuong = nutTangGiamSL.getValue();        
        try {
            int kiemTraSoLuong = 0; 
            String kiemTra = duLieuSP.getTrangThai();
            kiemTraSoLuong = duLieuSP.getSoLuong();

            if (kiemTraSoLuong == 0) { 
                duLieuSP.setSoLuong(0);
                duLieuSP.setTrangThai("Hết hàng");
            }
            if (!kiemTra.equals("Còn hàng") || soLuong == 0) { 
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Thông báo lỗi"); 
                alert.setHeaderText(null);
                alert.setContentText("Có lỗi xảy ra"); 
                alert.showAndWait(); 
            } else {
                if (kiemTraSoLuong < soLuong) { 
                    alert = new Alert(AlertType.ERROR); 
                    alert.setTitle("Thông báo lỗi"); 
                    alert.setHeaderText(null);
                    alert.setContentText("Không hợp lệ. Sản phẩm này đã hết hàng");
                    alert.showAndWait(); 
                } else {
                    hinhAnhSanPhamDuLieu = hinhAnhSanPhamDuLieu.replace("\\", "\\\\");
                    tongGia = (soLuong * duLieuSP.getGiaCa());
                    OrderDetail od = new OrderDetail(duLieuSP.getSoThuTu(), 1, soLuong, duLieuSP.getGiaCa());
                    Data.dsCTSP.add(od);
                    int capNhatTonKho = kiemTraSoLuong - soLuong; 
                    
                    System.out.println("Ngày: " + ngaySanPham); 
                    System.out.println("Hình ảnh: " + hinhAnhSanPhamDuLieu); 

                    duLieuSP.setSoLuong(capNhatTonKho);
                    DAO.DAOProduct.createInstance().Sua(duLieuSP, duLieuSP.getSoThuTu());

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Thông báo thông tin"); 
                    alert.setHeaderText(null);
                    alert.setContentText("Thêm thành công!"); 
                    alert.showAndWait(); 

                    t.layTongTienHoaDon(); 
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        datSoLuong(); 
    }
}
