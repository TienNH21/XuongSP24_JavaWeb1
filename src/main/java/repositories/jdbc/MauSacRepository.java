package repositories.jdbc;

import entities.MauSac;
import utils.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class MauSacRepository {
    private Connection conn;

    public MauSacRepository()
    {
        try {
            this.conn = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void insert(MauSac ms)
    {
        String sql = "INSERT INTO MauSac(Ma, Ten, TrangThai) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, ms.getMa());
            ps.setString(2, ms.getTen());
            ps.setInt(3, ms.getTrangThai());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(MauSac ms)
    {
        String sql = "UPDATE MauSac SET Ma = ?, Ten = ?, TrangThai = ? WHERE id = ?";
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, ms.getMa());
            ps.setString(2, ms.getTen());
            ps.setInt(3, ms.getTrangThai());
            ps.setInt(4, ms.getId());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
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
            rs.next();
            String ten = rs.getString("ten");
            String ma = rs.getString("ma");
            int trangThai = rs.getInt("trangThai");
            return new MauSac(id, ma, ten, trangThai);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<MauSac> findAll(String ma, String ten, Integer trangThai)
    {
        ArrayList<MauSac> ds = new ArrayList<>();
        /**
         * 1. Nếu ko có tham số tìm kiếm -> SELECT * FROM MauSac
         * 2. Nếu có đủ 3 tham số tìm kiếm ->
         *      SELECT * FROM MauSac WHERE Ma LIKE %?% AND Ten LIKE %?% AND TrangThai = ?
         * 3. Nếu chỉ có Ma -> SELECT * FROM MauSac WHERE Ma LIKE '%?%'
         * 4. Nếu chỉ có Ten -> SELECT * FROM MauSac WHERE Ten LIKE '%?%'
         * 5. Nếu chỉ có TrangThai -> SELECT * FROM MauSac WHERE TrangThai = ?
         * 6. Nếu có Ma và TrangThai -> SELECT * FROM MauSac WHERE Ma LIKE '%?%' AND TrangThai = ?
         */
        // WHERE Ma LIKE %?% AND Ten LIKE %?% AND TrangThai = ?
        String sql = "SELECT * FROM MauSac";

        if (ma.length() != 0 || ten.length() != 0 || trangThai != null) {
            sql += " WHERE ";
        }

        if (ma.length() != 0) {
            sql += " Ma LIKE ? ";
        }

        if (ten.length() != 0) {
            sql += ma.length() != 0 ? " AND " : "";
            sql += " Ten LIKE ? ";
        }

        if (trangThai != null) {
            sql += (ma.length() != 0 || ten.length() != 0) ? " AND " : "";
            sql += " TrangThai = ? ";
        }

        System.out.println(sql);
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            int i = 0;
            if (ma.length() != 0) {
                ps.setString(++i, "%" + ma + "%");
            }

            if (ten.length() != 0) {
                ps.setString(++i, "%" + ten + "%");
            }

            if (trangThai != null) {
                ps.setInt(++i, trangThai);
            }

            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("id");
                String ten1 = rs.getString("ten");
                String ma1 = rs.getString("ma");
                int trangThai1 = rs.getInt("trangThai");
                MauSac ms = new MauSac(id, ma1, ten1, trangThai1);
                ds.add(ms);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }

    public List<MauSac> paging(Optional<Integer> optionPage, Optional<Integer> optionLimit)
    {
        int limit = optionLimit.isPresent() ? optionLimit.get() : 10;
        int page = optionPage.isPresent() ? optionPage.get() : 1;

        ArrayList<MauSac> result = new ArrayList<>();
        String sql = "SELECT * FROM MauSac ORDER BY ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            int offset = (page - 1) * limit;
            ps.setInt(1, offset);
            ps.setInt(2, limit);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("id");
                String ten = rs.getString("ten");
                String ma = rs.getString("ma");
                int trangThai = rs.getInt("trangThai");
                MauSac ms = new MauSac(id, ma, ten, trangThai);
                result.add(ms);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public int count()
    {
        String sql = "SELECT COUNT(ID) AS Total FROM MauSac";
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            rs.next();
            int total = rs.getInt("Total");
            return total;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }


    public List<MauSac> findAll(int page, int limit, String ma, String ten, Integer trangThai)
    {
        ArrayList<MauSac> ds = new ArrayList<>();
        String sql = "SELECT * FROM MauSac";
        if (ma.length() != 0 || ten.length() != 0 || trangThai != null) {
            sql += " WHERE ";
        }

        if (ma.length() != 0) {
            sql += " Ma LIKE ? ";
        }

        if (ten.length() != 0) {
            sql += ma.length() != 0 ? " AND " : "";
            sql += " Ten LIKE ? ";
        }

        if (trangThai != null) {
            sql += (ma.length() != 0 || ten.length() != 0) ? " AND " : "";
            sql += " TrangThai = ? ";
        }

        sql += " ORDER BY ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            int paramIndex = 1;
            if (ma.length() != 0) {
                ps.setString(paramIndex++, "%" + ma + "%");
            }

            if (ten.length() != 0) {
                ps.setString(paramIndex++, "%" + ten + "%");
            }

            if (trangThai != null) {
                ps.setInt(paramIndex++, trangThai);
            }

            int offset = (page - 1) * limit;
            ps.setInt(paramIndex++, offset);
            ps.setInt(paramIndex++, limit);

            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("id");
                String ten1 = rs.getString("ten");
                String ma1 = rs.getString("ma");
                int trangThai1 = rs.getInt("trangThai");
                MauSac ms = new MauSac(id, ma1, ten1, trangThai1);
                ds.add(ms);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }

    public HashMap<Integer, String> findTenByIds(List<Integer> listId)
    {
        HashMap<Integer, String> ds = new HashMap<>();
        String sql = "SELECT ID, Ten FROM MauSac WHERE ID IN (";
        for (int i = 0; i <listId.size(); i++) {
            sql += "?";
            sql += i == listId.size() - 1 ? "" : ",";
        }

        sql += ")";
        System.out.println(sql);
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            for (int i = 0; i <listId.size(); i++) {
                ps.setInt(i+1, listId.get(i));
            }

            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String ten = rs.getString("Ten");
                ds.put(id, ten);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }

}
