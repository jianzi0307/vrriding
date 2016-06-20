package com.pkuvr.game_server.exception;

/**
 * <p>修改人：</p>
 * <p>修改时间：</p>
 * <p>修改描述：</p>
 */
public class PvpOtherNotOnlineException extends Exception {
    private static final long serialVersionUID = 1L;

    public PvpOtherNotOnlineException() {
    }

    public PvpOtherNotOnlineException(String message) {
        super(message);
    }

    public PvpOtherNotOnlineException(Throwable cause) {
        super(cause);
    }

    public PvpOtherNotOnlineException(String message, Throwable cause) {
        super(message, cause);
    }
}
