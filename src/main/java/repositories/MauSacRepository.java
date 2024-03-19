package repositories;

import entities.MauSac;
import utils.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MauSacRepository {
    private List<MauSac> list;
    private Connection conn;

    public MauSacRepository()
    {
        try {
            this.conn = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.list = new ArrayList<>();
        this.list.add(new MauSac(1, "ede", "Yellow", 1));
        this.list.add(new MauSac(2, "000", "Black", 1));
    }

    public List<MauSac> findAll()
    {
        ArrayList<MauSac> ds = new ArrayList<>();
        String sql = "SELECT * FROM MauSac";
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("id");
                String ten = rs.getString("ten");
                String ma = rs.getString("ma");
                int trangThai = rs.getInt("trangThai");
                MauSac ms = new MauSac(id, ma, ten, trangThai);
                ds.add(ms);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }

    public List<MauSac> getList() {
        return list;
    }

    public void setList(List<MauSac> list) {
        this.list = list;
    }

    public void insert(MauSac ms)
    {
        this.list.add(ms);
    }

    public void update(MauSac newMauSac)
    {
        // UPDATE MauSac SET Ma = ?, Ten = ?, TrangThai = ? WHERE ID = ?
        for (int i = 0; i < this.list.size(); i++) {
            MauSac oldMauSac = this.list.get(i);
            if (oldMauSac.getId() == newMauSac.getId()) {
                this.list.set(i, newMauSac);
                return ;
            }
        }
    }

    public void delete(MauSac ms)
    {
        String sql = "DELETE FROM MauSac WHERE id = ?";
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, ms.getId());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MauSac findById(int id)
    {
        String sql = "SELECT * FROM MauSac WHERE id = ?";
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            String ten = rs.getString("ten");
            String ma = rs.getString("ma");
            int trangThai = rs.getInt("trangThai");
            return new MauSac(id, ma, ten, trangThai);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
