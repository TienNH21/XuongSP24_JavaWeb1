package controllers;

import entities.MauSac;
import entities.SanPham;
import entities.SanPhamChiTiet;
import entities.custom_entity.SanPhamChiTietCustom;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.MauSacRepository;
import repositories.SanPhamChiTietRepository;
import repositories.SanPhamRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet({
    "/sp-chi-tiet/index",
    "/sp-chi-tiet/create",
    "/sp-chi-tiet/store",
    "/sp-chi-tiet/edit",
    "/sp-chi-tiet/update",
    "/sp-chi-tiet/delete",
})
public class SanPhamChiTietServlet extends HttpServlet {
    private SanPhamChiTietRepository spctRepo;
    private SanPhamRepository spRepo;
    private MauSacRepository msRepo;

    public SanPhamChiTietServlet()
    {
        this.spctRepo = new SanPhamChiTietRepository();
        this.spRepo = new SanPhamRepository();
        this.msRepo = new MauSacRepository();
    }

    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("create")) {
//            this.create(request, response);
        } else if (uri.contains("edit")) {
//            this.edit(request, response);
        } else if (uri.contains("delete")) {
//            this.delete(request, response);
        } else {
            this.index(request, response);
        }
    }

    public void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        String uri = request.getRequestURI();
        if (uri.contains("store")) {
//            this.store(request, response);
        } else if (uri.contains("update")) {
//            this.update(request, response);
        } else {
            //
        }
    }

    public void index(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        if (request.getParameter("san_pham_id") == null) {
            response.sendRedirect("/san-pham/index");
        } else {
            int spId = Integer.parseInt(request.getParameter("san_pham_id"));
            SanPham sp = this.spRepo.findById(spId);
            List<SanPhamChiTiet> ds = this.spctRepo.findBySanPham(spId);
            List<Integer> listMSId = new ArrayList<>();
            List<Integer> listKTId = new ArrayList<>();
            for (SanPhamChiTiet spct : ds) {
                listMSId.add( spct.getIdMauSac() );
                listKTId.add( spct.getIdKichThuoc() );
            }

            HashMap<Integer, String> listMS = this.msRepo.findTenByIds(listMSId);

            request.setAttribute("data", ds);
            request.setAttribute("sanPham", sp);
            request.setAttribute("listMS", listMS);
            request.getRequestDispatcher("/views/san_pham_chi_tiet/index.jsp")
                    .forward(request, response);
        }
    }
}
