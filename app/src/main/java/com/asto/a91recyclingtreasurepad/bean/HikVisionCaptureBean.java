package com.asto.a91recyclingtreasurepad.bean;

import java.io.Serializable;

/**
 * Created by zj on 2018/10/18.
 * is use for: 海康通道号等参数
 */
public class HikVisionCaptureBean implements Serializable{
    //存储使用海康其他参数
    private Integer logID;
    private Integer m_iStartChan;
    private Integer m_iChanNum;

    public HikVisionCaptureBean(int logID, int m_iStartChan, int m_iChanNum) {
        this.logID = logID;
        this.m_iStartChan = m_iStartChan;
        this.m_iChanNum = m_iChanNum;
    }

    public Integer getLogID() {
        return logID;
    }

    public void setLogID(Integer logID) {
        this.logID = logID;
    }

    public Integer getM_iStartChan() {
        return m_iStartChan;
    }

    public void setM_iStartChan(Integer m_iStartChan) {
        this.m_iStartChan = m_iStartChan;
    }

    public Integer getM_iChanNum() {
        return m_iChanNum;
    }

    public void setM_iChanNum(Integer m_iChanNum) {
        this.m_iChanNum = m_iChanNum;
    }
}
