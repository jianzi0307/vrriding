package com.pkuvr.game_server.exception;

/**
 * 玩家好友不在线
 *
 * @author ZY
 */
public class PlayerTonNotEnoughException extends Exception {
    private static final long serialVersionUID = 1L;

    public PlayerTonNotEnoughException() {
    }

    public PlayerTonNotEnoughException(String message) {
        super(message);
    }

    public PlayerTonNotEnoughException(Throwable cause) {
        super(cause);
    }

    public PlayerTonNotEnoughException(String message, Throwable cause) {
        super(message, cause);
    }
}