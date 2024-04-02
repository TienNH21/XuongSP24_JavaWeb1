package controllers;

import entities.SanPham;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.jdbc.SanPhamRepository;

import java.io.IOException;
import java.util.List;

@WebServlet({
        "/san-pham/index",
        "/san-pham/create",
        "/san-pham/store",
        "/san-pham/edit",
        "/san-pham/update",
        "/san-pham/delete",
})
public class SanPhamServlet extends HttpServlet {
    private SanPhamRepository spRepo;

    public SanPhamServlet()
    {
        this.spRepo = new SanPhamRepository();
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
        List<SanPham> ds = this.spRepo.findAll();
        request.setAttribute("data", ds);
        request.getRequestDispatcher("/views/san_pham/index.jsp")
            .forward(request, response);
    }
}
