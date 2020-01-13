package com.asto.a91recyclingtreasurepad.bean;

import java.io.Serializable;

/**
 * Created by zj on 2018/7/4.
 * is use for: 登录后获取用户信息
 */

public class UserInfoBean implements Serializable{
        /**
         * username : kangxianyue
         * weixinid : osEPF0Q9X4ahHeU7T9nJBf1Ae7RA
         * gmt_modified : 2018-07-04 17:02:33
         * utype : 1
         * wxtixin : 1
         * selfid : 9f35ab9bd308befd
         * company_id : 46
         * clientid : null
         * sex : 男
         * mobile : 13666651058
         * pwd : 965eb72c92a549dd
         * contact : 阿思拓
         * gmt_created : 2017-09-22 14:03:23
         * isdel : 0
         * group_id : 4
         * id : 41
         * bz :
         */

    private String username;
    private String weixinid;
    private String gmt_modified;
    private String utype;
    private Integer wxtixin;
    private String selfid;
    private Integer company_id;
    private String clientid;
    private String sex;
    private String mobile;
    private String pwd;
    private String contact;
    private String gmt_created;
    private Integer isdel;
    private Integer group_id;
    private Integer id;
    private String bz;
    private String eqtype; //地磅的类型，1：大磅；2：小磅
    /**
     * 海康摄像头参数
     * ip，端口号，用户名，密码
     */
    private String hikvision_IP;
    private Integer hikvision_port;
    private String hikvision_user;
    private String hikvision_pw;
    private Integer socket; //socket端口号

    public String getEqtype() {
        return eqtype;
    }

    public void setEqtype(String eqtype) {
        this.eqtype = eqtype;
    }

    public String getHikvision_IP() {
        return hikvision_IP;
    }

    public void setHikvision_IP(String hikvision_IP) {
        this.hikvision_IP = hikvision_IP;
    }

    public Integer getHikvision_port() {
        return hikvision_port;
    }

    public void setHikvision_port(Integer hikvision_port) {
        this.hikvision_port = hikvision_port;
    }

    public String getHikvision_user() {
        return hikvision_user;
    }

    public void setHikvision_user(String hikvision_user) {
        this.hikvision_user = hikvision_user;
    }

    public String getHikvision_pw() {
        return hikvision_pw;
    }

    public void setHikvision_pw(String hikvision_pw) {
        this.hikvision_pw = hikvision_pw;
    }

    public Integer getSocket() {
        return socket;
    }

    public void setSocket(Integer socket) {
        this.socket = socket;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWeixinid() {
        return weixinid;
    }

    public void setWeixinid(String weixinid) {
        this.weixinid = weixinid;
    }

    public String getGmt_modified() {
        return gmt_modified;
    }

    public void setGmt_modified(String gmt_modified) {
        this.gmt_modified = gmt_modified;
    }

    public String getUtype() {
        return utype;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }

    public Integer getWxtixin() {
        return wxtixin;
    }

    public void setWxtixin(Integer wxtixin) {
        this.wxtixin = wxtixin;
    }

    public String getSelfid() {
        return selfid;
    }

    public void setSelfid(String selfid) {
        this.selfid = selfid;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getGmt_created() {
        return gmt_created;
    }

    public void setGmt_created(String gmt_created) {
        this.gmt_created = gmt_created;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }
}
