package controller;

import Net.HoldData;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Cart;
import model.Customer;
import model.Data;
import model.Notification;
import model.Order;
import model.OrderDetail;
import model.Product;

public class HomeUserController implements Initializable {

    @FXML
    private VBox vungChuaSPDaMua;

    @FXML
    private AnchorPane formCapNhatTTCN;

    @FXML
    private AnchorPane formThongBao;

    @FXML
    private AnchorPane formDoiMatKhau;

    @FXML
    private AnchorPane formGioHang;

    @FXML
    private AnchorPane formSanPham;

    @FXML
    private AnchorPane formTimKiem;

    @FXML
    private AnchorPane formTrangChu;

    @FXML
    private GridPane hopChuaSP;

    @FXML
    private VBox hopChuaSPGioHang;

    @FXML
    private HBox hopChuaSPNB;

    @FXML
    private GridPane hopChuaSPTheoTimKiem;

    @FXML
    private PasswordField mk_NhapLaiMKMoi;

    @FXML
    private FontAwesomeIcon mk_iconQuayLai;

    @FXML
    private FontAwesomeIcon mk_iconthoat;

    @FXML
    private PasswordField mk_matKhauCu;

    @FXML
    private PasswordField mk_matKhauMoi;

    @FXML
    private Button mk_nutDoiMatKhau;

    @FXML
    private MenuItem nutCapNhatTT;

    @FXML
    private MenuItem nutDangXuat;

    @FXML
    private MenuItem nutDoiMK;

    @FXML
    private Button nutGioHang;

    @FXML
    private Button nutSanPham;

    @FXML
    private Button nutThanhToan;

    @FXML
    private Button nutThemSPVaoGio;

    @FXML
    private Button nutThongBao;

    @FXML
    private FontAwesomeIcon nutTimKiem;

    @FXML
    private Button nutTrangChu;

    @FXML
    private Button nutXoaSpTrongGio;

    @FXML
    private TextField thanhTimKiem;

    @FXML
    private Label thongBaoTK;

    @FXML
    private TextField ttc_daiChiKH;

    @FXML
    private TextField ttc_gioiTinhKH;

    @FXML
    private TextField ttc_ngaySinhKH;

    @FXML
    private TextField ttc_soDTKH;

    @FXML
    private TextField ttc_tenKH;

    @FXML
    private TextField ttm_diaChiKH;

    @FXML
    private TextField ttm_gioiTinhKH;

    @FXML
    private TextField ttm_ngaySinhKH;

    @FXML
    private Button ttm_nutCapNhat;

    @FXML
    private TextField ttm_soDTKH;

    @FXML
    private TextField ttm_tenKH;

    @FXML
    private MenuButton us_taiKhoan;

    @FXML
    private VBox vungChuaTB;

    @FXML
    private ScrollPane scrollPaneThongBao;

    @FXML
    private ScrollPane scrollPaneSPDaMua;

    @FXML
    private Label home_tongSP;

    @FXML
    private Label home_tongTien;

    private List<Product> dsSPNB;
    private List<Product> dsSP;
    private List<Product> dsSPTK;
    private List<Product> dsSPDM;
    private List<Cart> dsSPGH;
    private List<Notification> dsTB;
    private Alert alert;
    private Double tongTienGioHang = 0.0;

    public void hienThiDSSanPhamNoiBat() {
        dsSPNB = new ArrayList<>(DAO.DAOProduct.createInstance().LaySanPhamNoiBat());
        dsSP = new ArrayList<>(DAO.DAOProduct.createInstance().LayTT());
        int cot = 0;
        int dong = 1;

        hopChuaSPNB.getChildren().clear();
        hopChuaSP.getChildren().clear();
        try {
            for (Product x : dsSPNB) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/FXMLCardProduct.fxml"));
                HBox box = loader.load();
                CardProductController c = loader.getController();
                c.sSanPhamNoiBat(x);
                hopChuaSPNB.getChildren().add(box);
            }

            for (Product x : dsSP) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/FXMLCardProduct2.fxml"));
                VBox box2 = loader.load();
                CardProduct2Controller c2 = loader.getController();
                c2.sSanPham(x);

                if (cot == 5) {
                    cot = 0;
                    ++dong;
                }
                hopChuaSP.add(box2, cot++, dong);
                GridPane.setMargin(box2, new Insets(15));
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void hienThiSPTheoTimKiem() {
        if (thanhTimKiem.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng nhập tên sản phẩm muốn tìm kiếm!");
            alert.showAndWait();
        } else {
            new Thread(() -> {
                try (Socket socket = new Socket("localhost", 9999); ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream()); ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
                    HoldData tmp = new HoldData("TimKiem", thanhTimKiem.getText());
                    out.writeObject(tmp);
                    dsSPTK = (List<Product>) in.readObject();
                    Platform.runLater(() -> {
                        if (dsSPTK.isEmpty()) {
                            thongBaoTK.setText("Không có sản phẩm nào phù hợp với từ khóa tìm kiếm: \"" + thanhTimKiem.getText() + "\"");
                        } else {
                            thongBaoTK.setText("Danh sách sản phẩm phù hợp với từ khóa tìm kiếm: \"" + thanhTimKiem.getText() + "\"");
                            int cot = 0;
                            int dong = 1;
                            hopChuaSPTheoTimKiem.getChildren().clear();
                            try {
                                for (Product x : dsSPTK) {
                                    FXMLLoader loader = new FXMLLoader();
                                    loader.setLocation(getClass().getResource("/view/FXMLCardProduct2.fxml"));
                                    VBox box2 = loader.load();
                                    CardProduct2Controller c2 = loader.getController();
                                    c2.sSanPham(x);

                                    if (cot == 5) {
                                        cot = 0;
                                        ++dong;
                                    }
                                    hopChuaSPTheoTimKiem.add(box2, cot++, dong);
                                    GridPane.setMargin(box2, new Insets(15));
                                }
                            } catch (IOException ex) {
                                System.out.println(ex);
                            }
                            formTrangChu.setVisible(false);
                            formTimKiem.setVisible(true);
                            formSanPham.setVisible(false);
                            formGioHang.setVisible(false);
                            formDoiMatKhau.setVisible(false);
                        }
                    });
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }).start();
//            HoldData tmp = new HoldData("DangNhap", thanhTimKiem.getText());
////            dsSPTK = new ArrayList<>(DAO.DAOProduct.createInstance().TimSanPham(thanhTimKiem.getText()));
//            dsSPTK = 
//            if (dsSPTK.isEmpty()) {
//                thongBaoTK.setText("Không có sản phẩm nào phù hợp với từ khóa tìm kiếm: \"" + thanhTimKiem.getText() + "\"");
//            } else {
//                thongBaoTK.setText("Danh sách sản phẩm phù hợp với từ khóa tìm kiếm: \"" + thanhTimKiem.getText() + "\"");
//                int cot = 0;
//                int dong = 1;
//                hopChuaSPTheoTimKiem.getChildren().clear();
//                try {
//                    for (Product x : dsSPTK) {
//                        FXMLLoader loader = new FXMLLoader();
//                        loader.setLocation(getClass().getResource("/view/FXMLCardProduct2.fxml"));
//                        VBox box2 = loader.load();
//                        CardProduct2Controller c2 = loader.getController();
//                        c2.sSanPham(x);
//
//                        if (cot == 5) {
//                            cot = 0;
//                            ++dong;
//                        }
//                        hopChuaSPTheoTimKiem.add(box2, cot++, dong);
//                        GridPane.setMargin(box2, new Insets(15));
//                    }
//                } catch (IOException ex) {
//                    System.out.println(ex);
//                }
//                formTrangChu.setVisible(false);
//                formTimKiem.setVisible(true);
//                formSanPham.setVisible(false);
//                formGioHang.setVisible(false);
//                formDoiMatKhau.setVisible(false);
//            }
        }
    }

    public List<Cart> layDLSPtrongGioHang() {
        Integer maKH = DAO.DAOCustomer.createInstance().layMaKhachHang(Data.taiKhoan);
        this.dsSPGH = new ArrayList<>(DAO.DAOCart.LayTT(maKH));
        return this.dsSPGH;
    }

    public void hienThiSPTrongGioHang() {
        this.dsSPGH.clear();
        this.dsSPGH = new ArrayList<>(layDLSPtrongGioHang());
        hopChuaSPGioHang.getChildren().clear();
        try {
            for (int i = 0; i < dsSPGH.size(); i++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/FXMLCart.fxml"));
                HBox box = loader.load();
                CartController c = loader.getController();
                c.sDuLieuSP(i, dsSPGH.get(i));
                hopChuaSPGioHang.getChildren().add(box);

            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void nutThanhToan() {
        if (this.dsSPGH.isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Chưa có sản phẩm nào trong giỏ hàng. Vui lòng thêm sản phẩm để thanh toán!");
            alert.showAndWait();
        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo xác nhận");
            alert.setHeaderText(null);
            alert.setContentText("Bạn có chắc chắn muốn thanh toán tất cả các sản phẩm có trong giỏ hàng không?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                Integer maKH = DAO.DAOCustomer.createInstance().layMaKhachHang(Data.taiKhoan);
                for (Cart x : this.dsSPGH) {
                    DAO.DAOProduct.createInstance().capNhatSoLuongBot(x.getId(), x.getSoLuong());
                    this.tongTienGioHang += x.getSoLuong() * DAO.DAOProduct.createInstance().TimKiem(x.getId()).getGiaCa();
                    System.out.println(this.tongTienGioHang);
                }
                Date d = new Date();
                java.sql.Date dsql = new java.sql.Date(d.getTime());
                Order o = new Order(maKH, 1, this.tongTienGioHang, dsql);
                DAO.DAOOrder.createInstance().Them(o);
                Integer idHoaDon = DAO.DAOOrder.createInstance().layMaHoaDonMoiLuu();
                for (Cart x : this.dsSPGH) {
                    OrderDetail od = new OrderDetail(x.getId(), idHoaDon, x.getSoLuong());
                    DAO.DAOOrderDetail.createInstance().Them(od);
                }
                DAO.DAOCart.xoaSPCuaKH(maKH);
                this.tongTienGioHang = 0.0;
                hienThiSPTrongGioHang();
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Đã thanh toán các sản phẩm trong giỏ hàng của bạn!");
                alert.showAndWait();
                Notification tmp = new Notification(DAO.DAOCustomer.createInstance().layMaKhachHang(Data.taiKhoan), "Thanh toán sản phẩm", "Tất cả sản phẩm trong giỏ hàng của bạn đã được thanh toán thành công. Thông tin về đơn hàng sẽ được cập nhật trong tài khoản của quý khách .Vui lòng kiểm tra lại đơn hàng trước khi nhận hàng.", dsql);
            }
        }
    }

    public void nutThemSPVaoGio() {
        formTrangChu.setVisible(false);
        formTimKiem.setVisible(false);
        formSanPham.setVisible(true);
        formGioHang.setVisible(false);
        formDoiMatKhau.setVisible(false);
        formCapNhatTTCN.setVisible(false);
    }

    public void nutXoaSPTrongGio() {
        if (this.dsSPGH.isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Chưa có sản phẩm nào trong giỏ hàng!");
            alert.showAndWait();
        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo xác nhận");
            alert.setHeaderText(null);
            alert.setContentText("Bạn có chắc chắn muốn xóa tất cả các sản phẩm trong giỏ hàng không?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                Integer maKH = DAO.DAOCustomer.createInstance().layMaKhachHang(Data.taiKhoan);
                DAO.DAOCart.xoaSPCuaKH(maKH);
                hienThiSPTrongGioHang();
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Đã xóa các sản phẩm trong giỏ hàng của bạn!");
                alert.showAndWait();
            }
        }
    }

    public void nutDangXuat() {
        try {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo!!");
            alert.setHeaderText(null);
            alert.setContentText("Bạn có chắc chắn muốn đăng xuẩt khỏi tài khoản không?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLLogin.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setTitle("Cafe Shop System");
                stage.setMinHeight(400);
                stage.setWidth(600);
                stage.setScene(scene);
                stage.show();

                us_taiKhoan.getScene().getWindow().hide();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void nutDoiMatKhau() {
        formTrangChu.setVisible(false);
        formTimKiem.setVisible(false);
        formSanPham.setVisible(false);
        formGioHang.setVisible(false);
        formDoiMatKhau.setVisible(true);
        formCapNhatTTCN.setVisible(false);
    }

    public void nutXacNhanDoiMK() {
        if (mk_matKhauCu.getText().isEmpty() || mk_matKhauMoi.getText().isEmpty() || mk_NhapLaiMKMoi.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng điền đầy đủ thông tin!");
            alert.showAndWait();
        } else if (DAO.DAOCustomer.createInstance().kiemTraMK(Data.taiKhoan, mk_matKhauCu.getText())) {
            if (mk_matKhauMoi.getText().length() < 8) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Mật khẩu mới phải chứa ít nhất 8 ký tự!");
                alert.showAndWait();
            } else if (mk_matKhauMoi.getText().equals(mk_NhapLaiMKMoi.getText())) {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Thông báo!!");
                alert.setHeaderText(null);
                alert.setContentText("Bạn có chắc chắn thay đổi mật khẩu không?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    new Thread(() -> {
                        try (Socket socket = new Socket("localhost", 9999); ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream()); ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
                            HoldData tmp = new HoldData("DoiMatKhau", Data.taiKhoan, mk_matKhauMoi.getText());
                            out.writeObject(tmp);
                            Platform.runLater(() -> {
                                DAO.DAOCustomer.createInstance().capNhatMK(Data.taiKhoan, mk_matKhauMoi.getText());
                                alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Thông báo");
                                alert.setHeaderText(null);
                                alert.setContentText("Đã thay đổi mật khẩu thành công!");
                                alert.showAndWait();

                                formTrangChu.setVisible(true);
                                formTimKiem.setVisible(false);
                                formSanPham.setVisible(false);
                                formGioHang.setVisible(false);
                                formDoiMatKhau.setVisible(false);
                                formCapNhatTTCN.setVisible(false);
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();
//                    DAO.DAOCustomer.createInstance().capNhatMK(Data.taiKhoan, mk_matKhauMoi.getText());
//                    alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Thông báo");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Đã thay đổi mật khẩu thành công!");
//                    alert.showAndWait();
//
//                    formTrangChu.setVisible(true);
//                    formTimKiem.setVisible(false);
//                    formSanPham.setVisible(false);
//                    formGioHang.setVisible(false);
//                    formDoiMatKhau.setVisible(false);
//                    formCapNhatTTCN.setVisible(false);
                }
            } else {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Mật khẩu nhập lại phải giống với mật khẩu mới!");
                alert.showAndWait();

            }
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Mật khẩu cũ nhập vào không chính xác!");
            alert.showAndWait();
        }
    }

    public void nutCapNhatTTCaNhan() {
        hienThiTTKH();
        formTrangChu.setVisible(false);
        formTimKiem.setVisible(false);
        formSanPham.setVisible(false);
        formGioHang.setVisible(false);
        formDoiMatKhau.setVisible(false);
        formCapNhatTTCN.setVisible(true);
    }

    public void hienThiTTKH() {
        Customer tmp = DAO.DAOCustomer.createInstance().layTTKH(Data.taiKhoan);
        ttc_tenKH.setText(tmp.getHoVaTen());
        ttc_daiChiKH.setText(tmp.getDiaChi());
        ttc_soDTKH.setText(tmp.getSoDienThoai());
        ttc_gioiTinhKH.setText(tmp.getGioiTinh());
        ttc_ngaySinhKH.setText(tmp.getNgaySinh());
    }

    public void nutXacNhanCNTTCN() {
        if (ttm_tenKH.getText().isEmpty() || ttm_diaChiKH.getText().isEmpty() || ttm_gioiTinhKH.getText().isEmpty()
                || ttm_soDTKH.getText().isEmpty() || ttm_ngaySinhKH.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng điền đầy đủ thông tin!");
            alert.showAndWait();
        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo!!");
            alert.setHeaderText(null);
            alert.setContentText("Bạn có chắc chắn thay đổi thông tin cá nhân không?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                Customer tmp = new Customer();

                tmp.setHoVaTen(ttm_tenKH.getText());
                tmp.setDiaChi(ttm_diaChiKH.getText());
                tmp.setGioiTinh(ttm_gioiTinhKH.getText());
                tmp.setNgaySinh(ttm_ngaySinhKH.getText());
                tmp.setSoDienThoai(ttm_soDTKH.getText());

                DAO.DAOCustomer.createInstance().CapNhatTTKH(Data.taiKhoan, tmp);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Cập nhật thông tin cá nhân thành công!");
                alert.showAndWait();

                formTrangChu.setVisible(true);
                formTimKiem.setVisible(false);
                formSanPham.setVisible(false);
                formGioHang.setVisible(false);
                formDoiMatKhau.setVisible(false);
                formCapNhatTTCN.setVisible(false);
            }
        }
    }

    public void hienThiThongBao() {
        this.dsTB = new ArrayList<>(DAO.DAONotification.layTT(DAO.DAOCustomer.createInstance().layMaKhachHang(Data.taiKhoan)));
        vungChuaTB.getChildren().clear();
        try {
            for (int i = 0; i < dsTB.size(); i++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/FXMLCardNotification.fxml"));
                AnchorPane pane = loader.load();
                CardNotificationController c = loader.getController();
                c.sDuLieu(dsTB.get(i));
                vungChuaTB.getChildren().add(pane);
                VBox.setMargin(pane, new Insets(10));
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void hienThiDuLieuTrangChu() {
        home_tongSP.setText(String.valueOf(DAO.DAOCustomer.createInstance().LayRaSOLuongSPDaMua(DAO.DAOCustomer.createInstance().layMaKhachHang(Data.taiKhoan))));

        home_tongTien.setText("$" + String.format("%.2f", (DAO.DAOCustomer.createInstance().LayRaTongTienDaChi(DAO.DAOCustomer.createInstance().layMaKhachHang(Data.taiKhoan)))));
    }

    public void hienThiSPDaMua() {
        this.dsSPDM = DAO.DAOProduct.createInstance().LayRaSPDaMuaCuaKH(DAO.DAOCustomer.createInstance().layMaKhachHang(Data.taiKhoan));
        vungChuaSPDaMua.getChildren().clear();
        for (Product x : dsSPDM) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/FXMLCardProduct.fxml"));
                HBox box = loader.load();
                CardProductController c = loader.getController();
                c.sSanPhamNoiBat(x);
                vungChuaSPDaMua.getChildren().add(box);
                VBox.setMargin(box, new Insets(10));
            } catch (IOException ex) {
                Logger.getLogger(HomeUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void dieuHuong(ActionEvent e) {
        if (e.getSource() == nutTrangChu) {
            formTrangChu.setVisible(true);
            formTimKiem.setVisible(false);
            formSanPham.setVisible(false);
            formGioHang.setVisible(false);
            formDoiMatKhau.setVisible(false);
            formCapNhatTTCN.setVisible(false);
            formThongBao.setVisible(false);
        } else if (e.getSource() == nutSanPham) {
            formTrangChu.setVisible(false);
            formTimKiem.setVisible(false);
            formSanPham.setVisible(true);
            formGioHang.setVisible(false);
            formDoiMatKhau.setVisible(false);
            formCapNhatTTCN.setVisible(false);
            formThongBao.setVisible(false);

            hienThiDSSanPhamNoiBat();
        } else if (e.getSource() == nutGioHang) {
            formTrangChu.setVisible(false);
            formTimKiem.setVisible(false);
            formSanPham.setVisible(false);
            formGioHang.setVisible(true);
            formDoiMatKhau.setVisible(false);
            formCapNhatTTCN.setVisible(false);
            formThongBao.setVisible(false);

            hienThiSPTrongGioHang();
        } else if (e.getSource() == nutThongBao) {
            formTrangChu.setVisible(false);
            formTimKiem.setVisible(false);
            formSanPham.setVisible(false);
            formGioHang.setVisible(false);
            formDoiMatKhau.setVisible(false);
            formCapNhatTTCN.setVisible(false);
            formThongBao.setVisible(true);

            hienThiThongBao();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hienThiDSSanPhamNoiBat();
        layDLSPtrongGioHang();
        hienThiSPTrongGioHang();
        hienThiThongBao();
        hienThiDuLieuTrangChu();
        hienThiSPDaMua();
        scrollPaneThongBao.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPaneThongBao.setVbarPolicy(ScrollBarPolicy.NEVER);
        scrollPaneSPDaMua.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPaneSPDaMua.setVbarPolicy(ScrollBarPolicy.NEVER);
    }
}
