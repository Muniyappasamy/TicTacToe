package com.kata.tictactoe.controllers;


import com.kata.tictactoe.exceptions.PlayerWrongTurnException;
import com.kata.tictactoe.exceptions.PlayerXShouldMoveFirstException;
import com.kata.tictactoe.model.Player;
import com.kata.tictactoe.model.TurnRequest;
import com.kata.tictactoe.model.TurnResponse;
import com.kata.tictactoe.service.GameService;
import com.kata.tictactoe.service.PlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
public class TicTacToeController {

    @Autowired
    GameService gameService;

    @Autowired
    PlayersService playersService;


    @GetMapping("/players")
    public List<Player> getPlayers() {
        return playersService.getPlayers();
    }

    @GetMapping("/state")
    public Map<String, String> getState() {
        return gameService.getBoard();
    }

    @DeleteMapping("/clearboard")
    public Map<String, String> clearStateBoard() {
        return gameService.doResetTheBoard();
    }
    @PostMapping("/turn")
    public TurnResponse turn(@RequestBody TurnRequest request) {

        if (gameService.checkXMakesFirstMove()) {
            if (!request.getPlayerId().equals("X")) {

                throw new PlayerXShouldMoveFirstException("Player X Should Make First Move");

            }
        }
        String player = gameService.checkWhoIsGettingNextTurn();
        if (player.equals("X") && request.getPlayerId().equals("O")) {
            throw new PlayerWrongTurnException("O Should Make Turn Now Not X");
        }
        if (player.equals("O") && request.getPlayerId().equals("X")) {
            throw new PlayerWrongTurnException("X Should Make Turn Now Not O");
        }
        if (gameService.isGameOver()) {
            System.out.println("Game is already over!");
        } else {
            gameService.updateState(String.valueOf(request.getPosition()), playersService.getPlayer(request.getPlayerId()));
        }
        return new TurnResponse(gameService.findWinner(playersService), gameService.board);
    }


}
