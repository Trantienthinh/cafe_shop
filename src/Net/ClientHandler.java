package Net;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import model.Product;

class ClientHandler implements Runnable {

    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream()); ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {
            HoldData yeuCauNhan = (HoldData) in.readObject();
            System.out.println("Nhan duoc yeu cau: " + yeuCauNhan.getYeuCau() + " tu " + clientSocket);

            if ("dangNhap".equals(yeuCauNhan.getYeuCau())) {
                out.writeObject(xuLyYCDangNhap(yeuCauNhan.getTaiKhoan(), yeuCauNhan.getMatKhau()));
                System.out.println("Da xu ly yeu cau dang nhap cua " + clientSocket);
            } else if("TimKiem".equalsIgnoreCase(yeuCauNhan.getYeuCau())){
                out.writeObject(xuLyYCTimKiem(yeuCauNhan.getTuKhoa()));
                System.out.println("Da xu ly yeu cau dang nhap cua " + clientSocket);
            } else if("DoiMatKhau".equalsIgnoreCase(yeuCauNhan.getYeuCau())){
                xuLyYcDoiMK(yeuCauNhan.getTaiKhoan(), yeuCauNhan.getMatKhau());
                System.out.println("Da xu ly yeu cau dang nhap cua " + clientSocket);
            }            
            else {
                out.writeObject("yeu cau khong hop le");
                System.out.println("Yeu cau cua " + clientSocket + "khong the xu ly");
            }
            out.close();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
                System.out.println("Da ngat ket noi toi client: " + clientSocket + '\n');
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String xuLyYCDangNhap(String taiKhoan, String matKhau) {
        if (DAO.DAOCustomer.createInstance().dangNhap(taiKhoan, matKhau)) {
            return "KhachHang";
        } else if (DAO.DAOEmployee.createInstance().dangNhap(taiKhoan, matKhau)) {
            return "NhanVien";
        }
        return "fail";
    }
    
    public List<Product> xuLyYCTimKiem(String tuKhoa){
        return new ArrayList<>(DAO.DAOProduct.createInstance().TimSanPham(tuKhoa));
    }
    
    public void xuLyYcDoiMK(String taiKhoan, String matKhau){
        DAO.DAOCustomer.createInstance().capNhatMK(taiKhoan, matKhau);
    }
}
