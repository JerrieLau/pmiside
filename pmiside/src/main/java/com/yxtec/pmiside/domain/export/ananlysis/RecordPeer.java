/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yxtec.pmiside.domain.export.ananlysis;

/**
 *
 * @author 杰
 */
public class RecordPeer {
    
    private String content;
    private String date;
    private String interval;

    public RecordPeer(String date, String interval, String content){
        this.content = content;
        this.date = date;
        this.interval = interval;
    }
    
    
    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @return the interval
     */
    public String getInterval() {
        return interval;
    }

    /**
     * @param content the content to set
     */
    protected void setContent(String content) {
        this.content = content;
    }

    /**
     * @param date the date to set
     */
    protected void setDate(String date) {
        this.date = date;
    }

    /**
     * @param interval the interval to set
     */
    protected void setInterval(String interval) {
        this.interval = interval;
    }
}
