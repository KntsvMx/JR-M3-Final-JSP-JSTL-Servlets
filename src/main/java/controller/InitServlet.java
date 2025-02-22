package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static constants.SessionAttributes.PLAYER_ATTRIBUTE;

@WebServlet(name = "InitServlet", urlPatterns = "/start")
public class InitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute(PLAYER_ATTRIBUTE) == null) {
            resp.sendRedirect(req.getContextPath() + "/register.jsp");
        } else {
            resp.sendRedirect(req.getContextPath() + "/game.jsp");
        }
    }
}
