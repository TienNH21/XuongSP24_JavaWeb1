package repositories.jpa;

import entities.SanPhamChiTiet;
import entities.custom_entity.SanPhamChiTietCustom;
import jakarta.persistence.Query;
import org.hibernate.Session;
import requests.spct.IndexRequest;
import utils.HibernateUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SanPhamChiTietRepository {
    private Session hSession;

    public SanPhamChiTietRepository()
    {
        this.hSession = HibernateUtil.getFACTORY().openSession();
    }

    public List<SanPhamChiTietCustom> findAllWithPropName(int spId) {
        ArrayList<SanPhamChiTietCustom> ds = new ArrayList<>();
        String jpql = "SELECT new SanPhamChiTietCustom( " +
                "spct.id, ms.ten, kt.ten, spct.maSPCT, " +
                "spct.soLuong, spct.donGia, spct.trangThai) " +
                "FROM SanPhamChiTiet spct " +
                "LEFT OUTER JOIN MauSac ms ON spct.idMauSac = ms.id " +
                "LEFT OUTER JOIN KichThuoc kt ON spct.idKichThuoc = kt.id " +
                "WHERE spct.idSanPham = ?1";

        Query q = this.hSession.createQuery(jpql, SanPhamChiTiet.class);
        q.setParameter(1, spId);
        return q.getResultList();
    }

    public List<SanPhamChiTietCustom> findAllWithPropName(IndexRequest params) {
        ArrayList<SanPhamChiTietCustom> ds = new ArrayList<>();
        String jpql = "SELECT new SanPhamChiTietCustom( " +
                "spct.id, ms.ten, kt.ten, spct.maSPCT, " +
                "spct.soLuong, spct.donGia, spct.trangThai) " +
                "FROM SanPhamChiTiet spct " +
                "LEFT OUTER JOIN MauSac ms ON spct.idMauSac = ms.id " +
                "LEFT OUTER JOIN KichThuoc kt ON spct.idKichThuoc = kt.id " +
                "WHERE spct.idSanPham = :idSanPham ";

        // SELECT entity FROM SanPhamChiTiet entity

        if (params.getKeyword() != null) {
            jpql += " AND (spct.maSPCT LIKE :keyword OR ms.ten LIKE :keyword OR kt.ten LIKE :keyword) ";
        }

        if (params.getTrangThai() != null) {
            jpql += " AND spct.trangThai = :trangThai ";
        }

        System.out.println(jpql);
        Query q = this.hSession.createQuery(jpql, SanPhamChiTiet.class);
        q.setParameter("idSanPham", params.getIdSanPham());

        if (params.getKeyword() != null) {
            q.setParameter("keyword", "%" + params.getKeyword() + "%");
        }

        if (params.getTrangThai() != null) {
            q.setParameter("trangThai", params.getTrangThai());
        }

        int start = (params.getPage() - 1) * params.getLimit() + 1;
        q.setFirstResult(start);
        q.setMaxResults(params.getLimit());

        return q.getResultList();
    }
}
