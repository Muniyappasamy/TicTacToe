package com.kata.tictactoe.service;

import com.kata.tictactoe.model.Player;
import org.junit.jupiter.api.Assertions;
import com.kata.tictactoe.exceptions.InvalidChoiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {
    GameService gameService;

    @BeforeEach
    void setup() {
        gameService = new GameService();

    }

    @Test
    public void create_players_service() {

        Assertions.assertNotNull(gameService);

    }
    @Test
    public void update_state_checkX() {
        Player player = new Player("X","Player 1");
        gameService.updateState("0",player);
        Assertions.assertEquals("X",gameService.board.get("0"));

    }
    @Test
    public void update_state_checkY() {
        Player player = new Player("Y","Player 1");
        gameService.updateState("1",player);
        Assertions.assertEquals("Y",gameService.board.get("1"));

    }


    @Test
    void isGameOver() {
        gameService.gameOver = false;
        Assertions.assertEquals(false,gameService.isGameOver());
    }

    @Test
    void endGame() {
        boolean endGame = false;
        gameService.endGame(endGame);
        Assertions.assertEquals(false,gameService.isGameOver());
    }


    @Test
    void checkWinner_O() {
        Player player1 = new Player("O","Player 2");
        gameService.updateState("0",player1);
        Player player2 = new Player("O","Player 2");
        gameService.updateState("1",player2);
        Player player3 = new Player("O","Player 2");
        gameService.updateState("2",player3);

        String result = gameService.checkWinnerState();

        Assertions.assertEquals("Y",result);
    }
    @Test
    void checkWinner_x() {
        Player player1 = new Player("X","Player 1");
        gameService.updateState("2",player1);
        Player player2 = new Player("X","Player 1");
        gameService.updateState("5",player2);
        Player player3 = new Player("X","Player 1");
        gameService.updateState("8",player3);
        String result = gameService.checkWinnerState();

        Assertions.assertEquals("X",result);
    }
    @Test
    void update_invalid_input_check(){
        Player player= new Player("X","Player 1");
        gameService.updateState("1",player);
        InvalidChoiceException invalidChoiceException = Assertions.assertThrows(InvalidChoiceException.class,()-> gameService.updateState("1",player));
        Assertions.assertEquals("The position has already been taken",invalidChoiceException.getMessage());
    }
    @Test
    void findwinner_test() {
        Player player1 = new Player("X","Player 1");
        gameService.updateState("2",player1);
        Player player2 = new Player("X","Player 1");
        gameService.updateState("5",player2);
        Player player3 = new Player("X","Player 1");
        gameService.updateState("8",player3);
        PlayersService playersService = new PlayersService();
        Player result = gameService.findWinner(playersService);

        Assertions.assertEquals("X",result.getPlayerId());
    }
}