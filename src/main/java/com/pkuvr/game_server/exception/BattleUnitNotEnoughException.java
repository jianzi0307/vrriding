package com.pkuvr.game_server.exception;

/**
 * <p>类说明:支付点为null</p>
 * <p>创建人及时间：	宋士龙 2012-8-2</p>
 * <p>
 * <p>修改人：</p>
 * <p>修改时间：</p>
 * <p>修改描述：</p>
 */
public class BattleUnitNotEnoughException extends Exception {
    private static final long serialVersionUID = 1L;

    public BattleUnitNotEnoughException() {
    }

    public BattleUnitNotEnoughException(String message) {
        super(message);
    }

    public BattleUnitNotEnoughException(Throwable cause) {
        super(cause);
    }

    public BattleUnitNotEnoughException(String message, Throwable cause) {
        super(message, cause);
    }
}
