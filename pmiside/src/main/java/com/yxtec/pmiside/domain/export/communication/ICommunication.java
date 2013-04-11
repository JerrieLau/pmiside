/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yxtec.pmiside.domain.export.communication;

import org.apache.http.HttpResponse;


/**
 *
 * @author 杰
 */
public interface ICommunication {
    
    /**
     * 发送通讯请求
     * @param request
     * @return 
     */
    public HttpResponse sendRequest(IRequest request) throws Exception;
    
    /**
     * 获取响应内容
     * @return
     */
    public String getContent();    
    
    /**
     * 关闭连接
     */
    public void shutdown();
    
}
