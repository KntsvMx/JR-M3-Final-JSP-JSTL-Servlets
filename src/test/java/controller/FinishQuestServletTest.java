package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Game;
import model.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import util.SessionUtil;

import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FinishQuestServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private Game game;

    @Mock
    private Player player;

    @InjectMocks
    private FinishQuestServlet finishQuestServlet;

    @Test
    void testDoGetSessionNotFound() throws ServletException, IOException {
        when(request.getSession(false)).thenReturn(null);

        finishQuestServlet.doGet(request, response);

        verify(response).sendError(HttpServletResponse.SC_NOT_FOUND, "Session not found");
    }

    @Test
    void testDoGetGameNotFoundInSession() throws ServletException, IOException {
        when(request.getSession(false)).thenReturn(session);
        try (MockedStatic<SessionUtil> mockedSessionUtil = mockStatic(SessionUtil.class)) {
            mockedSessionUtil.when(() -> SessionUtil.getGameFromSession(session)).thenReturn(null);

            finishQuestServlet.doGet(request, response);

            verify(response).sendError(HttpServletResponse.SC_NOT_FOUND, "Game not found in session");
        }
    }

    @Test
    void testDoGetPlayerNotFoundInSession() throws ServletException, IOException {
        when(request.getSession(false)).thenReturn(session);
        try (MockedStatic<SessionUtil> mockedSessionUtil = mockStatic(SessionUtil.class)) {
            mockedSessionUtil.when(() -> SessionUtil.getGameFromSession(session)).thenReturn(game);
            when(game.getPlayer()).thenReturn(null);

            finishQuestServlet.doGet(request, response);

            verify(response).sendError(HttpServletResponse.SC_NOT_FOUND, "Player not found in session");
        }
    }

    @Test
    void testDoGetSuccessful() throws ServletException, IOException {
        when(request.getSession(false)).thenReturn(session);
        try (MockedStatic<SessionUtil> mockedSessionUtil = mockStatic(SessionUtil.class)) {
            mockedSessionUtil.when(() -> SessionUtil.getGameFromSession(session)).thenReturn(game);
            when(game.getPlayer()).thenReturn(player);

            finishQuestServlet.doGet(request, response);

            mockedSessionUtil.verify(() -> SessionUtil.setPlayerToSession(session, player));
            verify(response).sendRedirect(request.getContextPath() + "/statistic.jsp");
        }
    }
}