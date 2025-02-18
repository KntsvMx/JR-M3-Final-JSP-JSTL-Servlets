package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Player;

import java.io.IOException;

import static constants.SessionAttributes.PLAYER_ATTRIBUTE;

@WebServlet(name = "InitServlet", urlPatterns = "/start")
public class InitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);

        if (session == null || (Player) session.getAttribute(PLAYER_ATTRIBUTE) == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().write("Session not created");
        } else {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.sendRedirect(req.getContextPath() + "/game");
        }
    }
}
