/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yxtec.pmiside.domain.export.ananlysis;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author 杰
 */
public class PMISMonthDataAnanlysiser implements IPMISDataAnanlysiser {

    @Override
    public List<RecordPeer> ananlysisData(String source) throws Exception {
        List<RecordPeer> rps = new ArrayList<RecordPeer>();
        JSONObject jObj = new JSONObject(source);
        JSONArray rows = jObj.getJSONArray("rows");
        for (int i = rows.length() - 1; i >= 0; i--) {
            JSONObject row = (JSONObject) rows.get(i);
            RecordPeer rp = new RecordPeer(row.getString("LOGTIME"), row.getString("CONFIRGCOSTTIME"), row.getString("DAYCONTEXT"));
            rps.add(rp);
        }
        return rps;
    }
}
