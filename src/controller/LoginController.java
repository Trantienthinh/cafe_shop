package controller;

import java.net.*;
import java.io.*;
import Net.HoldData;
import database.JDBCutil;
import java.io.IOException;
import java.net.Socket;
import java.sql.*;
import java.util.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Data;

/**
 *
 * @author PC
 */
public class LoginController implements Initializable {

    @FXML
    private Button fb_backBtn;

    @FXML
    private Button fp_backBtn2;

    @FXML
    private Button fp_changeBtn;

    @FXML
    private AnchorPane fp_changePassword;

    @FXML
    private Button fp_comfirmBtn;

    @FXML
    private ComboBox<?> fp_dob;

    @FXML
    private TextField fp_fullname;

    @FXML
    private PasswordField fp_newPass;

    @FXML
    private PasswordField fp_oldPass;

    @FXML
    private AnchorPane fp_questionForm;

    @FXML
    private TextField fp_username;

    @FXML
    private Hyperlink lg_forgotPass;

    @FXML
    private AnchorPane lg_loginForm;

    @FXML
    private Button lg_loginbtn;

    @FXML
    private PasswordField lg_password;

    @FXML
    private TextField lg_username;

    @FXML
    private TextField rg_address;

    @FXML
    private ComboBox<?> rg_dob;

    @FXML
    private TextField rg_fullName;

    @FXML
    private ComboBox<?> rg_gender;

    @FXML
    private PasswordField rg_password;

    @FXML
    private TextField rg_phone;

    @FXML
    private Button rg_registerBtn;

    @FXML
    private AnchorPane rg_registerForm;

    @FXML
    private TextField rg_username;

    @FXML
    private Button side_alreadyHave;

    @FXML
    private Button side_createBtn;

    @FXML
    private AnchorPane side_form;

    private Connection cnt;
    private PreparedStatement pr;
    private ResultSet rs;

    private List<String> gender = new ArrayList<>();
    private List<String> dob = new ArrayList<>();
    private Alert alert;

    public void createAccount() {
        java.util.Date date = new java.util.Date();
        java.sql.Date sqldate = new java.sql.Date(date.getTime());

        if (rg_fullName.getText().isEmpty() || rg_phone.getText().isEmpty() || rg_address.getText().isEmpty()
                || rg_gender.getSelectionModel().getSelectedItem() == null || rg_password.getText().isEmpty()
                || rg_dob.getSelectionModel().getSelectedItem() == null || rg_username.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo lỗi!");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng điền đầy đủ thông tin cần thiết");
            alert.showAndWait();
        } else {
            try {

                String query = "INSERT INTO KhachHang(hoVaTen, soDienThoai, diaChi, gioiTinh, ngaySinh, taiKhoan, matKhau, ngayTao)"
                        + " VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
                cnt = JDBCutil.getConnection();

                String checkUserName = "SELECT taiKhoan FROM KhachHang WHERE taiKhoan = '" + rg_username.getText() + "';";
                pr = cnt.prepareStatement(checkUserName);
                rs = pr.executeQuery();
                if (rs.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Thông báo lỗi!!");
                    alert.setHeaderText(null);
                    alert.setContentText("Tên tài khoản đã tồn tại vui lòng chọn tên khác!");
                    alert.showAndWait();
                } else if (rg_password.getText().length() < 8) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Thông báo lỗi!!");
                    alert.setHeaderText(null);
                    alert.setContentText("Mật khẩu phải có ít nhất 8 ký tự!");
                    alert.showAndWait();
                } else {
                    pr = cnt.prepareStatement(query);
                    pr.setString(1, rg_fullName.getText());
                    pr.setString(2, rg_phone.getText());
                    pr.setString(3, rg_address.getText());
                    pr.setString(4, (String) rg_gender.getSelectionModel().getSelectedItem());
                    pr.setString(5, (String) rg_dob.getSelectionModel().getSelectedItem());
                    pr.setString(6, rg_username.getText());
                    pr.setString(7, rg_password.getText());
                    pr.setString(8, String.valueOf(sqldate));

                    pr.execute();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo thông tin!");
                    alert.setHeaderText(null);
                    alert.setContentText("Tài khoản đã được tạo thành công!");
                    alert.showAndWait();

                    rg_fullName.setText("");
                    rg_phone.setText("");
                    rg_address.setText("");
                    rg_gender.getSelectionModel().clearSelection();
                    rg_dob.getSelectionModel().clearSelection();
                    rg_username.setText("");
                    rg_password.setText("");

                    TranslateTransition slider = new TranslateTransition();
                    slider.setNode(side_form);
                    slider.setToX(0);
                    slider.setDuration(Duration.seconds(.5));

                    slider.setOnFinished((ActionEvent e) -> {
                        side_alreadyHave.setVisible(false);
                        side_createBtn.setVisible(true);
                    });

                    slider.play();
                }

                JDBCutil.closeConection(cnt);
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void loginAccount() {
        if (lg_password.getText().isEmpty() || lg_username.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo lỗi!");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng điền đầy đủ thông tin cần thiết");
            alert.showAndWait();
        } else {
//            try {
            HoldData tmp = new HoldData("dangNhap", lg_username.getText(), lg_password.getText());
////                cnt = JDBCutil.getConnection();
////                String query = "SELECT taiKhoan, matKhau FROM NhanVien WHERE taiKhoan = ? AND matKhau = ?;";
////                pr = cnt.prepareStatement(query);
////                pr.setString(1, lg_username.getText());
////                pr.setString(2, lg_password.getText());
////                rs = pr.executeQuery();
//                if (rs.next()) {
//                    alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Thông báo!");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Đăng nhập tài khoản thành công");
//                    alert.showAndWait();
//
//                    Data.taiKhoan = lg_username.getText();
//
//                    Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLHomeAdmin.fxml"));
//                    Stage stage = new Stage();
//                    Scene scene = new Scene(root);
//                    stage.setTitle("Cafe Shop System");
//                    stage.setMinHeight(600);
//                    stage.setWidth(1300);
//                    stage.setScene(scene);
//                    stage.show();
//
//                    lg_loginbtn.getScene().getWindow().hide();
//
//                } else {
//                    query = "SELECT taiKhoan, matKhau FROM KhachHang WHERE taiKhoan = ? AND matKhau = ?;";
//                    pr = cnt.prepareStatement(query);
//                    pr.setString(1, lg_username.getText());
//                    pr.setString(2, lg_password.getText());
//                    rs = pr.executeQuery();
//                    if (rs.next()) {
//                        alert = new Alert(Alert.AlertType.INFORMATION);
//                        alert.setTitle("Thông báo!");
//                        alert.setHeaderText(null);
//                        alert.setContentText("Đăng nhập tài khoản thành công");
//                        alert.showAndWait();
//
//                        Data.taiKhoan = lg_username.getText();
//
//                        Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLHomeUser.fxml"));
//                        Stage stage = new Stage();
//                        Scene scene = new Scene(root);
//                        stage.setTitle("Cafe Shop System");
//                        stage.setMinHeight(600);
//                        stage.setWidth(1300);
//                        stage.setScene(scene);
//                        stage.setResizable(false);
//                        stage.show();
//                        
//                        lg_loginbtn.getScene().getWindow().hide();
//                    } else {
//                        alert = new Alert(Alert.AlertType.ERROR);
//                        alert.setTitle("Thông báo lỗi!");
//                        alert.setHeaderText(null);
//                        alert.setContentText("Tài khoản hoặc mật khẩu không chính xác");
//                        alert.showAndWait();
//                    }
//                }
//                JDBCutil.closeConection(cnt);
            new Thread(() -> {
                try (Socket socket = new Socket("localhost", 9999); ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream()); ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

                    out.writeObject(tmp);
                    String response = (String) in.readObject();
                    Platform.runLater(()->{
                        if ("NhanVien".equalsIgnoreCase(response)) {
                            try {
                                alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Thông báo!");
                                alert.setHeaderText(null);
                                alert.setContentText("Đăng nhập tài khoản thành công");
                                alert.showAndWait();
                                
                                Data.taiKhoan = lg_username.getText();
                                
                                Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLHomeAdmin.fxml"));
                                Stage stage = new Stage();
                                Scene scene = new Scene(root);
                                stage.setTitle("Cafe Shop System");
                                stage.setMinHeight(600);
                                stage.setWidth(1300);
                                stage.setScene(scene);
                                stage.show();
                                
                                lg_loginbtn.getScene().getWindow().hide();
                            } catch (IOException ex) {
                                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else if ("KhachHang".equalsIgnoreCase(response)) {
                            try {
                                alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Thông báo!");
                                alert.setHeaderText(null);
                                alert.setContentText("Đăng nhập tài khoản thành công");
                                alert.showAndWait();
                                
                                Data.taiKhoan = lg_username.getText();
                                
                                Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLHomeUser.fxml"));
                                Stage stage = new Stage();
                                Scene scene = new Scene(root);
                                stage.setTitle("Cafe Shop System");
                                stage.setMinHeight(600);
                                stage.setWidth(1300);
                                stage.setScene(scene);
                                stage.setResizable(false);
                                stage.show();
                                
                                lg_loginbtn.getScene().getWindow().hide();
                            } catch (IOException ex) {
                                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Thông báo lỗi!");
                            alert.setHeaderText(null);
                            alert.setContentText("Tài khoản hoặc mật khẩu không chính xác");
                            alert.showAndWait();
                        }
                    });
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }).start();
//            } catch (SQLException ex) {
//                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }

    public void setValue() {
        if (gender.isEmpty()) {
            for (int i = 1990; i < 2024; i++) {
                this.dob.add(String.valueOf(i));
            }
            this.gender.add("Nam");
            this.gender.add("Nu");

            ObservableList genderData = FXCollections.observableArrayList(gender);
            ObservableList dobData = FXCollections.observableArrayList(dob);
            rg_gender.setItems(genderData);
            rg_dob.setItems(dobData);
            fp_dob.setItems(dobData);
        }
    }

    public void switchForgotPass() {
        setValue();
        fp_questionForm.setVisible(true);
        lg_loginForm.setVisible(false);
    }

    public void forgotPass() {
        if (fp_dob.getSelectionModel().getSelectedItem() == null || fp_fullname.getText().isEmpty() || fp_username.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo lỗi!");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng điền đầy đủ thông tin cần thiết");
            alert.showAndWait();
        } else {
            try {
                String query = "SELECT taiKhoan, ngaySinh, hoVaTen FROM KhachHang WHERE taiKhoan = ? AND ngaySinh = ? AND hoVaTen = ?;";
                cnt = JDBCutil.getConnection();
                pr = cnt.prepareStatement(query);
                pr.setString(1, fp_username.getText());
                pr.setString(2, (String) fp_dob.getSelectionModel().getSelectedItem());
                pr.setString(3, fp_fullname.getText());
                rs = pr.executeQuery();
                if (rs.next()) {
                    fp_changePassword.setVisible(true);
                    fp_questionForm.setVisible(false);
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Thông báo lỗi!");
                    alert.setHeaderText(null);
                    alert.setContentText("Thông tin xác nhận không chính xác");
                    alert.showAndWait();
                }
                JDBCutil.closeConection(cnt);
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void changePass() {
        if (fp_oldPass.getText().isEmpty() || fp_newPass.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo lỗi!");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng điền đầy đủ thông tin cần thiết");
            alert.showAndWait();
        } else if (!fp_oldPass.getText().equals(fp_newPass.getText())) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo lỗi!");
            alert.setHeaderText(null);
            alert.setContentText("Mật khẩu nhập lại chưa chính xác!");
            alert.showAndWait();
        } else {
            try {
                String query = "UPDATE KhachHang SET matKhau = ? WHERE taiKhoan = '" + fp_username.getText() + "';";
                cnt = JDBCutil.getConnection();
                pr = cnt.prepareStatement(query);
                pr.setString(1, fp_newPass.getText());
                pr.execute();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo!");
                alert.setHeaderText(null);
                alert.setContentText("Thay đổi mật khẩu thành công");
                alert.showAndWait();

                fp_changePassword.setVisible(false);
                lg_loginForm.setVisible(true);
                JDBCutil.closeConection(cnt);
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void backLoginForm() {
        fp_questionForm.setVisible(false);
        lg_loginForm.setVisible(true);
    }

    public void backQuestionForm() {
        fp_questionForm.setVisible(true);
        fp_changePassword.setVisible(false);
    }

    public void switchForm(ActionEvent event) {
        TranslateTransition slider = new TranslateTransition();
        setValue();
        if (event.getSource() == side_createBtn) {
            slider.setNode(side_form);
            slider.setToX(300);
            slider.setDuration(Duration.seconds(.5));

            slider.setOnFinished((ActionEvent e) -> {
                side_alreadyHave.setVisible(true);
                side_createBtn.setVisible(false);

                fp_questionForm.setVisible(false);
                lg_loginForm.setVisible(true);
                fp_changePassword.setVisible(false);
            });

            slider.play();

        } else if (event.getSource() == side_alreadyHave) {
            slider.setNode(side_form);
            slider.setToX(0);
            slider.setDuration(Duration.seconds(.5));

            slider.setOnFinished((ActionEvent e) -> {
                side_alreadyHave.setVisible(false);
                side_createBtn.setVisible(true);

                fp_questionForm.setVisible(false);
                lg_loginForm.setVisible(true);
                fp_changePassword.setVisible(false);
            });

            slider.play();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
