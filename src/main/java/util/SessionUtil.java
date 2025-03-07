package util;

import jakarta.servlet.http.HttpSession;
import model.Game;
import model.Player;

import static constants.SessionAttributes.*;

public class SessionUtil {
    public static void setMaxInactivityToSession(HttpSession session, int maxInactivity) {
        session.setAttribute(MAX_INACTIVITY, maxInactivity);
    }

    public static void setPlayerToSession(HttpSession session, Player player) {
        if (session == null || player == null) {
            throw new IllegalArgumentException("Session is null");
        }
        session.setAttribute(PLAYER_ATTRIBUTE, player);
    }

    public static Player getPlayerFromSession(HttpSession session) {
        if (session == null) {
            throw new IllegalArgumentException("Session is null");
        }
        return (Player) session.getAttribute(PLAYER_ATTRIBUTE);
    }

    public static void setGameToSession(HttpSession session, Game game) {
        if (session != null && game != null) {
            session.setAttribute(GAME_ATTRIBUTE, game);
        } else {
            throw new IllegalArgumentException("Session is null");
        }
    }

    public static Game getGameFromSession(HttpSession session) {
        return (Game) session.getAttribute(GAME_ATTRIBUTE);
    }


    public static void removePlayerAttributeFromSession(HttpSession session) {
        session.removeAttribute(PLAYER_ATTRIBUTE);
    }
}
