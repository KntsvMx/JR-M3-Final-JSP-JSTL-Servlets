package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Game;
import model.Question;
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
            Question question = game.getCurrentQuestion();
            session.setAttribute(QUESTION_ATTRIBUTE, question.getQuestion());
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
        if (game.checkAnswer(answer)) {
            game.increaseCurrentQuestionIndex();
        } else {
            SessionUtil.setGameToSession(session, game);
            returnToFailurePage(req, resp);
            return;
        }

        if (game.isGameFinished()) {
            SessionUtil.setGameToSession(session, game);
            resp.sendRedirect(req.getContextPath() + "/finish");
        } else {
            Question nextQuestion = game.getCurrentQuestion();
            session.setAttribute(QUESTION_ATTRIBUTE, nextQuestion.getQuestion());
            SessionUtil.setGameToSession(session, game);
            resp.sendRedirect(req.getContextPath() + "/question");
        }
    }

    private void returnToFailurePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/failure.jsp").forward(req, resp);
    }
}
