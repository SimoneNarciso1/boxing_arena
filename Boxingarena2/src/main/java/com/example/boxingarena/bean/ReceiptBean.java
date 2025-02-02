package com.example.boxingarena.bean;

import java.time.LocalDate;

public class ReceiptBean {
    private LocalDate date;
    private int idSubscription;
    private int idBoxer;
    private int idTournament;


    public ReceiptBean() {
    }


    public ReceiptBean(LocalDate date, int idSubscription, int idBoxer, int idTournament) {
        this.date = date;
        this.idSubscription = idSubscription;
        this.idBoxer = idBoxer;
        this.idTournament = idTournament;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public int getIdSubscription() {
        return idSubscription;
    }

    public void setIdSubscription(int idSubscription) {
        this.idSubscription = idSubscription;
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
}
