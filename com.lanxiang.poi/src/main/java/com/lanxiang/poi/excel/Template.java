package com.lanxiang.poi.excel;

/**
 * Created by lanxiang on 2017/2/14.
 */
public class Template {

    private String comment;

    private String outPutPath;

    private Integer totalRow;

    private String name;

    private String model;

    private Integer quantity;

    private String unit;

    private Integer needReturn;

    public Template(){

    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getOutPutPath() {
        return outPutPath;
    }

    public void setOutPutPath(String outPutPath) {
        this.outPutPath = outPutPath;
    }

    public Integer getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(Integer totalRow) {
        this.totalRow = totalRow;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getNeedReturn() {
        return needReturn;
    }

    public void setNeedReturn(Integer needReturn) {
        this.needReturn = needReturn;
    }
}

