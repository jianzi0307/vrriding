package com.pkuvr.game_server.exception;

/**
 * @author ZY
 */
public class MineBlockHouseErrorException extends Exception {
    private static final long serialVersionUID = 1L;

    public MineBlockHouseErrorException() {
    }

    public MineBlockHouseErrorException(String message) {
        super(message);
    }

    public MineBlockHouseErrorException(Throwable cause) {
        super(cause);
    }

    public MineBlockHouseErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}