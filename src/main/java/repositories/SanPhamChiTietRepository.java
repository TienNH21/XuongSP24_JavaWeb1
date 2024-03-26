package repositories;

import entities.SanPham;
import entities.SanPhamChiTiet;
import utils.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SanPhamChiTietRepository {
    private Connection conn;

    public SanPhamChiTietRepository()
    {
        try {
            this.conn = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<SanPhamChiTiet> findAll()
    {
        ArrayList<SanPhamChiTiet> ds = new ArrayList<>();
        String sql = "SELECT * FROM SanPhamChiTiet";
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("ID");
                int idMS = rs.getInt("IdMauSac");
                int idKT = rs.getInt("IdKichThuoc");
                int idSP = rs.getInt("IdSanPham");
                String ma = rs.getString("MaSPCT");
                int soLuong = rs.getInt("SoLuong");
                double donGia = rs.getDouble("DonGia");
                int trangThai = rs.getInt("TrangThai");
                SanPhamChiTiet spct = new SanPhamChiTiet(id, idMS, idKT, idSP, ma, soLuong, donGia, trangThai);
                ds.add(spct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }

    public List<SanPhamChiTiet> findBySanPham(int spId) {
        ArrayList<SanPhamChiTiet> ds = new ArrayList<>();
        String sql = "SELECT * FROM SanPhamChiTiet WHERE IdSanPham = ?";
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, spId);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("ID");
                int idMS = rs.getInt("IdMauSac");
                int idKT = rs.getInt("IdKichThuoc");
                int idSP = rs.getInt("IdSanPham");
                String ma = rs.getString("MaSPCT");
                int soLuong = rs.getInt("SoLuong");
                double donGia = rs.getDouble("DonGia");
                int trangThai = rs.getInt("TrangThai");
                SanPhamChiTiet spct = new SanPhamChiTiet(id, idMS, idKT, idSP, ma, soLuong, donGia, trangThai);
                ds.add(spct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }
}
