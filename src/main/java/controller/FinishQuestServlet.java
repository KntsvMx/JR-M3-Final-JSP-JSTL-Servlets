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

@WebServlet(name = "FinishQuestServlet", urlPatterns = "/finish")
public class FinishQuestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Session not found");
            return;
        }

        Game game = (Game) SessionUtil.getGameFromSession(session);
        if (game == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Game not found in session");
            return;
        }

        Player player = (Player) game.getPlayer();
        if (player == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Player not found in session");
            return;
        }

        SessionUtil.setPlayerToSession(session, player);

        resp.sendRedirect(req.getContextPath() + "/statistic.jsp");
    }
}
