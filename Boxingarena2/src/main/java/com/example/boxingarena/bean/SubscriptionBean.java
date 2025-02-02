package com.example.boxingarena.bean;

public class SubscriptionBean {
    private int points;
    private String boxer;
    private String tournament;
    private int idBoxer;
    private int idTournament;

    public SubscriptionBean(int idBoxer, int idTournament, int points, String boxer, String tournament) {
        this.points = points;
        this.idBoxer = idBoxer;
        this.idTournament = idTournament;
        this.boxer = boxer;
        this.tournament = tournament;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getIdBoxer() {
        return idBoxer;
    }

    public void setIdBoxer(int idBoxer) {
        this.idBoxer = idBoxer;
    }

    public int getIdTournament() {
        return idTournament;
    }

    public void setIdTournament(int idTournament) {
        this.idTournament = idTournament;
    }

    public String getBoxer() {
        return boxer;
    }

    public void setBoxer(String boxer) {
        this.boxer = boxer;
    }

    public String getTournament() {
        return tournament;
    }

    public void setTournament(String tournament) {
        this.tournament = tournament;
    }
}
