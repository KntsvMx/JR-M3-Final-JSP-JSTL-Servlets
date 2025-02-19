package model;

import util.QuestionUtil;

import java.util.ArrayList;

public class Game {
    private final ArrayList<Question> questions;
    private Player player;
    private int currentQuestionIndex;

    public Game(Player player) {
        this.questions = QuestionUtil.initQuestions();
        this.player = player;
        this.currentQuestionIndex = 0;
    }


    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    public void setCurrentQuestionIndex(int currentQuestionIndex) {
        this.currentQuestionIndex = currentQuestionIndex;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
