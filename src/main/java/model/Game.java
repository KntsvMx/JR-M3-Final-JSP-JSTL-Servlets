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

   public Question getCurrentQuestion() {
        if (currentQuestionIndex < questions.size()) {
            return questions.get(currentQuestionIndex);
        }
        return null;
    }

    public void setDefaultCurrentQuestionIndex() {
        this.currentQuestionIndex = 0;
    }

    public void increaseCurrentQuestionIndex() {
        if (currentQuestionIndex < questions.size()) {
            this.currentQuestionIndex++;
        }
    }

    public boolean isGameFinished() {
        return currentQuestionIndex >= questions.size();
    }

    public boolean checkAnswer(boolean answer) {
        if (currentQuestionIndex < questions.size() && questions.get(currentQuestionIndex).isAnswer(answer)) {
            player.increaseScore();
            return true;
        }
        return false;
    }

    public Player getPlayer() {
        return player;
    }
}
