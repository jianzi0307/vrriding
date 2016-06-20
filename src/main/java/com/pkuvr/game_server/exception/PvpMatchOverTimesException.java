package com.pkuvr.game_server.exception;

/**
 * <p>修改人：</p>
 * <p>修改时间：</p>
 * <p>修改描述：</p>
 */
public class PvpMatchOverTimesException extends Exception {
    private static final long serialVersionUID = 1L;

    public PvpMatchOverTimesException() {
    }

    public PvpMatchOverTimesException(String message) {
        super(message);
    }

    public PvpMatchOverTimesException(Throwable cause) {
        super(cause);
    }

    public PvpMatchOverTimesException(String message, Throwable cause) {
        super(message, cause);
    }
}
