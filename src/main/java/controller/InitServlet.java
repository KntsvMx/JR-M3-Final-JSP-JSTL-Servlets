package controller;

import jakarta.servlet.ServletException;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(false);

        if (session == null || (Player) session.getAttribute(PLAYER_ATTRIBUTE) == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().write("Session not created");
        } else {
            resp.setStatus(HttpServletResponse.SC_OK);
            req.getRequestDispatcher("/game").forward(req, resp);
        }
    }
}
