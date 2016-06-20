package com.pkuvr.game_server.exception;

/**
 * 玩家好友不在线
 *
 * @author ZY
 */
public class FriendNotOnlineException extends Exception {
    private static final long serialVersionUID = 1L;

    public FriendNotOnlineException() {
    }

    public FriendNotOnlineException(String message) {
        super(message);
    }

    public FriendNotOnlineException(Throwable cause) {
        super(cause);
    }

    public FriendNotOnlineException(String message, Throwable cause) {
        super(message, cause);
    }
}