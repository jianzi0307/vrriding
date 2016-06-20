package com.pkuvr.game_server.exception;

/**
 * 玩家好友不在线
 *
 * @author ZY
 */
public class DamageBattleUnitTooLargeException extends Exception {
    private static final long serialVersionUID = 1L;

    public DamageBattleUnitTooLargeException() {
    }

    public DamageBattleUnitTooLargeException(String message) {
        super(message);
    }

    public DamageBattleUnitTooLargeException(Throwable cause) {
        super(cause);
    }

    public DamageBattleUnitTooLargeException(String message, Throwable cause) {
        super(message, cause);
    }
}