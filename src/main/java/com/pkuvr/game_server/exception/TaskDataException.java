package com.pkuvr.game_server.exception;

/**
 * <p>类说明:错误数据</p>
 * <p>文件名：TaskNotDailyException.java</p>
 * <p>创建人及时间：EvilHades</p>
 * <p>
 * <p>修改人：</p>
 * <p>修改时间：</p>
 * <p>修改描述：</p>
 */
public class TaskDataException extends Exception {
    private static final long serialVersionUID = 1L;

    public TaskDataException() {
    }

    public TaskDataException(String message) {
        super(message);
    }

    public TaskDataException(Throwable cause) {
        super(cause);
    }

    public TaskDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
