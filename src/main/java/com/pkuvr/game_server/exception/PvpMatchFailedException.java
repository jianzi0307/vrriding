package com.pkuvr.game_server.exception;

/**
 * <p>修改人：</p>
 * <p>修改时间：</p>
 * <p>修改描述：</p>
 */
public class PvpMatchFailedException extends Exception {
    private static final long serialVersionUID = 1L;

    public PvpMatchFailedException() {
    }

    public PvpMatchFailedException(String message) {
        super(message);
    }

    public PvpMatchFailedException(Throwable cause) {
        super(cause);
    }

    public PvpMatchFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
