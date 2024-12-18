package controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Cart;
import model.Data;
import model.Product;

public class CardProduct2Controller implements Initializable {
    
    @FXML
    private Label giaSP;

    @FXML
    private ImageView hinhSP;

    @FXML
    private Button nutXem;

    @FXML
    private Label tenSP;
    private Product SP;
    private String hinhAnh;
    private Image img;
    private TextInputDialog dialog;
    private Alert alert;
    
    public void sSanPham(Product SanPham){
        this.SP = SanPham;
        
        hinhAnh = SP.getHinhAnh();
        String chuoiTmp = SP.getHinhAnh().substring(0, 6);
        String duongDan;
        if(chuoiTmp.equals("/image")){
            duongDan = SP.getHinhAnh();
        }else{
            duongDan = "File:" + SP.getHinhAnh(); 
        } 
        img = new Image(duongDan, 168, 210, false, true); 
        this.tenSP.setText(this.SP.getTenSanPham());
        this.giaSP.setText("$" + String.valueOf(this.SP.getGiaCa()));
        this.hinhSP.setImage(img);
//        this.hopChua.setStyle("-fx-background-color: #" + mauSac[(int)(Math.random()*mauSac.length)] + ";" + 
//                               "-fx-background-radius: 15;" + 
//                               "-fx-effect:-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0), 10, 0, 0, 10);");
    }
    
    public void nutThemVaoGio(){
        dialog = new TextInputDialog();
        dialog.setTitle("Nhập thông tin");
        dialog.setHeaderText("Nhập số lượng sản phẩm để thêm vào giỏ hàng cảu bạn! ");
        dialog.setContentText("Số lượng: ");

        Optional<String> result = dialog.showAndWait();
        if(result.isEmpty()){
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng nhập số lượng sản phẩm!");
            alert.showAndWait();
        }else{
            Integer soLuong  = Integer.parseInt(result.get());
            Cart tmp = new Cart(DAO.DAOCustomer.createInstance().layMaKhachHang(Data.taiKhoan), this.SP.getSoThuTu(), soLuong);
            DAO.DAOCart.themSP(tmp);
            
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Đã thêm sản phẩm vào giỏ hàng!");
            alert.showAndWait();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
