package util;

import jakarta.servlet.http.HttpSession;
import model.Player;

import static constants.SessionAttributes.MAX_INACTIVITY;
import static constants.SessionAttributes.PLAYER_ATTRIBUTE;

public class SessionUtil {
    public static void setMaxInactivityToSession(HttpSession session, int maxInactivity) {
        session.setAttribute(MAX_INACTIVITY, maxInactivity);
    }

    public static void setPlayerToSession(HttpSession session, Player player) {
        session.setAttribute(PLAYER_ATTRIBUTE, player);
    }
}
