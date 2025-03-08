package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static constants.SessionAttributes.PLAYER_ATTRIBUTE;

@WebServlet(name = "InitServlet", urlPatterns = "/start")
public class InitServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(InitServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("Get request to /start");
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute(PLAYER_ATTRIBUTE) == null) {
            logger.info("Session doesn't exist or player attribute is null, redirecting to /register");
            resp.sendRedirect(req.getContextPath() + "/register");
        } else {
            logger.info("Session exists and player attribute is not null, redirecting to /game");
            resp.sendRedirect(req.getContextPath() + "/game");
        }
    }
}
