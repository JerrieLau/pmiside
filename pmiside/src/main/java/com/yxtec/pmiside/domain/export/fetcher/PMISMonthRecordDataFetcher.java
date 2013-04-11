/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yxtec.pmiside.domain.export.fetcher;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.http.HttpResponse;

import com.yxtec.pmiside.domain.export.communication.HTTPRequest;
import com.yxtec.pmiside.domain.export.communication.ICommunication;

/**
 * 
 * @author 杰
 */
public class PMISMonthRecordDataFetcher implements IPMISDataFetcher {

	private ICommunication communication;
	
	private String checkedStatus;
	
	private String checkedLocation;
	
	public String getCheckedStatus() {
		return checkedStatus;
	}

	public void setCheckedStatus(String checkedStatus) {
		this.checkedStatus = checkedStatus;
	}

	public String getCheckedLocation() {
		return checkedLocation;
	}

	public void setCheckedLocation(String checkedLocation) {
		this.checkedLocation = checkedLocation;
	}

	public ICommunication getCommunication() {
		return communication;
	}

	public void setCommunication(ICommunication communication) {
		this.communication = communication;
	}

	public HTTPRequest getIndexReq() {
		return indexReq;
	}

	public void setIndexReq(HTTPRequest indexReq) {
		this.indexReq = indexReq;
	}

	public HTTPRequest getLoginReq() {
		return loginReq;
	}

	public void setLoginReq(HTTPRequest loginReq) {
		this.loginReq = loginReq;
	}

	public HTTPRequest getQueryReq() {
		return queryReq;
	}

	public void setQueryReq(HTTPRequest queryReq) {
		this.queryReq = queryReq;
	}

	private HTTPRequest indexReq;
	private HTTPRequest loginReq;
	private HTTPRequest queryReq;

	@Override
	public String fetchPMISData(IFetchInput in) throws Exception {
		FetchMonthRecordInput fmri = (FetchMonthRecordInput) in;

		// Request Index.
		index();

		// Login.
		login(fmri);
		
		return query(fmri);
	}

	private void login(FetchMonthRecordInput fmri) throws Exception {
		// Login.
		loginReq.setContent("j_username=" + fmri.username + "&j_password=" + fmri.password);
		HttpResponse rsp = communication.sendRequest(loginReq);
		String location = rsp.getFirstHeader("Location").getValue();
		if(!(String.valueOf(rsp.getStatusLine().getStatusCode()).equals(checkedStatus) && location.equals(checkedLocation))) {
			throw new Exception("PMIS登录失败，请检查输入的用户名及密码！");
		}
	}

	private void index() throws Exception {
		// Request Index.
		communication.sendRequest(indexReq);
	}

	private String query(FetchMonthRecordInput fmri) throws Exception {
		// Query Data
		Calendar calendar = Calendar.getInstance();
		calendar.set(fmri.year, fmri.month - 1, 1);
		calendar.get(Calendar.YEAR);
		calendar.get(Calendar.MONTH);
		calendar.get(Calendar.DAY_OF_MONTH);
		Date minDate = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String start = sdf.format(minDate);
		calendar.set(fmri.year, fmri.month - 1, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.get(Calendar.YEAR);
		calendar.get(Calendar.MONTH);
		calendar.get(Calendar.DAY_OF_MONTH);
		Date maxDate = calendar.getTime();
		String end = sdf.format(maxDate);
		queryReq.setContent("beginLogTime=" + start + "&endLogTime=" + end + "&page=1&rows=31");
		communication.sendRequest(queryReq);
		return communication.getContent();
	}
}
