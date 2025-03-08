package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Game;
import model.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.SessionUtil;

import java.io.IOException;

import static constants.SessionAttributes.QUESTION_ATTRIBUTE;

@WebServlet(name = "QuestionServlet", urlPatterns = "/question")
public class QuestionServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(QuestionServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Get request to /question");
        HttpSession session = req.getSession(false);
        Game game = (Game) SessionUtil.getGameFromSession(session);

        if (game != null) {
            logger.info("Game found in session, getting current question");
            Question question = game.getCurrentQuestion();
            logger.info("Current question - {}", question);
            session.setAttribute(QUESTION_ATTRIBUTE, question.getQuestion());
            logger.info("Redirecting to /game.jsp");
            req.getRequestDispatcher("/game.jsp").forward(req, resp);
        } else {
            logger.error("Game not found in session during getting question");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Game not found in session");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Get request to /question to check answer");
        HttpSession session = req.getSession(false);
        if (session == null) {
            logger.error("Session not found during checking answer");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Session not found");
            return;
        }

        Game game = (Game) SessionUtil.getGameFromSession(session);
        if (game == null) {
            logger.error("Game not found in session during checking answer");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Game not found in session");
            return;
        }

        boolean answer = Boolean.parseBoolean(req.getParameter("answer"));
        if (game.checkAnswer(answer)) {
            logger.info("Answer is correct, increase current question index");
            game.increaseCurrentQuestionIndex();
        } else {
            logger.info("Answer is incorrect, set default current question index");
            game.setDefaultCurrentQuestionIndex();
            SessionUtil.setGameToSession(session, game);
            logger.info("Redirecting to /failure page to show failure message after incorrect answer");
            resp.sendRedirect(req.getContextPath() + "/failure");
            return;
        }

        if (game.isGameFinished()) {
            logger.info("Game is finished, redirecting to /finish page");
            SessionUtil.setGameToSession(session, game);
            resp.sendRedirect(req.getContextPath() + "/finish");
        } else {
            logger.info("Game is not finished, redirecting to /question to get next question");
            Question nextQuestion = game.getCurrentQuestion();
            logger.info("Next question - {}", nextQuestion);
            session.setAttribute(QUESTION_ATTRIBUTE, nextQuestion.getQuestion());
            SessionUtil.setGameToSession(session, game);
            logger.info("Redirecting to /question");
            resp.sendRedirect(req.getContextPath() + "/question");
        }
    }
}
