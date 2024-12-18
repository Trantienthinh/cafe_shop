package controller;

import java.sql.*;
import database.JDBCutil;
import java.io.File;
import java.net.URL;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import model.Data;
import model.Order;
import model.OrderDetail;
import model.Product;

public class HomeAdminController implements Initializable {

    @FXML
    private AnchorPane HoaDonForm;

    @FXML
    private AnchorPane QlyHTKForm;

    @FXML
    private TableView<OrderDetail> bangHoaDon;

    @FXML
    private GridPane OChuaSP;

    @FXML
    private TableView<Product> danhSachSP;

    @FXML
    private TableColumn<Product, Double> cotGiaSP;

    @FXML
    private TableColumn<Product, String> cotLoaiSP;

    @FXML
    private TableColumn<Product, String> cotMaSP;

    @FXML
    private TableColumn<Product, java.util.Date> cotNgayNhapSP;

    @FXML
    private TableColumn<Product, Integer> cotSoLuongSP;

    @FXML
    private TableColumn<Product, String> cotTenSP;

    @FXML
    private TableColumn<Product, String> cotTrangThai;

    @FXML
    private TextField mn_maKH;

    @FXML
    private AnchorPane giaoDienChinh;

    @FXML
    private ImageView hinhSP;

    @FXML
    private TextField htk_giaSP;

    @FXML
    private ComboBox<?> htk_loaiSP;

    @FXML
    private TextField htk_luongSP;

    @FXML
    private TextField htk_maSP;

    @FXML
    private TextField htk_tenSP;

    @FXML
    private ComboBox<?> htk_ttSP;

    @FXML
    private AnchorPane menuForm;

    @FXML
    private TableColumn<OrderDetail, Integer> mn_cotSoLuong;

    @FXML
    private TableColumn<OrderDetail, Integer> mn_cotGia;

    @FXML
    private TableColumn<OrderDetail, Integer> mn_cotMaSP;

    @FXML
    private TextField mn_soLuongSP;

    @FXML
    private Button nutCaiLai;

    @FXML
    private Button nutDangXuat;

    @FXML
    private Button nutGan;

    @FXML
    private Button nutHangTK;

    @FXML
    private Button nutXDSHoaDon;

    @FXML
    private Button nutMenu;

    @FXML
    private Button nutSua;

    @FXML
    private Button nutThanhToan;

    @FXML
    private Button nutThem;

    @FXML
    private Button nutTrangChu;

    @FXML
    private Button nutXoa;

    @FXML
    private Button nutXoaHoaDon;

    @FXML
    private Button nutXuatHoaDon;

    @FXML
    private Label tenNguoiDung;

    @FXML
    private Label tongTien;

    @FXML
    private AnchorPane trangChuForm;

    @FXML
    private ScrollPane thanhCuonSP;

    @FXML
    private TableView<Order> od_danhSachHoaDon;

    @FXML
    private TableColumn<Order, Integer> od_maHoaDon;

    @FXML
    private TableColumn<Order, Integer> od_maKhachHang;

    @FXML
    private TableColumn<Order, Integer> od_maNhanVien;

    @FXML
    private TableColumn<Order, String> od_ngayLap;

    @FXML
    private TableColumn<Order, Double> od_tongTien;

    @FXML
    private Label h_soLuongKH;

    @FXML
    private Label h_thuNhapTrongNgay;

    @FXML
    private Label h_tongThuNhap;

    @FXML
    private Label h_soLuongDonHang;

    @FXML
    private AreaChart<?, ?> h_bieuDoThuNhap;

    @FXML
    private AreaChart<?, ?> h_bieuDoLuongKH;

    private Alert alert;
    private String[] loaiCafe = {"Cà phê nguyên hạt", "Cà phê xay", "Cà phê hạt xanh"};
    private String[] ttCafe = {"Còn hàng", "Hết hàng"};
    private ObservableList<Product> dsSP;
    private Image img;
    private double tongTienPhaiTra;
    private double thuNhapTrongNgay = 0;
    private double tongThuNhap = 0;

    public void sLoaiCafe() {
        List<String> loai = new ArrayList<>();
        for (String x : loaiCafe) {
            loai.add(x);
        }

        ObservableList ds = FXCollections.observableArrayList(loai);
        htk_loaiSP.setItems(ds);
    }

    public void sttCafe() {
        List<String> tt = new ArrayList<>();
        for (String x : ttCafe) {
            tt.add(x);
        }

        ObservableList ds = FXCollections.observableArrayList(tt);
        htk_ttSP.setItems(ds);
    }

    public void sDsSP() {
        dsSP = FXCollections.observableArrayList(DAO.DAOProduct.createInstance().LayTT());

        cotMaSP.setCellValueFactory(new PropertyValueFactory<>("maSanPham"));
        cotTenSP.setCellValueFactory(new PropertyValueFactory<>("tenSanPham"));
        cotLoaiSP.setCellValueFactory(new PropertyValueFactory<>("loai"));
        cotSoLuongSP.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
        cotGiaSP.setCellValueFactory(new PropertyValueFactory<>("giaCa"));
        cotTrangThai.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
        cotNgayNhapSP.setCellValueFactory(new PropertyValueFactory<>("ngay"));

        danhSachSP.setItems(dsSP);
    }

    public void sDsHD() {
        ObservableList<OrderDetail> dsHD = FXCollections.observableArrayList(Data.dsCTSP);

        mn_cotMaSP.setCellValueFactory(new PropertyValueFactory<>("id"));
        mn_cotSoLuong.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
        mn_cotGia.setCellValueFactory(new PropertyValueFactory<>("gia"));

        bangHoaDon.setItems(dsHD);
    }

    public ObservableList<Product> layDuLieuSanPham() {
        dsSP = FXCollections.observableArrayList(DAO.DAOProduct.createInstance().LayTT());

        return dsSP;
    }

    public void hienThiOChuaSanPham() {
        dsSP.clear();
        dsSP.addAll(layDuLieuSanPham());

        int dong = 0;
        int cot = 0;

//        OChuaSP.getChildren().clear();
        OChuaSP.getRowConstraints().clear();
        OChuaSP.getColumnConstraints().clear();

        for (int i = 0; i < dsSP.size(); i++) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/FXMLProductContainer.fxml"));
                AnchorPane pane = loader.load();
                ProductContainerController cardController = loader.getController();
                cardController.setDuLieu(dsSP.get(i));

                if (cot == 3) {
                    cot = 0;
                    dong += 1;
                }

                OChuaSP.add(pane, cot++, dong);

                GridPane.setMargin(pane, new Insets(10));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void hienThiDanhSachHD() {
        ObservableList<Order> dsHD = FXCollections.observableList(DAO.DAOOrder.createInstance().LayTT());

        od_maHoaDon.setCellValueFactory(new PropertyValueFactory<>("idHoaDon"));
        od_maKhachHang.setCellValueFactory(new PropertyValueFactory<>("idKhachHang"));
        od_maNhanVien.setCellValueFactory(new PropertyValueFactory<>("idNhanVien"));
        od_tongTien.setCellValueFactory(new PropertyValueFactory<>("tongTien"));
        od_ngayLap.setCellValueFactory(new PropertyValueFactory<>("ngayLap"));

        od_danhSachHoaDon.setItems(dsHD);
    }

    public void hienThiTenNguoiDung() {
        String taiKhoan = Data.taiKhoan;
        taiKhoan = taiKhoan.substring(0, 1).toUpperCase() + taiKhoan.substring(1);
        tenNguoiDung.setText(taiKhoan);
    }

    public void layTongTienHoaDon() {
        tongTienPhaiTra = 0;
        for (OrderDetail o : Data.dsCTSP) {
            tongTienPhaiTra += (o.getSoLuong() * o.getGia());
        }
    }

    public void hienThiTongTien() {
        layTongTienHoaDon();
        tongTien.setText("$" + String.valueOf(tongTienPhaiTra));
    }

    public void hienThiTTTrangChu() {

        h_soLuongKH.setText(String.valueOf(DAO.DAOCustomer.createInstance().layRaSoLuongKH()));
        h_soLuongDonHang.setText(String.valueOf(DAO.DAOOrder.createInstance().layRaSoLuongDH()));
        java.util.Date d = new java.util.Date();
        java.sql.Date dsql = new java.sql.Date(d.getTime());
        List<Order> dsHDTrongNgay = DAO.DAOOrder.createInstance().LayDonHangTrongNgay(dsql);
        for (Order o : dsHDTrongNgay) {
            thuNhapTrongNgay += o.getTongTien();
        }
        List<Order> dsHD = DAO.DAOOrder.createInstance().LayTT();
        for (Order o : dsHD) {
            tongThuNhap += o.getTongTien();
        }
        h_thuNhapTrongNgay.setText("$" + String.valueOf(thuNhapTrongNgay));
        h_tongThuNhap.setText("$" + String.valueOf(tongThuNhap));

    }

    public void hienThiBieuDoThuNhap() {
        h_bieuDoThuNhap.getData().clear();

        String sql = "SELECT ngayLap, SUM(tongTien) FROM HoaDon GROUP BY ngayLap ORDER BY TIMESTAMP(ngayLap)";
        Connection cnt = JDBCutil.getConnection();
        XYChart.Series bieuDo = new XYChart.Series();
        try {
            PreparedStatement pr = cnt.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                bieuDo.getData().add(new XYChart.Data<>(rs.getString(1), rs.getFloat(2)));
            }

            h_bieuDoThuNhap.getData().add(bieuDo);
            JDBCutil.closeConection(cnt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hienThiBieuDoKhachHang() {
        h_bieuDoLuongKH.getData().clear();

        // truy vấn cần sửa
        String sql = "SELECT ngayLap, COUNT(idHoaDon) FROM HoaDon GROUP BY ngayLap ORDER BY TIMESTAMP(ngayLap)";
        Connection cnt = JDBCutil.getConnection();
        XYChart.Series bieuDo = new XYChart.Series();
        try {
            PreparedStatement pr = cnt.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                bieuDo.getData().add(new XYChart.Data<>(rs.getString(1), rs.getInt(2)));
            }

            h_bieuDoLuongKH.getData().add(bieuDo);
            JDBCutil.closeConection(cnt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chonDuLieuHangHoa() {

        Product duLieuSanPham = danhSachSP.getSelectionModel().getSelectedItem();
        int num = danhSachSP.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        htk_maSP.setText(duLieuSanPham.getMaSanPham());
        htk_tenSP.setText(duLieuSanPham.getTenSanPham());
        htk_luongSP.setText(String.valueOf(duLieuSanPham.getSoLuong()));
        htk_giaSP.setText(String.valueOf(duLieuSanPham.getGiaCa()));

        Data.duongDan = duLieuSanPham.getHinhAnh();

        String chuoiTmp = duLieuSanPham.getHinhAnh().substring(0, 6);
        String duongDan;
        if(chuoiTmp.equals("/image")){
            duongDan = duLieuSanPham.getHinhAnh();
        }else{
            duongDan = "File:" + duLieuSanPham.getHinhAnh(); 
        } 
        Data.ngayNhap = String.valueOf(duLieuSanPham.getNgay());
        Data.id = duLieuSanPham.getSoThuTu();

        img = new Image(duongDan, 146, 150, false, true);
        hinhSP.setImage(img);
    }

    public void dangXuat() {

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

                nutDangXuat.getScene().getWindow().hide();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void nutThemSP() {
        if (htk_tenSP.getText().isEmpty() || htk_maSP.getText().isEmpty() || htk_loaiSP.getSelectionModel().getSelectedItem() == null
                || htk_giaSP.getText().isEmpty() || htk_luongSP.getText().isEmpty() || htk_ttSP.getSelectionModel().getSelectedItem() == null) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Thông báo lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng điền đầy đủ thông tin");
            alert.showAndWait();
        } else {
            if (DAO.DAOProduct.createInstance().kiemTraMa(htk_maSP.getText())) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Thông báo lỗi");
                alert.setHeaderText(null);
                alert.setContentText(htk_maSP.getText() + " đã tồn tại");
                alert.showAndWait();
            } else {
                String duongDan = Data.duongDan;
                duongDan = duongDan.replace("\\", "\\\\");
                java.util.Date date = new java.util.Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                Product tmp = new Product(htk_maSP.getText(), htk_tenSP.getText(), (String) htk_loaiSP.getSelectionModel().getSelectedItem(),
                        Integer.valueOf(htk_luongSP.getText()), Double.valueOf(htk_giaSP.getText()), (String) htk_ttSP.getSelectionModel().getSelectedItem(), duongDan, sqlDate);

                DAO.DAOProduct.createInstance().Them(tmp);
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Thêm sản phẩm thành công!");
                alert.showAndWait();

                sDsSP();
                nutCaiLai();
            }
        }
    }

    public void nutSuaSP() {
        if (htk_tenSP.getText().isEmpty() || htk_maSP.getText().isEmpty() || htk_loaiSP.getSelectionModel().getSelectedItem() == null
                || htk_giaSP.getText().isEmpty() || htk_luongSP.getText().isEmpty() || htk_ttSP.getSelectionModel().getSelectedItem() == null
                || Data.duongDan == null) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Thông báo lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng điền đầy đủ thông tin");
            alert.showAndWait();
        } else {

            String duongDan = Data.duongDan;
            duongDan = duongDan.replace("\\", "\\\\");
            java.util.Date date = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            Product tmp = new Product(htk_maSP.getText(), htk_tenSP.getText(), (String) htk_loaiSP.getSelectionModel().getSelectedItem(),
                    Integer.valueOf(htk_luongSP.getText()), Double.valueOf(htk_giaSP.getText()), (String) htk_ttSP.getSelectionModel().getSelectedItem(), duongDan, sqlDate);

            try {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Thông báo xác nhận");
                alert.setHeaderText(null);
                alert.setContentText("Bạn có chắc chắn muốn cập nhật Sản phẩm có mã: " + htk_maSP.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    DAO.DAOProduct.createInstance().Sua(tmp, Data.id);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText(null);
                    alert.setContentText("Cập nhật thành công!");
                    alert.showAndWait();

                    // ĐỂ CẬP NHẬT BẢNG HIỂN THỊ
                    sDsSP();
                    // ĐỂ XÓA TRẮNG CÁC TRƯỜNG
                    nutCaiLai();
                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Thông báo lỗi");
                    alert.setHeaderText(null);
                    alert.setContentText("Đã hủy.");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void nutXoaSP() {
        if (Data.id == 0) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Thông báo lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng chọn sản phẩm muốn xóa");
            alert.showAndWait();
        } else {
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Thông báo xác nhận");
            alert.setHeaderText(null);
            alert.setContentText("Bạn có chắc chắn muốn xóa sản phẩm có mã: " + Data.id + "?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                DAO.DAOProduct.createInstance().Xoa(Data.id);
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Xóa thành công!");
                alert.showAndWait();

                // CẬP NHẬT LẠI BẢNG HIỂN THỊ
                sDsSP();
                // XÓA TRẮNG CÁC TRƯỜNG
                nutCaiLai();

            } else {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Thông báo lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Đã hủy.");
                alert.showAndWait();
            }
        }
    }

    public void nutGanHinh() {
        FileChooser moFile = new FileChooser();
        moFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Mở file ảnh", "*png", "*jpg"));

        File file = moFile.showOpenDialog(giaoDienChinh.getScene().getWindow());

        if (file != null) {
            Data.duongDan = file.getAbsolutePath();
            img = new Image(file.toURI().toString(), 120, 127, false, true);

            hinhSP.setImage(img);
        }
    }

    public void nutCaiLai() {
        htk_maSP.clear();
        htk_tenSP.clear();
        htk_loaiSP.getSelectionModel().clearSelection();
        htk_luongSP.clear();
        htk_giaSP.clear();
        htk_ttSP.getSelectionModel().clearSelection();
        Data.duongDan = null;
        Data.id = 0;
        hinhSP.setImage(null);
    }

    public void nutThanhToan() {
        if (Data.dsCTSP.isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Thông báo lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng chọn sản phầm cần mua rồi thực hiện chức năng thanh toán!");
            alert.showAndWait();
        } else {
            if (mn_maKH.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Thông báo lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Vui lòng Nhập mã khách hàng để thanh toán hóa đơn!");
                alert.showAndWait();
            } else {
                layTongTienHoaDon();
                java.util.Date d = new java.util.Date();
                java.sql.Date dsql = new java.sql.Date(d.getTime());
                Order o = new Order(Integer.valueOf(mn_maKH.getText()), DAO.DAOEmployee.createInstance().layMaNhanVien(Data.taiKhoan), tongTienPhaiTra, dsql);
                DAO.DAOOrder.createInstance().Them(o);
                Integer idHD = DAO.DAOOrder.createInstance().layMaHoaDonMoiLuu();
                for (OrderDetail od : Data.dsCTSP) {
                    od.setIdHoaDon(idHD);
                    DAO.DAOOrderDetail.createInstance().Them(od);
                }
                Data.dsCTSP.clear();
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Đơn hàng đã được thanh toán.");
                alert.showAndWait();
                tongTien.setText("$0.0");
                mn_maKH.setText("");
                sDsHD();
            }
        }
    }

    public void nutXoaTTDonHang() {
        for (OrderDetail od : Data.dsCTSP) {
            DAO.DAOProduct.createInstance().capNhatSoLuongThem(od.getId(), od.getSoLuong());
        }
        Data.dsCTSP.clear();
        alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("Đã xóa thông tin về đơn hàng vừa nhập");
        alert.showAndWait();
        tongTien.setText("$0.0");
        mn_maKH.setText("");
        sDsHD();
    }

    public void chuyenManHinh(ActionEvent e) {

        if (e.getSource() == nutTrangChu) {
            trangChuForm.setVisible(true);
            QlyHTKForm.setVisible(false);
            menuForm.setVisible(false);
            HoaDonForm.setVisible(false);

            hienThiTTTrangChu();
            hienThiBieuDoThuNhap();
            hienThiBieuDoKhachHang();
        } else if (e.getSource() == nutHangTK) {
            trangChuForm.setVisible(false);
            QlyHTKForm.setVisible(true);
            menuForm.setVisible(false);
            HoaDonForm.setVisible(false);

            sLoaiCafe();
            sttCafe();
            sDsSP();
        } else if (e.getSource() == nutMenu) {
            trangChuForm.setVisible(false);
            QlyHTKForm.setVisible(false);
            menuForm.setVisible(true);
            HoaDonForm.setVisible(false);

            hienThiOChuaSanPham();
            hienThiTongTien();
            sDsHD();
        } else if (e.getSource() == nutXDSHoaDon) {
            trangChuForm.setVisible(false);
            QlyHTKForm.setVisible(false);
            menuForm.setVisible(false);
            HoaDonForm.setVisible(true);

            hienThiDanhSachHD();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hienThiTenNguoiDung();
        sLoaiCafe();
        sttCafe();
        sDsSP();

        hienThiOChuaSanPham();
        hienThiTongTien();
        sDsHD();
        hienThiDanhSachHD();
        hienThiTTTrangChu();
        hienThiBieuDoThuNhap();
        hienThiBieuDoKhachHang();
    }
}
