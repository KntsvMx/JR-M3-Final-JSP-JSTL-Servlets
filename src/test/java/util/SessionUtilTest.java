package util;

import jakarta.servlet.http.HttpSession;
import model.Game;
import model.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static constants.SessionAttributes.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SessionUtilTest {

    @Mock
    private HttpSession session;

    @Mock
    private Player player;

    @Mock
    private Game game;

    @Test
    void setsMaxInactivityToSession() {
        SessionUtil.setMaxInactivityToSession(session, 30);
        verify(session).setAttribute(MAX_INACTIVITY, 30);
    }

    @Test
    void setsPlayerToSession() {
        SessionUtil.setPlayerToSession(session, player);
        verify(session).setAttribute(PLAYER_ATTRIBUTE, player);
    }

    @Test
    void throwsExceptionWhenSettingNullPlayerToSession() {
        assertThrows(IllegalArgumentException.class, () -> SessionUtil.setPlayerToSession(session, null));
    }

    @Test
    void getsPlayerFromSession() {
        when(session.getAttribute(PLAYER_ATTRIBUTE)).thenReturn(player);
        Player result = SessionUtil.getPlayerFromSession(session);
        assertEquals(player, result);
    }

    @Test
    void throwsExceptionWhenGettingPlayerFromNullSession() {
        assertThrows(IllegalArgumentException.class, () -> SessionUtil.getPlayerFromSession(null));
    }

    @Test
    void setsGameToSession() {
        SessionUtil.setGameToSession(session, game);
        verify(session).setAttribute(GAME_ATTRIBUTE, game);
    }

    @Test
    void throwsExceptionWhenSettingNullGameToSession() {
        assertThrows(IllegalArgumentException.class, () -> SessionUtil.setGameToSession(session, null));
    }

    @Test
    void getsGameFromSession() {
        when(session.getAttribute(GAME_ATTRIBUTE)).thenReturn(game);
        Game result = SessionUtil.getGameFromSession(session);
        assertEquals(game, result);
    }

    @Test
    void removesPlayerAttributeFromSession() {
        SessionUtil.removePlayerAttributeFromSession(session);
        verify(session).removeAttribute(PLAYER_ATTRIBUTE);
    }
}