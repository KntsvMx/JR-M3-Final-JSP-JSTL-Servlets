package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet(name = "FailureServlet", urlPatterns = "/failure")
public class FailureServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(FailureServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Get request to /failure and redirecting to /failure.jsp");
        req.getRequestDispatcher("/failure.jsp").forward(req, resp);
    }
}
