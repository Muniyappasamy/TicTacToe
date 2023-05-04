package com.kata.tictactoe.service;

import com.kata.tictactoe.exceptions.PlayerNotFoundException;
import com.kata.tictactoe.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayersService {

    List<Player> players;

    public PlayersService() {
        players = new ArrayList<>(2);
        players.add(new Player("X", "Player 1"));
        players.add(new Player("O", "Player 2"));
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getPlayer(String playerId) {
        List<Player> collect = players.stream()
                .filter(e -> e.getPlayerId().equals(playerId))
                .collect(Collectors.toList());
        if (collect.size() > 0) {
            return collect.get(0);
        }
        throw new PlayerNotFoundException("Player " + playerId + " not found");
    }
}
