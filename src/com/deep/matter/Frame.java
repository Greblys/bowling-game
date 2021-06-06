package com.deep.matter;

public class Frame {
    private int firstBall = 0;
    private int secondBall = 0;
    private int score = 0;

    public Frame(int firstBall, int secondBall) {
        this.firstBall = firstBall;
        this.secondBall = secondBall;
    }

    public int getFirstBall() {
        return firstBall;
    }

    public void setFirstBall(int firstBall) {
        this.firstBall = firstBall;
    }

    public int getSecondBall() {
        return secondBall;
    }

    public void setSecondBall(int secondBall) {
        this.secondBall = secondBall;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
