package com.kata.tictactoe.model;

public class Player {

    String playerId;
    String playerName;

    public Player(String playerId, String playerName) {
        this.playerId = playerId;
        this.playerName = playerName;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
