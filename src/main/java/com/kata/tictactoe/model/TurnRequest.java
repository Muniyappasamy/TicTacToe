package com.kata.tictactoe.model;


public class TurnRequest {

    private String playerId;


    private Integer position;

    public TurnRequest(String playerId, Integer position) {
        this.playerId = playerId;
        this.position = position;
    }

    @Override
    public String toString() {
        return "TurnRequest{" +
                "playerId='" + playerId + '\'' +
                ", position=" + position +
                '}';
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
