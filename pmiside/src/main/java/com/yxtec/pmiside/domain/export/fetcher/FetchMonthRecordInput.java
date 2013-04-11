/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yxtec.pmiside.domain.export.fetcher;

/**
 *
 * @author 杰
 */
public class FetchMonthRecordInput implements IFetchInput{
    
    protected String username;
    protected String password;
    protected int month;
    protected int year;
    
    public FetchMonthRecordInput(String username, String password, int month, int year){
        this.username = username;
        this.password = password;
        this.month = month;
        this.year = year;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }
    
}
