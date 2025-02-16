package util;

import javax.servlet.http.HttpSession;

public class SessionUtil {
    public static void setUserNameToSession(HttpSession session, String userName) {
        session.setAttribute("userName", userName);
    }

    public static String getUserNameFromSession(HttpSession session) {
        return (String) session.getAttribute("userName");
    }

    public static void setGameIdToSession(HttpSession session, int gameId) {
        session.setAttribute("gameId", gameId);
    }

    public static int getGameIdFromSession(HttpSession session) {
        return (int) session.getAttribute("gameId");
    }


    public static void setUserIpToSession(HttpSession session, String userIp) {
        session.setAttribute("userIp", userIp);
    }

    public static String getUserIpFromSession(HttpSession session) {
        return (String) session.getAttribute("userIp");
    }

    public static void setMaxInactivityToSession(HttpSession session, int maxInactivity) {
        session.setAttribute("maxInactivity", maxInactivity);
    }
}
