package com.example.boxingarena.bean;

import java.time.LocalDate;

public class BoxingTournament {

    private int id;
    private String name;
    private String location;
    private LocalDate date;
    private int cost;




    public BoxingTournament(int id, String name, String location, LocalDate date) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.date = date;

    }


    public BoxingTournament(int id, String name, String location, LocalDate date, int cost) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.date = date;
        this.cost = cost;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }



    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

}

