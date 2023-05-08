package com.kata.tictactoe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PlayerWrongTurnException extends RuntimeException {
    public PlayerWrongTurnException(String message) {
        super(message);
    }
}
