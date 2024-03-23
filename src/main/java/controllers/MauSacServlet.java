package controllers;

import entities.MauSac;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.MauSacRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet({
    "/mau-sac/index",
    "/mau-sac/create",
    "/mau-sac/store",
    "/mau-sac/edit",
    "/mau-sac/update",
    "/mau-sac/delete",
})
public class MauSacServlet extends HttpServlet {
    private MauSacRepository msRepo;

    public MauSacServlet()
    {
        this.msRepo = new MauSacRepository();
    }

    public void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("create")) {
            this.create(request, response);
        } else if (uri.contains("edit")) {
            this.edit(request, response);
        } else if (uri.contains("delete")) {
            this.delete(request, response);
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
            this.store(request, response);
        } else if (uri.contains("update")) {
            this.update(request, response);
        } else {
            //
        }
    }

    public void index(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
//        String ma = request.getParameter("ma") != null ? request.getParameter("ma").trim() : "";
//        String ten = request.getParameter("ten") != null ? request.getParameter("ten").trim() : "";
//        String tts = request.getParameter("trangThai") != null ? request.getParameter("trangThai").trim() : "";
//        Integer trangThai = tts.length() != 0 ? Integer.parseInt(tts) : null;
//        List<MauSac> ds = this.msRepo.findAll(ma, ten, trangThai);

//        if (ma.length() != 0) {
//            request.setAttribute("ma", ma);
//        }
//
//        if (ten.length() != 0) {
//            request.setAttribute("ten", ten);
//        }
//
//        if (trangThai != null) {
//            request.setAttribute("trangThai", trangThai);
//        }

        String pageS = request.getParameter("page");
        String limitS = request.getParameter("limit");
        int page = pageS == null || pageS .trim().length() == 0 ? 1 : Integer.parseInt(pageS);
        int limit = limitS == null || limitS .trim().length() == 0 ? 10 : Integer.parseInt(limitS);
        List<MauSac> ds = this.msRepo.paging(Optional.of(page), Optional.of(limit));
        int totalPage = this.msRepo.count() / limit + 1;
        request.setAttribute("data", ds);
        request.setAttribute("totalPage", totalPage);
        request.getRequestDispatcher("/views/mau_sac/index.jsp")
            .forward(request, response);
    }

    public void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/mau_sac/create.jsp")
                .forward(request, response);
    }

    public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idS = request.getParameter("id");
        int id = Integer.parseInt(idS);
        MauSac ms = this.msRepo.findById(id);
        if (ms != null)
        {
            request.setAttribute("data", ms);
            request.getRequestDispatcher("/views/mau_sac/edit.jsp")
                    .forward(request, response);
        } else {
            response.sendRedirect("/mau-sac/index");
        }
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idS = request.getParameter("id");
        int id = Integer.parseInt(idS);
        MauSac ms = this.msRepo.findById(id);
        if (ms != null)
        {
            this.msRepo.delete(ms);
        }

        response.sendRedirect("/mau-sac/index");
    }

    public void store(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        int trangThai = Integer.parseInt(request.getParameter("trangThai"));
        MauSac ms = new MauSac(0, ma, ten, trangThai);
        this.msRepo.insert(ms);
        response.sendRedirect("/mau-sac/index");
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        int trangThai = Integer.parseInt(request.getParameter("trangThai"));
        MauSac ms = new MauSac(id, ma, ten, trangThai);
        this.msRepo.update(ms);
        response.sendRedirect("/mau-sac/index");
    }

}
