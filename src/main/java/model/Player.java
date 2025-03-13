package model;

public class Player {
    private String name;
    private int score;
    private String ip;

    public Player() {

    }
// TODO: delete currentQuestion from the constructor
    public Player(String name, int score, String ip, String currentQuestion) {
        this.name = name;
        this.score = score;
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void increaseScore() {
        this.score++;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
