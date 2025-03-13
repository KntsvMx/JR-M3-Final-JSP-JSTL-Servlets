package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Game;
import model.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import util.SessionUtil;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private Player player;

    @Mock
    private Game game;

    @InjectMocks
    private GameServlet gameServlet;


    @Test
    void testDoGetCreatesNewGameWhenGameIsNullAndPlayerIsNotNull() throws ServletException, IOException {
        when(request.getSession(false)).thenReturn(session);
        when(request.getContextPath()).thenReturn("");
        try (MockedStatic<SessionUtil> mockedSessionUtil = mockStatic(SessionUtil.class)) {
            mockedSessionUtil.when(() -> SessionUtil.getPlayerFromSession(session)).thenReturn(player);
            mockedSessionUtil.when(() -> SessionUtil.getGameFromSession(session)).thenReturn(null);

            gameServlet.doGet(request, response);

            ArgumentCaptor<Game> gameCaptor = ArgumentCaptor.forClass(Game.class);
            mockedSessionUtil.verify(() -> SessionUtil.setGameToSession(eq(session), gameCaptor.capture()));
            mockedSessionUtil.verify(() -> SessionUtil.removePlayerAttributeFromSession(session));
            verify(response).sendRedirect(request.getContextPath() + "/question");

            assertNotNull(gameCaptor.getValue());
            assertEquals(player, gameCaptor.getValue().getPlayer());
        }
    }

    @Test
    void testDoGetReturnsErrorWhenPlayerIsNull() throws ServletException, IOException {
        when(request.getSession(false)).thenReturn(session);
        try (MockedStatic<SessionUtil> mockedSessionUtil = mockStatic(SessionUtil.class)) {
            mockedSessionUtil.when(() -> SessionUtil.getPlayerFromSession(session)).thenReturn(null);

            gameServlet.doGet(request, response);

            verify(response).sendError(HttpServletResponse.SC_NOT_FOUND, "Player not found in session");
        }
    }

    @Test
    void testDoGetRedirectsToQuestionWhenGameAlreadyExists() throws ServletException, IOException {
        when(request.getSession(false)).thenReturn(session);
        when(request.getContextPath()).thenReturn("");
        try (MockedStatic<SessionUtil> mockedSessionUtil = mockStatic(SessionUtil.class)) {
            mockedSessionUtil.when(() -> SessionUtil.getGameFromSession(session)).thenReturn(game);
            mockedSessionUtil.when(() -> SessionUtil.getPlayerFromSession(session)).thenReturn(player);

            gameServlet.doGet(request, response);

            verify(response).sendRedirect(request.getContextPath() + "/question");
        }
    }
}