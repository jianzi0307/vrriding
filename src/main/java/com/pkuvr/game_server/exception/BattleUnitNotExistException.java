package com.pkuvr.game_server.exception;

/**
 * 玩家好友不在线
 *
 * @author ZY
 */
public class BattleUnitNotExistException extends Exception {
    private static final long serialVersionUID = 1L;

    public BattleUnitNotExistException() {
    }

    public BattleUnitNotExistException(String message) {
        super(message);
    }

    public BattleUnitNotExistException(Throwable cause) {
        super(cause);
    }

    public BattleUnitNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}