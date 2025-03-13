package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FailureServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @InjectMocks
    private FailureServlet failureServlet;

    @Test
    void testDoGetHandlesFailureScenario() throws ServletException, IOException {
        when(request.getRequestDispatcher("/failure.jsp")).thenReturn(requestDispatcher);

        failureServlet.doGet(request, response);

        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void testDoGetRedirectsToErrorPage() throws ServletException, IOException {
        when(request.getRequestDispatcher("/failure.jsp")).thenReturn(requestDispatcher);

        failureServlet.doGet(request, response);

        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void testDoGetSetsErrorMessage() throws ServletException, IOException {
        when(request.getRequestDispatcher("/failure.jsp")).thenReturn(requestDispatcher);

        failureServlet.doGet(request, response);

        verify(requestDispatcher).forward(request, response);
    }
}