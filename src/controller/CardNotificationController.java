/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Notification;

public class CardNotificationController implements Initializable {

    @FXML
    private FontAwesomeIcon icon;

    @FXML
    private Label nhanIcon;

    @FXML
    private Label noiDUng;

    @FXML
    private Label thoiGian;

    @FXML
    private Label tieuDe;
    
    
    private String[] mauSac = {"#16695c", "#2a28bc", "#299aba", "#25d74b", "#d62a27", "#e124cf"};
    private String[] glypName = {"PAPER_PLANE", "PHONE", "GLOBE", "PINTEREST"};
    
    public void sDuLieu(Notification n){
        icon.setGlyphName(glypName[(int)(Math.random()*glypName.length)]);
        tieuDe.setText(n.getTieuDe());
        noiDUng.setText(n.getNoiDUng());
        thoiGian.setText(String.valueOf(n.getNgayTao()));
        nhanIcon.setStyle("-fx-background-color: " + mauSac[(int)(Math.random()*mauSac.length)] + ";" + 
                          "-fx-border-radius: 50%;");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
