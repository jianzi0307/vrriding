package com.pkuvr.game_server.exception;

/**
 * 玩家好友不在线
 *
 * @author ZY
 */
public class BattleResultErrorException extends Exception {
    private static final long serialVersionUID = 1L;

    public BattleResultErrorException() {
    }

    public BattleResultErrorException(String message) {
        super(message);
    }

    public BattleResultErrorException(Throwable cause) {
        super(cause);
    }

    public BattleResultErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}