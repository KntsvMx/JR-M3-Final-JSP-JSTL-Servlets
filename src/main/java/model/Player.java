package model;

public class Player {

    private String name;
    private Integer score;
    private String ip;
    private String currentQuestion;

    public Player(String name, Integer score, String ip, String currentQuestion) {
        this.name = name;
        this.score = score;
        this.ip = ip;
        this.currentQuestion = currentQuestion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(String currentQuestion) {
        this.currentQuestion = currentQuestion;
    }
}
