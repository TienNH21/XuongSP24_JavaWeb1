package repositories;

import entities.MauSac;
import entities.SanPham;
import utils.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SanPhamRepository {
    private Connection conn;

    public SanPhamRepository()
    {
        try {
            this.conn = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<SanPham> findAll()
    {
        ArrayList<SanPham> ds = new ArrayList<>();
        String sql = "SELECT * FROM SanPham";
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("id");
                String ten = rs.getString("ten");
                String ma = rs.getString("ma");
                int trangThai = rs.getInt("trangThai");
                SanPham sp = new SanPham(id, ma, ten, trangThai);
                ds.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }
}
