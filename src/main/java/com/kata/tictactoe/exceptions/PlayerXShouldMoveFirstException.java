package com.kata.tictactoe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PlayerXShouldMoveFirstException extends RuntimeException {
    public PlayerXShouldMoveFirstException(String message) {
        super(message);
    }
}
