/**
 * 
 */
package com.yxtec.pmiside.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yxtec.pmiside.dao.IPMISExportUserMessageDaoJpa;
import com.yxtec.pmiside.domain.export.ananlysis.IPMISDataAnanlysiser;
import com.yxtec.pmiside.domain.export.ananlysis.RecordPeer;
import com.yxtec.pmiside.domain.export.fetcher.FetchMonthRecordInput;
import com.yxtec.pmiside.domain.export.fetcher.IFetchInput;
import com.yxtec.pmiside.domain.export.fetcher.IPMISDataFetcher;
import com.yxtec.pmiside.domain.export.reportor.IReportCreater;
import com.yxtec.pmiside.mode.PMISExportUserMessage;
import com.yxtec.pmiside.security.SecurityUtilities;

/**
 * @author Jerrie
 * 
 */
@Service("exportService")
@Scope("prototype")
public class ExportServiceImpl implements IExportService {

	@Autowired
	private IPMISExportUserMessageDaoJpa dao;

	@Resource(name = "pmisdf")
	private IPMISDataFetcher dataFetcher;

	@Resource(name = "pmisda")
	private IPMISDataAnanlysiser ananlysiser;

	@Resource(name = "rc")
	private IReportCreater reportCreater;
	
	private String basepath;
	
	@Resource(name="securityUtilities")
	private SecurityUtilities su;

	@Override
	public String exportMonthWorkReport(PMISExportUserMessage pum) throws Exception {
		IFetchInput fi = new FetchMonthRecordInput(pum.getPmisUserid(), pum.getPmisPassword(), pum.getMonth(), pum.getYear());
		String source = dataFetcher.fetchPMISData(fi);
		List<RecordPeer> rps = ananlysiser.ananlysisData(source);
		Map<String, String> headMsg = new HashMap<String, String>();
		headMsg.put("userName", pum.getPmisName());
		headMsg.put("userId", pum.getPmisUserid());
		headMsg.put("projectName", pum.getPmisProjectName());
		headMsg.put("projectRole", pum.getPmisProjectRole());
		headMsg.put("monthYear", pum.getYear() + "-" + pum.getMonth());
		headMsg.put("place", pum.getPmisOfficePlace());
		headMsg.put("supplier", pum.getSupplier());
		reportCreater.setBasepath(basepath);
		return reportCreater.createReport(headMsg, rps);
	}

	@Override
	@Transactional
	public void savePMISExportUserMessage(PMISExportUserMessage pum) {
		dao.save(pum);
	}

	@Override
	public PMISExportUserMessage findPMISExportUserMessageByuserID(String userid) {
		return dao.findPMISExportUserMessageByuserID(userid);
	}

	@Override
	public void setBasepath(String path) {
		this.basepath = path;
	}
}
