package com.pkuvr.game_server.exception;

/**
 * 钻石、黄金、石材、油料等 其中之一不足
 *
 * @author ZY
 */
public class NotEnoughStoneException extends Exception {
    private static final long serialVersionUID = 1L;

    public NotEnoughStoneException() {
        super();
    }

    public NotEnoughStoneException(String message) {
        super(message);
    }

    public NotEnoughStoneException(Throwable cause) {
        super(cause);
    }

    public NotEnoughStoneException(String message, Throwable cause) {
        super(message, cause);
    }
}
