package com.pkuvr.game_server.exception;

/**
 * 玩家好友不在线
 *
 * @author ZY
 */
public class CanNotCollectResourceException extends Exception {
    private static final long serialVersionUID = 1L;

    public CanNotCollectResourceException() {
    }

    public CanNotCollectResourceException(String message) {
        super(message);
    }

    public CanNotCollectResourceException(Throwable cause) {
        super(cause);
    }

    public CanNotCollectResourceException(String message, Throwable cause) {
        super(message, cause);
    }
}