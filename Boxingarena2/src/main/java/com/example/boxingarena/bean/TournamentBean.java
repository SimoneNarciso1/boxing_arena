package com.example.boxingarena.bean;

import java.time.LocalDate;

public class TournamentBean {


    private Integer id;
    private String name;
    private String location;
    private Integer number;
    private Integer cost;
    private LocalDate date;

    public TournamentBean() {}

    public TournamentBean(int id, String name, String location, Integer number, Integer cost, LocalDate date) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.number = number;
        this.cost = cost;
        this.date = date;
    }

    public TournamentBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Integer getCost() {
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

    public void checkField(String name, String location, LocalDate date, Integer cost, Integer number) throws IllegalArgumentException {
        if (name.isEmpty() || location.isEmpty() || date == null || cost == null || number == null) {
            throw new IllegalArgumentException("Please fill all the fields");
        }
    }
}
