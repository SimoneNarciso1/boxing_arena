package com.example.boxingarena.entity;

import java.time.LocalDate;

public class Tournament {

    //region properties

    private String name;
    private String location;
    private Integer number;
    private int cost;
    private LocalDate date;
    private int id;
    private String creatorName;

    //endregion

    //region constructor

    public Tournament(String name, String location, Integer number, int cost, LocalDate date, String creatorName) {
        this.name = name;
        this.location = location;
        this.number = number;
        this.cost = cost;
        this.date = date;
        this.creatorName = creatorName;
    }

    public Tournament(int id, String name, String location, Integer number, int cost, LocalDate date, String creatorName) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.number = number;
        this.cost = cost;
        this.date = date;
        this.creatorName = creatorName;
    }

    public Tournament(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //endregion

    //region getter setter

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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    //endregion
}
