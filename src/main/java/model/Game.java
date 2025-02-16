package model;

import java.util.ArrayList;

public class Game {
    private final ArrayList<Question> questions;
    private Player player;
    private int currentQuestionIndex;

    public Game(ArrayList<Question> questions, Player player) {
        this.questions = initQuestions();
        this.player = player;
        this.currentQuestionIndex = 0;
    }

    private ArrayList<Question> initQuestions() {
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question("The Sun is planet", false));
        questions.add(new Question("The Earth is planet", true));
        questions.add(new Question("Venus is the hottest planet in the Solar System", true));
        questions.add(new Question("Astronaut experience zero gravity in space", true));
        questions.add(new Question("The Moon is a star", false));
        questions.add(new Question("The Earth is flat", false));
        questions.add(new Question("The Earth is the third planet from the Sun", true));
        questions.add(new Question("The Earth is the only planet that has life", true));
        questions.add(new Question("The Earth is the largest planet in the Solar System", false));
        questions.add(new Question("Mars has liquid water on its surface", false));
        return questions;
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
