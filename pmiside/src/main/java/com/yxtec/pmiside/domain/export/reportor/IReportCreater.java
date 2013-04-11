/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yxtec.pmiside.domain.export.reportor;

import java.util.List;
import java.util.Map;

import com.yxtec.pmiside.domain.export.ananlysis.RecordPeer;

/**
 *
 * @author 杰
 */
public interface IReportCreater {
    
    /**
     * 创建报表
     * @param headMsg
     * @param rps
     * @throws Exception
     */
    public String createReport(Map<String, String> headMsg, List<RecordPeer> rps) throws Exception;
    
    
    /**
     * 设置基准路径
     * @param base
     */
    public void setBasepath(String base);
}
