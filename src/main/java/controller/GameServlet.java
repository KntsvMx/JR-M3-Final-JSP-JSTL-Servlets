package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Game;
import model.Player;
import util.SessionUtil;

import java.io.IOException;

@WebServlet(name = "GameServlet", urlPatterns = {"/game"})
public class GameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        createGame(req, resp, session);
    }

    private static void createGame(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws ServletException, IOException {
        Player player = SessionUtil.getPlayerFromSession(session);
        Game game = SessionUtil.getGameFromSession(session);
        if (game == null && player != null) {
            game = new Game(player);
            SessionUtil.setGameToSession(session, game);
            SessionUtil.removePlayerAttributeFromSession(session);
            resp.sendRedirect(req.getContextPath() + "/question");
        } else if (player == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Player not found in session");
        } else {
            resp.sendRedirect(req.getContextPath() + "/question");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
