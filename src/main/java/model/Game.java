package model;

import util.QuestionUtil;

import java.util.ArrayList;

public class Game {
    private final ArrayList<Question> questions;
    private final Player player;
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

    public void increaseCurrentQuestionIndex() {
        if (currentQuestionIndex < questions.size()) {
            this.currentQuestionIndex++;
        }
    }

    public boolean checkAnswer(boolean answer) {
        if (questions.get(currentQuestionIndex).isAnswer(answer)) {
            player.increaseScore();
        }
        return answer;
    }

    public Player getPlayer() {
        return player;
    }
}
