package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LogoutServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @InjectMocks
    private LogoutServlet logoutServlet;

    @Test
    void testRedirectsToHomePageAfterLogout() throws ServletException, IOException {
        when(request.getSession(false)).thenReturn(session);
        when(request.getContextPath()).thenReturn("");

        logoutServlet.doGet(request, response);

        verify(session).invalidate();
        verify(response).sendRedirect(request.getContextPath() + "/index.jsp");
    }

    @Test
    void testHandlesNoSessionGracefully() throws ServletException, IOException {
        when(request.getSession(false)).thenReturn(null);
        when(request.getContextPath()).thenReturn("");

        logoutServlet.doGet(request, response);

        verify(response).sendRedirect(request.getContextPath() + "/index.jsp");
    }
}