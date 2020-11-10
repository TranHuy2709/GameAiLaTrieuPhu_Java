package com.example.appailatrieuphu;

public class TienThuong {
    private String cauHoi;
    private String tienThuong;
    private int dauMoc;

    public TienThuong(String cauHoi, String tienThuong, int dauMoc) {
        this.cauHoi = cauHoi;
        this.tienThuong = tienThuong;
        this.dauMoc = dauMoc;
    }

    public String getCauHoi() {
        return cauHoi;
    }

    public void setCauHoi(String cauHoi) {
        this.cauHoi = cauHoi;
    }

    public String getTienThuong() {
        return tienThuong;
    }

    public void setTienThuong(String tienThuong) {
        this.tienThuong = tienThuong;
    }

    public int getDauMoc() {
        return dauMoc;
    }

    public void setDauMoc(int dauMoc) {
        this.dauMoc = dauMoc;
    }
}
