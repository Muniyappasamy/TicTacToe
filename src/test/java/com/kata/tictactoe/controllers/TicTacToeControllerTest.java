package com.kata.tictactoe.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kata.tictactoe.model.TurnRequest;
import com.kata.tictactoe.model.Player;
import com.kata.tictactoe.service.GameService;
import com.kata.tictactoe.service.PlayersService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

@WebMvcTest
class TicTacToeControllerTest {

    @MockBean
    GameService gameService;

    @MockBean
    PlayersService playersService;

    @Autowired
    MockMvc mockMvc;
    @Test
    public void get_all_players() throws Exception {
     List<Player> players;
        players = new ArrayList<>(2);
        players.add(new Player("X", "Player 1"));
        players.add(new Player("O", "Player 2"));
        Mockito.when(playersService.getPlayers()).thenReturn(players);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/players");

        MvcResult result = mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assertions.assertEquals(true,result.getResponse().getContentAsString().contains("X"));
        Assertions.assertEquals(true,result.getResponse().getContentAsString().contains("O"));

    }

    @Test
    public void get_mapping_state() throws Exception {
       Map board = new HashMap<>();
        board.put("0", null);
        board.put("1", null);
        board.put("2", null);
        board.put("3", null);
        board.put("4", null);
        board.put("5", null);
        board.put("6", null);
        board.put("7", null);
        board.put("8", null);
        board.put("9", null);

        Mockito.when(gameService.getBoard()).thenReturn(board);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/state");

        MvcResult result = mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assertions.assertEquals(true,result.getResponse().getContentAsString().contains("0"));

    }

    @Test
    public void test_play_turn_api() throws Exception {

        TurnRequest turnRequest = new TurnRequest("X",0);

        Mockito.when(gameService.findWinner(playersService)).thenReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/turn").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(turnRequest));

        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());

    }

}