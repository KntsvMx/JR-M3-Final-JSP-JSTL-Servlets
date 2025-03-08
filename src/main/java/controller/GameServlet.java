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

@WebServlet(name = "GameServlet", urlPatterns = {"/game"})
public class GameServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(GameServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        logger.info("Get request to /game");
        HttpSession session = req.getSession(false);
        createGame(req, resp, session);
    }

    private static void createGame(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws ServletException, IOException {
        logger.info("Creating game");
        Player player = SessionUtil.getPlayerFromSession(session);
        Game game = SessionUtil.getGameFromSession(session);
        logger.info("Game created successfully - {}", game);
        if (game == null && player != null) {
            logger.info("Game is null and player is not null, creating new game");
            game = new Game(player);
            SessionUtil.setGameToSession(session, game);
            SessionUtil.removePlayerAttributeFromSession(session);
            logger.info("Game created successfully, redirecting to /question");
            resp.sendRedirect(req.getContextPath() + "/question");
        } else if (player == null) {
            logger.warn("Player is null, redirecting to /register");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Player not found in session");
        } else {
            logger.info("Game already exists, redirecting to /question");
            resp.sendRedirect(req.getContextPath() + "/question");
        }
    }
}
