package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static constants.SessionAttributes.PLAYER_ATTRIBUTE;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class InitServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher requestDispatcher;

    @InjectMocks
    private InitServlet initServlet;

    @BeforeEach
    public void setUp() {
        when(request.getContextPath()).thenReturn("");
    }

    @Test
    public void testDoGetWhenSessionIsNull() throws Exception {
        when(request.getSession(false)).thenReturn(null);
        initServlet.doGet(request, response);
        verify(response).sendRedirect("/register");
    }

    @Test
    public void testDoGetWhenSessionAttributeIsNull() throws Exception {
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute(PLAYER_ATTRIBUTE)).thenReturn(null);
        initServlet.doGet(request, response);
        verify(response).sendRedirect("/register");
    }


    @Test
    public void testDoGetWhenSessionAttributeIsNotNull() throws Exception {
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute(PLAYER_ATTRIBUTE)).thenReturn(new Object());
        initServlet.doGet(request, response);
        verify(response).sendRedirect("/game");
    }

    @Test
    public void testDoGetHandlesIOException() throws Exception {
        when(request.getSession(false)).thenReturn(session);
        doThrow(new IOException()).when(response).sendRedirect(anyString());
        assertThrows(IOException.class, () -> initServlet.doGet(request, response));
    }
}