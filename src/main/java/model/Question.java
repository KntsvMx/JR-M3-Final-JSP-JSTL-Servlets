package model;

public class Question {
    private String question;
    private boolean answer;

    public Question() {

    }

    public Question(String question, boolean answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isAnswer(boolean playerAnswer) {
        return answer == playerAnswer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
