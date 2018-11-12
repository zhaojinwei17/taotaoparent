package com.taotao.pojo;

import java.io.Serializable;
import java.util.List;

public class EasyUIDataGridResult implements Serializable {
    private long total;
    private List rows;
    public long getTotal() {
        return total;
    }
    public void setTotal(long total) {
        this.total = total;
    }
    public List getRows() {
        return rows;
    }

    public void setRows(List row) {
        this.rows = row;
    }

    @Override
    public String toString() {
        return "EasyUIDataGridResult{" +
                "total=" + total +
                ", row=" + rows +
                '}';
    }
}