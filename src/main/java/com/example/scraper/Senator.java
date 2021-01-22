package com.example.scraper;

public class Senator {
    private String name;
    private String state;
    private String party;
    private String classNum;

    public Senator(String name, String state, String party, String classNum) {
        this.name = name;
        this.state = state;
        this.party = party;
        this.classNum = classNum;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public String getParty() {
        return party;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public String toString() {
        return this.name;
    }

}