package com.example.boxingarena.entity;
import java.time.LocalDate;

public class Receipt {
    private int idReceipt;
    private LocalDate date;
    private int idSubscriptionEntity;
    private int idBoxerEntity;
    private int idTournamentEntity;

    public Receipt() {
    }

    public Receipt(LocalDate date, int idBoxerEntity, int idTournamentEntity) {

        this.date = date;
        this.idBoxerEntity = idBoxerEntity;
        this.idTournamentEntity = idTournamentEntity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getIdSubscriptionEntity() {
        return idSubscriptionEntity;
    }

    public void setIdSubscriptionEntity(int idSubscriptionEntity) {
        this.idSubscriptionEntity = idSubscriptionEntity;
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
}
