package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import model.Product;

public class CardProductController implements Initializable {

    @FXML
    private ImageView danhGia;

    @FXML
    private Label c_giaSP;

    @FXML
    private ImageView c_hinhAnh;

    @FXML
    private Button c_nutXem;

    @FXML
    private Label c_tenSP;

    @FXML
    private HBox hopChua;

    private String[] mauSac = {"B9E5FF", "BDB2FE", "FB9AA8", "FF5056"};
    private Product SP;
    private Image img;

    public void sSanPhamNoiBat(Product SanPham) {
        this.SP = SanPham;
        String chuoiTmp = SP.getHinhAnh().substring(0, 6);
        String duongDan;
        if (chuoiTmp.equals("/image")) {
            duongDan = SP.getHinhAnh();
        } else {
            duongDan = "File:" + SP.getHinhAnh();
        }
        img = new Image(duongDan, 111, 140, false, true);
        this.c_tenSP.setText(this.SP.getTenSanPham());
        this.c_giaSP.setText("$" + String.valueOf(this.SP.getGiaCa()));
        this.c_hinhAnh.setImage(img);
        this.danhGia.setImage(new Image("/image/kisspng-5-star.png"));
        this.hopChua.setStyle("-fx-background-color: #159678;"
                + "-fx-background-radius: 15;"
                + "-fx-effect:-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0), 10, 0, 0, 10);");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
