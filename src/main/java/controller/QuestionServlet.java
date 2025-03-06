package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Game;
import util.SessionUtil;

import java.io.IOException;

import static constants.SessionAttributes.QUESTION_ATTRIBUTE;

@WebServlet(name = "QuestionServlet", urlPatterns = "/question")
public class QuestionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Game game = (Game) SessionUtil.getGameFromSession(session);

        if (game != null) {
            String question = game.getQuestions().get(game.getCurrentQuestionIndex()).getQuestion();
            session.setAttribute(QUESTION_ATTRIBUTE, question);
            req.getRequestDispatcher("/game.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Game not found in session");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Session not found");
            return;
        }

        Game game = (Game) SessionUtil.getGameFromSession(session);
        if (game == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Game not found in session");
            return;
        }

        boolean answer = Boolean.parseBoolean(req.getParameter("answer"));
        int currentQuestionIndex = game.getCurrentQuestionIndex();
        if (currentQuestionIndex >= game.getQuestions().size()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid question index");
            return;
        }

        if (game.checkAnswer(answer)) {
            game.increaseCurrentQuestionIndex();
        } else {
            returnToFailurePage(req, resp);
        }

        if (game.getCurrentQuestionIndex() < game.getQuestions().size()) {
           req.getRequestDispatcher("/question").forward(req, resp);
        } else {
            req.getRequestDispatcher("/finish").forward(req, resp);
        }
    }


    private void returnToFailurePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/failure.jsp").forward(req, resp);
    }
}
