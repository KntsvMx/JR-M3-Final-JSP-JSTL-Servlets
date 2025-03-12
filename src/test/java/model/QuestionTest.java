package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    private Question questionWithArgsConstructor;
    private Question questionWithNoArgsConstructor;

    @BeforeEach
    public void setUp() {
        questionWithArgsConstructor = new Question("Is the sky blue?", true);
        questionWithNoArgsConstructor = new Question();
    }

    @Test
    void testGetQuestionWithArgsConstructor() {
        assertEquals("Is the sky blue?", questionWithArgsConstructor.getQuestion());
    }

    @Test
    void testSetQuestionWithArgsConstructor() {
        questionWithArgsConstructor.setQuestion("Is the grass green?");
        assertEquals("Is the grass green?", questionWithArgsConstructor.getQuestion());
    }

    @Test
    void testIsAnswerWithArgsConstructor() {
        assertTrue(questionWithArgsConstructor.isAnswer(true));
    }

    @Test
    void testSetAnswerWithArgsConstructor() {
        questionWithArgsConstructor.setAnswer(false);
        assertFalse(questionWithArgsConstructor.isAnswer(true));
    }

    @Test
    void testGetQuestionWithNoArgsConstructor() {
        questionWithNoArgsConstructor.setQuestion("Is water wet?");
        assertEquals("Is water wet?", questionWithNoArgsConstructor.getQuestion());
    }

    @Test
    void testSetQuestionWithNoArgsConstructor() {
        questionWithNoArgsConstructor.setQuestion("Is fire hot?");
        assertEquals("Is fire hot?", questionWithNoArgsConstructor.getQuestion());
    }

    @Test
    void testIsAnswerWithNoArgsConstructor() {
        questionWithNoArgsConstructor.setAnswer(true);
        assertTrue(questionWithNoArgsConstructor.isAnswer(true));
    }

    @Test
    void testSetAnswerWithNoArgsConstructor() {
        questionWithNoArgsConstructor.setAnswer(false);
        assertFalse(questionWithNoArgsConstructor.isAnswer(true));
    }
}