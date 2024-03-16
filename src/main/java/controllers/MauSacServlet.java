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
    ) {
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
        List<MauSac> ds = this.msRepo.getList();
        request.setAttribute("data", ds);
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
            response.sendRedirect("/XuongSP24_JavaWeb1_war_exploded/mau-sac/index");
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

        response.sendRedirect("/XuongSP24_JavaWeb1_war_exploded/mau-sac/index");
    }

    public void store(HttpServletRequest request, HttpServletResponse response)
    {

    }

    public void update(HttpServletRequest request, HttpServletResponse response)
    {

    }

}
