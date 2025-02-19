package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Game;
import model.Player;

import java.io.IOException;

import static constants.SessionAttributes.GAME_ATTRIBUTE;
import static constants.SessionAttributes.PLAYER_ATTRIBUTE;

@WebServlet(name = "GameServlet", urlPatterns = {"/game"})
public class GameServlet extends HttpServlet {
    private Player player;
    private Game game;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        player = (Player) req.getSession().getAttribute(PLAYER_ATTRIBUTE);
        game = new Game(player);
        req.getSession().setAttribute(GAME_ATTRIBUTE, game);
        req.getRequestDispatcher("/game.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
