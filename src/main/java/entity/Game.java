package entity;

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
