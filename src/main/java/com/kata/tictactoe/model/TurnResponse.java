package com.kata.tictactoe.model;


import java.util.Map;

public class TurnResponse {
    public Boolean gameOver = false;
    public Player winner;
    public Map<String, String> state;

    public TurnResponse(Player winner, Map<String, String> state) {
        if (winner != null) {
            this.winner = winner;
            this.gameOver = true;
        }
        this.state = state;
    }

    public Boolean getGameOver() {
        return gameOver;
    }

    public void setGameOver(Boolean gameOver) {
        this.gameOver = gameOver;
    }

    public Map<String, String> getState() {
        return state;
    }

    public void setState(Map<String, String> state) {
        this.state = state;
    }


}
