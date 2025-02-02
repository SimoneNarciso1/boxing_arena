package com.example.boxingarena.entity;

public class BoxerRanking {

    private String boxerName;
    private int matches;

    private int points;

    public BoxerRanking(String boxerName, int matches,  int points) {

        this.boxerName = boxerName;
        this.matches = matches;

        this.points = points;
    }

    // Getters and Setters


    public  String getBoxerName() {
        return boxerName;
    }

    public void setBoxerName(String boxerName) {
        this.boxerName = boxerName;
    }

    public  int getMatches() {
        return matches;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }



    public  int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
