package com.pkuvr.game_server.exception;

/**
 * @author ZY
 */
public class MineUnderAttackedException extends Exception {
    private static final long serialVersionUID = 1L;

    public MineUnderAttackedException() {
    }

    public MineUnderAttackedException(String message) {
        super(message);
    }

    public MineUnderAttackedException(Throwable cause) {
        super(cause);
    }

    public MineUnderAttackedException(String message, Throwable cause) {
        super(message, cause);
    }
}