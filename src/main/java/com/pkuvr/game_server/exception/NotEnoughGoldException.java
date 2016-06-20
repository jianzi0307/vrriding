package com.pkuvr.game_server.exception;

/**
 * 钻石、黄金、石材、油料等 其中之一不足
 *
 * @author ZY
 */
public class NotEnoughGoldException extends Exception {
    private static final long serialVersionUID = 1L;

    public NotEnoughGoldException() {
        super();
    }

    public NotEnoughGoldException(String message) {
        super(message);
    }

    public NotEnoughGoldException(Throwable cause) {
        super(cause);
    }

    public NotEnoughGoldException(String message, Throwable cause) {
        super(message, cause);
    }
}
