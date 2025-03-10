package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Player;
import util.SessionUtil;

import java.io.IOException;

import static constants.GameConstants.DEFAULT_FIRST_QUESTION;
import static constants.GameConstants.DEFAULT_SCORE;
import static constants.SessionAttributes.*;

@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String playerName = req.getParameter(USER_NAME);

        if (playerName == null || playerName.isEmpty()) {
            req.setAttribute("errorMessage", "Player name is required");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        HttpSession session = req.getSession(true);
        String userIp = req.getRemoteAddr();
        Player player = new Player(playerName, DEFAULT_SCORE, userIp, DEFAULT_FIRST_QUESTION);

        SessionUtil.setPlayerToSession(session, player);
        SessionUtil.setMaxInactivityToSession(session, MAX_INACTIVITY_PERIOD);

        req.removeAttribute("errorMessage");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.sendRedirect(req.getContextPath() + "/start");
    }
}
