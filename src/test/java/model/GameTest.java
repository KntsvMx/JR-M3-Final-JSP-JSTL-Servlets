package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        Player player = new Player("John", 0, "127.0.0.1", "The Sun if planet");
        game = new Game(player);
    }

    @Test
    void getQuestions() {
        ArrayList<Question> questions = game.getQuestions();
        assertNotNull(questions);
    }

    @Test
    void getCurrentQuestion() {
        String expectedQuestion = "The Sun is planet";
        Question question = game.getCurrentQuestion();
        assertEquals(expectedQuestion, question.getQuestion());
    }

    @Test
    void setDefaultCurrentQuestionIndex() {
        game.setDefaultCurrentQuestionIndex();
        String question = game.getCurrentQuestion().getQuestion();
        String expectedQuestion = "The Sun is planet";
        assertEquals(expectedQuestion, question);
    }

    @Test
    void increaseCurrentQuestionIndex() {
        game.setDefaultCurrentQuestionIndex();
        game.increaseCurrentQuestionIndex();
        String increasedExpectedIndexQuestion = "The Earth is planet";
        Question  increasedIndexQuestion = game.getCurrentQuestion();
        assertEquals(increasedExpectedIndexQuestion, increasedIndexQuestion.getQuestion());
    }

    @Test
    void isGameFinishedFalse() {
        boolean isGameFinished = game.isGameFinished();
        assertFalse(isGameFinished);
    }

    @Test
    @Disabled
    void isGameFinishedTrue() {
        game.setDefaultCurrentQuestionIndex();
        for (int i = 0; i < game.getQuestions().size(); i++) {
            game.increaseCurrentQuestionIndex();
        }
        boolean isGameFinished = game.isGameFinished();
        assertTrue(isGameFinished);
    }

    @Test
    void checkAnswerIfExpectedAnswerOfQuestionIsFalse() {
        game.setDefaultCurrentQuestionIndex();
        boolean isAnswerCorrect = game.checkAnswer(false);
        assertTrue(isAnswerCorrect);
    }

    @Test
    void checkAnswerIfExpectedAnswerOfQuestionIsTrue() {
        game.setDefaultCurrentQuestionIndex();
        game.increaseCurrentQuestionIndex();
        boolean isAnswerCorrect = game.checkAnswer(true);
        assertTrue(isAnswerCorrect);
    }

    @Test
    void checkAnswerIfExpectedAnswerOfQuestionIsWrong() {
        game.setDefaultCurrentQuestionIndex();
        boolean isAnswerCorrect = game.checkAnswer(true);
        assertFalse(isAnswerCorrect);
    }

    @Test
    void getPlayer() {
        Player player = game.getPlayer();
        assertNotNull(player);
    }
}