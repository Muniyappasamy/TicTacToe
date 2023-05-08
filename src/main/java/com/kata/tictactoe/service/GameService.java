package com.kata.tictactoe.service;

import com.kata.tictactoe.exceptions.InvalidChoiceException;
import com.kata.tictactoe.model.Player;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class GameService {

    private static final int BOARD_NUMBER = 3;
    public Boolean gameOver;
    public Map<String, String> board;

    public Map<Integer, List<Integer>> winningPossibilityMap;

    public GameService() {
        this.board = new HashMap<>();
        this.board.put("1", null);
        this.board.put("2", null);
        this.board.put("3", null);
        this.board.put("4", null);
        this.board.put("5", null);
        this.board.put("6", null);
        this.board.put("7", null);
        this.board.put("8", null);
        this.board.put("9", null);
        this.gameOver = false;

        this.winningPossibilityMap = new HashMap<>();
        this.winningPossibilityMap.put(1, Arrays.asList(1, 2, 3));
        this.winningPossibilityMap.put(2, Arrays.asList(4, 5, 6));
        this.winningPossibilityMap.put(3, Arrays.asList(7, 8, 9));
        this.winningPossibilityMap.put(4, Arrays.asList(1, 4, 7));
        this.winningPossibilityMap.put(5, Arrays.asList(2, 5, 8));
        this.winningPossibilityMap.put(6, Arrays.asList(3, 6, 9));
        this.winningPossibilityMap.put(7, Arrays.asList(1, 5, 9));
        this.winningPossibilityMap.put(8, Arrays.asList(3, 5, 7));



    }

    public Map<String, String> clearBoard() {

        Map<String,String> resetBoard = new HashMap<>();
        resetBoard.put("1", null);
        resetBoard.put("2", null);
        resetBoard.put("3", null);
        resetBoard.put("4", null);
        resetBoard.put("5", null);
        resetBoard.put("6", null);
        resetBoard.put("7", null);
        resetBoard.put("8", null);
        resetBoard.put("9", null);

        board = resetBoard;
        return board;
    }


    public Map<String, String> getBoard() {
        return board;
    }

    public Boolean isGameOver() {
        return gameOver;
    }

    public void endGame(Boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void updateState(String position, Player player) {
        if (board.get(position) != null) {
            throw new InvalidChoiceException("The position has already been taken");
        }
        board.put(position, player.getPlayerId());
    }

    public String checkWinner_3_3_State() {

        for (List<Integer> values : winningPossibilityMap.values()) {

            StringBuffer totalCount= new StringBuffer();;
            for (int value : values) {
                if (board.get(String.valueOf(value)) != null  && board.get(String.valueOf(value)).equals("X")) {
                    totalCount.append(board.get(String.valueOf(value)));
                }
                if (board.get(String.valueOf(value)) != null  && board.get(String.valueOf(value)).equals("O")) {
                    totalCount.append(board.get(String.valueOf(value)));
                }
            }

            if (totalCount!= null && totalCount.equals("XXX")) {
                return "X";
            }
            if (totalCount!= null && totalCount.equals("OOO")) {
                return "O";
            }
        }
        if (board.values().stream().filter(Objects::nonNull).collect(Collectors.toList()).size() == 8) {
            return "draw";
        }
        return "InProgress";
    }

    public String checkWinner_N_N_State() {
        Optional<String> result;
        result = rowWiseCheckWinnerState();
        if (result.isPresent()) {
            return result.get();
        }
        result = columnWiseCheckWinnerState();
        if (result.isPresent()) {
            return result.get();
        }
        result = rightTopToLeftBottomDiagonalWinnerState();
        if (result.isPresent()) {
            return result.get();
        }
        result = leftTopToRightBottomDiagonalWinnerState();
        if (result.isPresent()) {
            return result.get();
        }
        if (board.values().stream().filter(Objects::nonNull).collect(Collectors.toList()).size() == 8) {
            return "draw";
        }
        return "InProgress";
    }

    public Optional<String> rowWiseCheckWinnerState() {

        for (int count = 1, previous = BOARD_NUMBER; count <= BOARD_NUMBER * BOARD_NUMBER; previous = count, count = count + BOARD_NUMBER) {
            String result = null;
            for (int rowCount = count; rowCount < count + previous; rowCount++) {

                result += board.get(String.valueOf(rowCount));

            }
            if (result.equals("XXX")) {
                return Optional.of("X");
            } else if (result.equals("OOO")) {
                return Optional.of("O");
            }
        }
        return Optional.empty();
    }

    public Optional<String> columnWiseCheckWinnerState() {

        for (int count = 1, constCounter = 1; count <= BOARD_NUMBER; count++) {
            String result = null;
            for (int colCount = count; constCounter <= BOARD_NUMBER; colCount = colCount + BOARD_NUMBER) {

                result += board.get(String.valueOf(colCount));

            }
            if (result.equals("XXX")) {
                return Optional.of("X");
            } else if (result.equals("OOO")) {
                return Optional.of("O");
            }
        }
        return Optional.empty();
    }

    public Optional<String> leftTopToRightBottomDiagonalWinnerState() {


        String result = null;
        for (int rowCount = 1, tempCounter = 1; tempCounter <= BOARD_NUMBER; rowCount = (rowCount + BOARD_NUMBER + 1)) {

            result += board.get(String.valueOf(rowCount));

        }
        if (result.equals("XXX")) {
            return Optional.of("X");
        } else if (result.equals("OOO")) {
            return Optional.of("O");
        }

        return Optional.empty();
    }

    public Optional<String> rightTopToLeftBottomDiagonalWinnerState() {


        String result = null;
        for (int rowCount = BOARD_NUMBER, tempCounter = 1; tempCounter <= BOARD_NUMBER; rowCount = (rowCount + BOARD_NUMBER - 1)) {

            result += board.get(String.valueOf(rowCount));

        }
        if (result.equals("XXX")) {
            return Optional.of("X");
        } else if (result.equals("OOO")) {
            return Optional.of("O");
        }

        return Optional.empty();
    }


    public Player findWinner(PlayersService playersService) {
        String winner = this.checkWinner_3_3_State();
        Player playerWinner = null;

        if (!winner.equals("InProgress")) {
            if (winner.equals("X") || winner.equals("O")) {

                playerWinner = playersService.getPlayer(winner);
                this.endGame(true);
            } else if (winner.equals("draw")) {
                playerWinner = new Player("draw", "No one wins");
                this.endGame(true);
            } else {
                playerWinner = new Player("InProgress", "No one wins Yet");
                this.endGame(false);
            }

        }
        return playerWinner;

    }

    public Boolean checkXMakesFirstMove() {
        return board.values().stream().allMatch(Objects::isNull);

    }

    public Map<String,String> doResetTheBoard() {
         board.replaceAll((k, v) -> null);
         return board;
    }

    public String checkWhoIsGettingNextTurn() {

        int totalX=0;
        int totalO=0;

        for (String value : board.values()) {

            if(value != null && value.equals("X")){

                totalX ++ ;

            }
            else  if(value != null && value.equals("O")){

                totalO ++ ;

            }

        }
        return totalX>totalO?"O":"X";

    }

}
