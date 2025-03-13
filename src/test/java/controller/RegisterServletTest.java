package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import util.SessionUtil;

import java.io.IOException;

import static constants.SessionAttributes.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegisterServletTest {

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher requestDispatcher;

    @InjectMocks
    private RegisterServlet registerServlet;

//    @BeforeEach
//    public void setUp() {
//        when(request.getContextPath()).thenReturn("");
//    }

    @Test
    public void testDoGetRedirectToRegisterJSP() throws ServletException, IOException {
        registerServlet.doGet(request, response);
        verify(response).sendRedirect(request.getContextPath() + "/register.jsp");
    }

    @Test
    public void testDoPostAndPlayerNameIsNullThenRedirectToRegisterJSPAgain() throws ServletException, IOException {
        when(request.getParameter(USER_NAME)).thenReturn(null);
        when(request.getRequestDispatcher("/register.jsp")).thenReturn(requestDispatcher);
        registerServlet.doPost(request, response);
        verify(request).getRequestDispatcher("/register.jsp");
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testDoPostAndPlayerNameIsEmptyThenRedirectToRegisterJSPAgain() throws ServletException, IOException {
        when(request.getParameter(USER_NAME)).thenReturn("");
        when(request.getRequestDispatcher("/register.jsp")).thenReturn(requestDispatcher);
        registerServlet.doPost(request, response);
        verify(request).getRequestDispatcher("/register.jsp");
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testDoPostAndPlayerNameIsNullThenSetErrorMessage() throws ServletException, IOException {
        when(request.getParameter(USER_NAME)).thenReturn(null);
        when(request.getRequestDispatcher("/register.jsp")).thenReturn(requestDispatcher);
        registerServlet.doPost(request, response);
        verify(request).setAttribute("errorMessage", "Player name is required");
    }

    @Test
    public void testDoPostAndPlayerNameInNotNullThenSetSessionTimeout() throws ServletException, IOException {
        when(request.getSession(true)).thenReturn(session);
        when(request.getParameter(USER_NAME)).thenReturn("John");
        when(request.getRemoteAddr()).thenReturn("127.0.0.1");

        try (MockedStatic<SessionUtil> sessionUtilMockedStatic = mockStatic(SessionUtil.class)) {
            registerServlet.doPost(request, response);
            sessionUtilMockedStatic.verify(() -> SessionUtil.setMaxInactivityToSession(session, MAX_INACTIVITY_PERIOD));

        }
    }

    @Test
    public void testDoPostAndPlayerNameIsNotEmptyAndNotNullThenRedirectToStart() throws ServletException, IOException {
        when(request.getSession(true)).thenReturn(session);
        when(request.getParameter(USER_NAME)).thenReturn("John");
        when(request.getRemoteAddr()).thenReturn("127.0.0.1");

        registerServlet.doPost(request, response);

        verify(session).setAttribute(eq(PLAYER_ATTRIBUTE), any(Player.class));
        verify(response).sendRedirect(request.getContextPath() + "/start");
    }


}