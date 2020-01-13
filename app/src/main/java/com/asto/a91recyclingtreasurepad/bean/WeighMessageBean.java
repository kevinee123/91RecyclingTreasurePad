package com.asto.a91recyclingtreasurepad.bean;

import java.io.Serializable;

/**
 * Created by zj on 2018/7/5.
 * is use for:
 */

public class WeighMessageBean implements Serializable{
    /**
     * gw : 500.0
     * out_time : 2018-07-05 14:19:28
     * code : 1530770120
     * name : 临时供货商247
     * iccode : 2017512912512674
     * price : 1.0
     * selfid : 3537dfaad7d53891
     * tare : 14.0
     * tare_check : 1
     * pname : 书本纸
     * id : 9902
     * gmt_created : 2018-07-05 13:55:20
     */

    private Double gw;
    private String out_time;
    private String code;
    private String name;
    private String iccode;
    private Double price;
    private String selfid;
    private Double tare;
    private Double tare_check;
    private String pname;
    private Integer id;
    private String gmt_created;
    private Double nw;
    private Double total;

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getNw() {
        return nw;
    }

    public void setNw(Double nw) {
        this.nw = nw;
    }

    public Double getGw() {
        return gw;
    }

    public void setGw(Double gw) {
        this.gw = gw;
    }

    public String getOut_time() {
        return out_time;
    }

    public void setOut_time(String out_time) {
        this.out_time = out_time;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIccode() {
        return iccode;
    }

    public void setIccode(String iccode) {
        this.iccode = iccode;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSelfid() {
        return selfid;
    }

    public void setSelfid(String selfid) {
        this.selfid = selfid;
    }

    public Double getTare() {
        return tare;
    }

    public void setTare(Double tare) {
        this.tare = tare;
    }

    public Double getTare_check() {
        return tare_check;
    }

    public void setTare_check(Double tare_check) {
        this.tare_check = tare_check;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGmt_created() {
        return gmt_created;
    }

    public void setGmt_created(String gmt_created) {
        this.gmt_created = gmt_created;
    }
}
