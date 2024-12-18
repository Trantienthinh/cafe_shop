package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Cart;
import model.Product;


public class CartController implements Initializable {

    @FXML
    private Label giaSP;

    @FXML
    private ImageView hinhSP;

    @FXML
    private Label loaiSP;

    @FXML
    private Label soLuong;

    @FXML
    private Label soThuTu;

    @FXML
    private Label tenSP;

    @FXML
    private Label tongTien;

    @FXML
    private Label xuatXu;
    
    private Cart GH;
    private Product SP;
    private Image img;
    private Double tongT = 0.0;
    
    public void sDuLieuSP(int stt, Cart SPGioHang){
        this.GH = SPGioHang;       
        this.SP = DAO.DAOProduct.createInstance().TimKiem(this.GH.getId());
        String chuoiTmp = SP.getHinhAnh().substring(0, 6);
        String duongDan;
        if(chuoiTmp.equals("/image")){
            duongDan = SP.getHinhAnh();
        }else{
            duongDan = "File:" + SP.getHinhAnh(); 
        } 
        img = new Image(duongDan, 111, 140, false, true); 
        this.tenSP.setText(this.SP.getTenSanPham());
        this.giaSP.setText("$" + String.valueOf(this.SP.getGiaCa()));
        this.hinhSP.setImage(img);
        this.loaiSP.setText(this.SP.getLoai());
        this.soLuong.setText(String.valueOf(this.GH.getSoLuong()));
        this.tongT = this.GH.getSoLuong() * this.SP.getGiaCa();
        this.tongTien.setText("$" + String.valueOf(tongT)); 
        this.soThuTu.setText(String.valueOf(stt));
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
