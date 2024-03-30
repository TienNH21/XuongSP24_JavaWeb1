package validator.mau_sac;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter({
    "/mau-sac/store"
})
public class StoreRequest implements Filter {
    @Override
    public void doFilter(
        ServletRequest servletRequest,
        ServletResponse servletResponse,
        FilterChain filterChain
    ) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();

        String ma = req.getParameter("ma");
        String ten = req.getParameter("ten");
        String tts = req.getParameter("trangThai");
        String error = "";

        if (ma == null|| ten == null || tts == null ||
            ma.trim().length() == 0 || ten.trim().length() == 0 || tts.trim().length() == 0
        ) {
            session.setAttribute("error", "Không được để trống");
            res.sendRedirect("/mau-sac/create");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
