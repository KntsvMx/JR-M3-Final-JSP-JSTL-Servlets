package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.SessionUtil;

import java.io.IOException;

import static constants.GameConstants.DEFAULT_FIRST_QUESTION;
import static constants.GameConstants.DEFAULT_SCORE;
import static constants.SessionAttributes.MAX_INACTIVITY_PERIOD;
import static constants.SessionAttributes.USER_NAME;

@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(RegisterServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Get doGet request and redirect to /register.jsp");
        resp.sendRedirect(req.getContextPath() + "/register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Get doPost request and perform registration");
        String playerName = req.getParameter(USER_NAME);

        if (playerName == null || playerName.isEmpty()) {
            logger.warn("Player name is required to register, redirecting to /register.jsp");
            req.setAttribute("errorMessage", "Player name is required");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        HttpSession session = req.getSession(true);
        logger.info("Create new session and perform registration for player: {}", playerName);
        String userIp = req.getRemoteAddr();
        Player player = new Player(playerName, DEFAULT_SCORE, userIp, DEFAULT_FIRST_QUESTION);
        logger.info("Player {}, {} created successfully", playerName, userIp);

        SessionUtil.setPlayerToSession(session, player);
        SessionUtil.setMaxInactivityToSession(session, MAX_INACTIVITY_PERIOD);
        logger.info("Session inactivity timeout set to {} seconds", MAX_INACTIVITY_PERIOD);

        req.removeAttribute("errorMessage");
        resp.setStatus(HttpServletResponse.SC_OK);
        logger.info("Redirect to /start");
        resp.sendRedirect(req.getContextPath() + "/start");
    }
}
