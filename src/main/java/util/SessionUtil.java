package util;

import jakarta.servlet.http.HttpSession;

import static constants.SessionAttributes.*;

public class SessionUtil {
    public static void setUserNameToSession(HttpSession session, String userName) {
        session.setAttribute(USER_NAME, userName);
    }

    public static String getUserNameFromSession(HttpSession session) {
        return (String) session.getAttribute(USER_NAME);
    }

    public static void setGameIdToSession(HttpSession session, int gameId) {
        session.setAttribute(GAME_ID, gameId);
    }

    public static int getGameIdFromSession(HttpSession session) {
        return (int) session.getAttribute(GAME_ID);
    }


    public static void setUserIpToSession(HttpSession session, String userIp) {
        session.setAttribute(USER_IP, userIp);
    }

    public static String getUserIpFromSession(HttpSession session) {
        return (String) session.getAttribute(USER_IP);
    }

    public static void setMaxInactivityToSession(HttpSession session, int maxInactivity) {
        session.setAttribute(MAX_INACTIVITY, maxInactivity);
    }
}
