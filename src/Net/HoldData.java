package Net;

import java.io.Serializable;


public class HoldData  implements Serializable{
    private static final long serialVersionUID = 1L;
    private String yeuCau;
    private String taiKhoan;
    private String matKhau;
    private String tuKhoa;

    public HoldData(String yeuCau, String taiKhoan, String matKhau) {
        this.yeuCau = yeuCau;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
    }

    public HoldData(String yeuCau, String tuKhoa) {
        this.yeuCau = yeuCau;
        this.tuKhoa = tuKhoa;
    }

    
    public String getYeuCau() {
        return yeuCau;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public String getTuKhoa() {
        return tuKhoa;
    }

    
}
