/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yxtec.pmiside.domain.export.ananlysis;

import java.util.List;

/**
 *
 * @author 杰
 */
public interface IPMISDataAnanlysiser {
    public List<RecordPeer> ananlysisData(String source) throws Exception;
}
