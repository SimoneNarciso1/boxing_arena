package com.example.boxingarena.bean;

public class BoxerRankingBean {
    private String boxer;
    private int numberMatches;
    private int totalPoints;

    public BoxerRankingBean(String boxerName, int numberMatches, int totalPoints) {
        this.boxer = boxerName;
        this.numberMatches = numberMatches;
        this.totalPoints = totalPoints;
    }

    public String getBoxer() {
        return boxer;
    }

    public void setBoxer(String boxer) {
        this.boxer = boxer;
    }

    public int getNumberMatches() {
        return numberMatches;
    }

    public void setNumberMatches(int numberMatches) {
        this.numberMatches = numberMatches;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }
}

