package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;
    private static final String FIRST_QUESTION = "The Sun is planet";
    private static final String SECOND_QUESTION = "The Earth is planet";

    @BeforeEach
    public void setUp() {
        Player player = new Player("John", 0, "127.0.0.1", FIRST_QUESTION);
        game = new Game(player);
    }

    @Test
    void testGetQuestions() {
        ArrayList<Question> questions = game.getQuestions();
        assertNotNull(questions);
    }

    @Test
    void testGetCurrentQuestion() {
        Question question = game.getCurrentQuestion();
        assertEquals(FIRST_QUESTION, question.getQuestion());
    }

    @Test
    void testSetDefaultCurrentQuestionIndex() {
        game.setDefaultCurrentQuestionIndex();
        Question question = game.getCurrentQuestion();
        assertEquals(FIRST_QUESTION, question.getQuestion());
    }

    @Test
    void testIncreaseCurrentQuestionIndex() {
        game.setDefaultCurrentQuestionIndex();
        game.increaseCurrentQuestionIndex();
        Question question = game.getCurrentQuestion();
        assertEquals(SECOND_QUESTION, question.getQuestion());
    }

    @Test
    void testIsGameFinishedFalse() {
        boolean isGameFinished = game.isGameFinished();
        assertFalse(isGameFinished);
    }

    @Test
    void testIsGameFinishedTrue() {
        game.setDefaultCurrentQuestionIndex();
        for (int i = 0; i < game.getQuestions().size(); i++) {
            game.increaseCurrentQuestionIndex();
        }
        boolean isGameFinished = game.isGameFinished();
        assertTrue(isGameFinished);
    }

    @Test
    void testCheckAnswerIfExpectedAnswerOfQuestionIsFalse() {
        game.setDefaultCurrentQuestionIndex();
        boolean isAnswerCorrect = game.checkAnswer(false);
        assertTrue(isAnswerCorrect);
    }

    @Test
    void testCheckAnswerIfExpectedAnswerOfQuestionIsTrue() {
        game.setDefaultCurrentQuestionIndex();
        game.increaseCurrentQuestionIndex();
        boolean isAnswerCorrect = game.checkAnswer(true);
        assertTrue(isAnswerCorrect);
    }

    @Test
    void testCheckAnswerIfExpectedAnswerOfQuestionIsWrong() {
        game.setDefaultCurrentQuestionIndex();
        boolean isAnswerCorrect = game.checkAnswer(true);
        assertFalse(isAnswerCorrect);
    }

    @Test
    void testGetPlayer() {
        Player player = game.getPlayer();
        assertNotNull(player);
    }
}