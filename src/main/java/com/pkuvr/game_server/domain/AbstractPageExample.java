package com.pkuvr.game_server.domain;

/**
 * <p>类说明:</p>
 * <p>文件名： AbstractPageDomain.java</p>
 * <p>创建人及时间：	宋士龙 2012-6-15</p>
 * <p>
 * <p>修改人：</p>
 * <p>修改时间：</p>
 * <p>修改描述：</p>
 **/
public abstract class AbstractPageExample {
    private boolean isLimit;
    private int offset;
    private int rowCount;

    public boolean isLimit() {
        return isLimit;
    }

    public void setLimit(boolean isLimit) {
        this.isLimit = isLimit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }
}
