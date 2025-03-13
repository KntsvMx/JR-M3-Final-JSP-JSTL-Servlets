package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Game;
import model.Question;
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
class QuestionServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Mock
    private Game game;

    @Mock
    private Question question;

    @InjectMocks
    private QuestionServlet questionServlet;

    @Test
    void testRedirectsToGamePageWhenGameExistsInSession() throws ServletException, IOException {
        when(request.getSession(false)).thenReturn(session);
        when(game.getCurrentQuestion()).thenReturn(question);
        when(question.getQuestion()).thenReturn("Sample Question");
        when(request.getRequestDispatcher("/game.jsp")).thenReturn(requestDispatcher);
        try (MockedStatic<SessionUtil> mockedSessionUtil = mockStatic(SessionUtil.class)) {
            mockedSessionUtil.when(() -> SessionUtil.getGameFromSession(session)).thenReturn(game);

            questionServlet.doGet(request, response);

            verify(session).setAttribute("question", "Sample Question");
            verify(requestDispatcher).forward(request, response);
        }
    }

    @Test
    void testSendsErrorWhenGameNotFoundInSession() throws ServletException, IOException {
        when(request.getSession(false)).thenReturn(session);
        try (MockedStatic<SessionUtil> mockedSessionUtil = mockStatic(SessionUtil.class)) {
            mockedSessionUtil.when(() -> SessionUtil.getGameFromSession(session)).thenReturn(null);

            questionServlet.doGet(request, response);

            verify(response).sendError(HttpServletResponse.SC_NOT_FOUND, "Game not found in session");
        }
    }

    @Test
    void testSendsErrorWhenSessionNotFound() throws ServletException, IOException {
        when(request.getSession(false)).thenReturn(null);

        questionServlet.doPost(request, response);

        verify(response).sendError(HttpServletResponse.SC_NOT_FOUND, "Session not found");
    }

    @Test
    void testRedirectsToFailurePageWhenAnswerIsIncorrect() throws ServletException, IOException {
        when(request.getSession(false)).thenReturn(session);
        when(request.getContextPath()).thenReturn("");
        when(request.getParameter("answer")).thenReturn("false");
        try (MockedStatic<SessionUtil> mockedSessionUtil = mockStatic(SessionUtil.class)) {
            mockedSessionUtil.when(() -> SessionUtil.getGameFromSession(session)).thenReturn(game);
            when(game.checkAnswer(false)).thenReturn(false);

            questionServlet.doPost(request, response);

            verify(game).setDefaultCurrentQuestionIndex();
            mockedSessionUtil.verify(() -> SessionUtil.setGameToSession(session, game));
            verify(response).sendRedirect(request.getContextPath() + "/failure");
        }
    }

    @Test
    void testRedirectsToFinishPageWhenGameIsFinished() throws ServletException, IOException {
        when(request.getSession(false)).thenReturn(session);
        when(request.getContextPath()).thenReturn("");
        when(request.getParameter("answer")).thenReturn("true");
        try (MockedStatic<SessionUtil> mockedSessionUtil = mockStatic(SessionUtil.class)) {
            mockedSessionUtil.when(() -> SessionUtil.getGameFromSession(session)).thenReturn(game);
            when(game.checkAnswer(true)).thenReturn(true);
            when(game.isGameFinished()).thenReturn(true);

            questionServlet.doPost(request, response);

            mockedSessionUtil.verify(() -> SessionUtil.setGameToSession(session, game));
            verify(response).sendRedirect(request.getContextPath() + "/finish");
        }
    }

    @Test
    void testRedirectsToNextQuestionWhenAnswerIsCorrectAndGameNotFinished() throws ServletException, IOException {
        when(request.getSession(false)).thenReturn(session);
        when(request.getContextPath()).thenReturn("");
        when(request.getParameter("answer")).thenReturn("true");
        when(game.getCurrentQuestion()).thenReturn(question);
        when(question.getQuestion()).thenReturn("Next Question");
        try (MockedStatic<SessionUtil> mockedSessionUtil = mockStatic(SessionUtil.class)) {
            mockedSessionUtil.when(() -> SessionUtil.getGameFromSession(session)).thenReturn(game);
            when(game.checkAnswer(true)).thenReturn(true);
            when(game.isGameFinished()).thenReturn(false);

            questionServlet.doPost(request, response);

            verify(game).increaseCurrentQuestionIndex();
            verify(session).setAttribute("question", "Next Question");
            mockedSessionUtil.verify(() -> SessionUtil.setGameToSession(session, game));
            verify(response).sendRedirect(request.getContextPath() + "/question");
        }
    }
}
