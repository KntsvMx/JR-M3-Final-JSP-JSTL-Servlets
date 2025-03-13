package util;

import model.Question;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class QuestionUtilTest {

    @Test
    void testReturnsNonEmptyList() {
        ArrayList<Question> questions = QuestionUtil.initQuestions();
        assertFalse(questions.isEmpty());
    }

    @Test
    void testReturnsCorrectNumberOfQuestions() {
        ArrayList<Question> questions = QuestionUtil.initQuestions();
        assertEquals(10, questions.size());
    }

    @Test
    void testAllQuestionsHaveNonNullText() {
        ArrayList<Question> questions = QuestionUtil.initQuestions();
        for (Question question : questions) {
            assertNotNull(question.getQuestion());
        }
    }
}