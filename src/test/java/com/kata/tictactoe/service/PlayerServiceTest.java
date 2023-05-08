package com.kata.tictactoe.service;

import com.kata.tictactoe.exceptions.PlayerNotFoundException;
import com.kata.tictactoe.model.Player;
import com.kata.tictactoe.service.PlayersService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PlayerServiceTest {

    PlayersService playersService;

    @BeforeEach
    void setup() {
        playersService = new PlayersService();

    }

    @Test
    public void create_players_service() {

        Assertions.assertNotNull(playersService);

    }
    @Test
    public void create_player() {

        Player player = new Player("X","Player 1");

        Assertions.assertNotNull(playersService);

    }
    @Test
    public void player_position_check() {

        Player player = new Player("X","Player 1");

        Assertions.assertEquals("Player 1",player.getPlayerName());

    }
    @Test
    public void get_list_of_players(){
        List<Player> listOfPlayers = playersService.getPlayers();
        Assertions.assertEquals(2,listOfPlayers.size());
    }
    @Test
    public void get_specific_player(){
        Player player = playersService.getPlayer("X");
        Assertions.assertNotNull(player);
        Assertions.assertEquals("X",player.getPlayerId());
    }

    @Test
    public void player_not_found_for_unknow_player(){

        PlayerNotFoundException playerNotFoundException = Assertions.assertThrows(PlayerNotFoundException.class,()->playersService.getPlayer("Z"));
        Assertions.assertEquals("Player Z not found",playerNotFoundException.getMessage());
    }

}
