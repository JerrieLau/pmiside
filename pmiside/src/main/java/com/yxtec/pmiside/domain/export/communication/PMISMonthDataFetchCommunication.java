/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yxtec.pmiside.domain.export.communication;

import java.nio.charset.Charset;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;


/**
 *
 * @author 杰
 */
public class PMISMonthDataFetchCommunication implements ICommunication {

    protected HttpClient httpclient = null;
    
    private String content;
    
    @Override
    public HttpResponse sendRequest(IRequest request) throws Exception{
        HTTPRequest httpReq = (HTTPRequest) request;
        HttpResponse httpRsp = null;

        HttpGet httpGet = null;
        HttpPost httpPost = null;
        //construct http request entity.
        if (httpReq.getMethod().equalsIgnoreCase("GET")) {
            httpGet = new HttpGet(httpReq.getUrl());
        } else {
            httpPost = new HttpPost(httpReq.getUrl());
            httpPost.setEntity(new StringEntity(httpReq.getContent(), Charset.forName("gbk")));
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        }

        // send http request, wrap response.
        try {
            httpRsp = httpclient.execute(httpGet == null ? httpPost : httpGet);
            content = EntityUtils.toString(httpRsp.getEntity());
        } finally {
            if (httpGet != null) {
                httpGet.releaseConnection();
            }
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }
        return httpRsp;
    }

    @Override
    public void shutdown() {
    	httpclient.getConnectionManager().shutdown();
    }

    /**
     * @return the httpclient
     */
    public HttpClient getHttpclient() {
        return httpclient;
    }

    /**
     * @param httpclient the httpclient to set
     */
    public void setHttpclient(HttpClient httpclient) {
        this.httpclient = httpclient;
    }

	@Override
	public String getContent() {
		return content;
	}
}
