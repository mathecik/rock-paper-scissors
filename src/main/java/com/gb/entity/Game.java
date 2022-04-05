package com.gb.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int gameId;
    Player firstPlayer;
    int firstPlayerScore;

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    boolean saved;

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public void setFirstPlayer(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public void setFirstPlayerScore(int firstPlayerScore) {
        this.firstPlayerScore = firstPlayerScore;
    }

    public void setSecondPlayer(Player secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public void setSecondPlayerScore(int secondPlayerScore) {
        this.secondPlayerScore = secondPlayerScore;
    }

    public void setRoundCount(int roundCount) {
        this.roundCount = roundCount;
    }

    Player secondPlayer;
    int secondPlayerScore;
    int roundCount;

    public int getGameId() {
        return gameId;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public int getFirstPlayerScore() {
        return firstPlayerScore;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public int getSecondPlayerScore() {
        return secondPlayerScore;
    }

    public int getRoundCount() {
        return roundCount;
    }
}
