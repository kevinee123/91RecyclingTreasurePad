package com.asto.a91recyclingtreasurepad.bean;

import java.io.Serializable;

/**
 * Created by zj on 2018/9/3.
 * is use for: 过磅信息
 */
public class InsertStorageBean implements Serializable{
    private String card_number;//卡号
    private String sname;//供应商姓名
    private String collect_number;//编号
    private String gross_weight;//毛重
    private String created_at;//毛重时间
    private String tare_weight;//皮重
    private String collect_id;//单号id
    private String pure_weight;//净重
    private String pname;//品类
    private String price;//单价
    private String out_time;//皮重时间
    private String total_price;//总价
    private Integer tare_check;//过磅次数 0 : 第一次过重； 1 : 第二次过重
    private Integer pic_type;//上传图片类型1，为过毛重，2为过皮重
    private String point;//扣点
    private String clasp;//扣杂

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getClasp() {
        return clasp;
    }

    public void setClasp(String clasp) {
        this.clasp = clasp;
    }

    public Integer getPic_type() {
        return pic_type;
    }

    public void setPic_type(Integer pic_type) {
        this.pic_type = pic_type;
    }

    public String getCollect_id() {
        return collect_id;
    }

    public void setCollect_id(String collect_id) {
        this.collect_id = collect_id;
    }

    public String getPure_weight() {
        return pure_weight;
    }

    public void setPure_weight(String pure_weight) {
        this.pure_weight = pure_weight;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getCollect_number() {
        return collect_number;
    }

    public void setCollect_number(String collect_number) {
        this.collect_number = collect_number;
    }

    public String getGross_weight() {
        return gross_weight;
    }

    public void setGross_weight(String gross_weight) {
        this.gross_weight = gross_weight;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getTare_weight() {
        return tare_weight;
    }

    public void setTare_weight(String tare_weight) {
        this.tare_weight = tare_weight;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOut_time() {
        return out_time;
    }

    public void setOut_time(String out_time) {
        this.out_time = out_time;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public Integer getTare_check() {
        return tare_check;
    }

    public void setTare_check(Integer tare_check) {
        this.tare_check = tare_check;
    }
}
