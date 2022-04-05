package com.gb.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Player {

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public Player() {
        super();
    }

    @Id
    String playerName;
    Integer savedGameId;

    public Integer getSavedGameId() {
        return savedGameId;
    }

    public void setSavedGameId(Integer savedGameId) {
        this.savedGameId = savedGameId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String name){
        this.playerName = name;
    }
}
