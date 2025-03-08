package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Game;
import model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.SessionUtil;

import java.io.IOException;

@WebServlet(name = "FinishQuestServlet", urlPatterns = "/finish")
public class FinishQuestServlet extends HttpServlet {

    private final static Logger logger = LoggerFactory.getLogger(FinishQuestServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Get request to /finish");
        HttpSession session = req.getSession(false);
        if (session == null) {
            logger.error("Session not found");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Session not found");
            return;
        }

        Game game = (Game) SessionUtil.getGameFromSession(session);
        if (game == null) {
            logger.error("Game not found in session");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Game not found in session");
            return;
        }

        Player player = (Player) game.getPlayer();
        if (player == null) {
            logger.error("Player not found in session");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Player not found in session");
            return;
        }


        logger.info("Finishing quest for player - {}", player);
        SessionUtil.setPlayerToSession(session, player);
        logger.info("Redirecting to /statistic.jsp");
        resp.sendRedirect(req.getContextPath() + "/statistic.jsp");
    }
}
