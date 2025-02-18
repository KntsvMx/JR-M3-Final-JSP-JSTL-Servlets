package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.SessionUtil;


import java.io.IOException;

import static constants.SessionAttributes.USER_NAME;

@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int maxInactivity = 30 * 60; // 30 minutes
        String playerName = req.getParameter(USER_NAME);

        if (playerName == null || playerName.isEmpty()) {
            req.setAttribute("errorMessage", "Player name is required");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        HttpSession session = req.getSession(true);
        String userIp = req.getRemoteAddr();

        SessionUtil.setUserNameToSession(session, playerName);
        SessionUtil.setGameIdToSession(session, 0);
        SessionUtil.setUserIpToSession(session, userIp);
        SessionUtil.setMaxInactivityToSession(session, maxInactivity);

        req.removeAttribute("errorMessage");
        resp.sendRedirect(req.getContextPath() + "/start");
    }
}
