package com.example.boxingarena.entity;

public class Subscription {
    private int pointEntity;
    private String boxerEntity;
    private String tournamentEntity;
    private int idBoxerEntity;
    private int idTournamentEntity;

    public Subscription(int idBoxerEntity, int idTournamentEntity, int pointEntity, String boxerEntity, String tournamentEntity) {
        this.pointEntity = pointEntity;
        this.idBoxerEntity = idBoxerEntity;
        this.idTournamentEntity = idTournamentEntity;
        this.boxerEntity = boxerEntity;
        this.tournamentEntity = tournamentEntity;
    }

    public int getPointEntity() {
        return pointEntity;
    }

    public void setPointEntity(int pointEntity) {
        this.pointEntity = pointEntity;
    }

    public int getIdBoxerEntity() {
        return idBoxerEntity;
    }

    public void setIdBoxerEntity(int idBoxerEntity) {
        this.idBoxerEntity = idBoxerEntity;
    }

    public int getIdTournamentEntity() {
        return idTournamentEntity;
    }

    public void setIdTournamentEntity(int idTournamentEntity) {
        this.idTournamentEntity = idTournamentEntity;
    }

    public String getBoxerEntity() {
        return boxerEntity;
    }

    public void setBoxerEntity(String boxerEntity) {
        this.boxerEntity = boxerEntity;
    }

    public String getTournamentEntity() {
        return tournamentEntity;
    }

    public void setTournamentEntity(String tournamentEntity) {
        this.tournamentEntity = tournamentEntity;
    }
}
