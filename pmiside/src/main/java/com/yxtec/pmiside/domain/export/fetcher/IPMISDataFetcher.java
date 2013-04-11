/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yxtec.pmiside.domain.export.fetcher;

/**
 *
 * @author 杰
 */
public interface IPMISDataFetcher {
    
    /**
     *
     * @param in
     * @return
     * @throws Exception
     */
    public String fetchPMISData(IFetchInput in) throws Exception;
}
