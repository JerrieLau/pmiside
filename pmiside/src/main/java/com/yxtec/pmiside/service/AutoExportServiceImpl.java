/**
 * 
 */
package com.yxtec.pmiside.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.yxtec.pmiside.dao.IPMISExportUserMessageDaoJpa;
import com.yxtec.pmiside.domain.email.IMailSupporter;
import com.yxtec.pmiside.domain.email.Mail;
import com.yxtec.pmiside.domain.export.ananlysis.IPMISDataAnanlysiser;
import com.yxtec.pmiside.domain.export.ananlysis.RecordPeer;
import com.yxtec.pmiside.domain.export.fetcher.FetchMonthRecordInput;
import com.yxtec.pmiside.domain.export.fetcher.IFetchInput;
import com.yxtec.pmiside.domain.export.fetcher.IPMISDataFetcher;
import com.yxtec.pmiside.domain.export.reportor.IReportCreater;
import com.yxtec.pmiside.initial.InitializeServlet;
import com.yxtec.pmiside.mode.PMISExportUserMessage;
import com.yxtec.pmiside.security.SecurityUtilities;

/**
 * 自动导出服务
 * @author Jerrie
 * 
 */
@Component("autoExportService")
@Scope("prototype")
public class AutoExportServiceImpl implements AutoExportService {

	@Autowired
	private IPMISExportUserMessageDaoJpa dao;

	@Resource(name = "pmisdf")
	private IPMISDataFetcher dataFetcher;

	@Resource(name = "pmisda")
	private IPMISDataAnanlysiser ananlysiser;

	@Resource(name = "rc")
	private IReportCreater reportCreater;

	@Resource(name = "mailSupporter")
	private IMailSupporter mailSupporter;
	
	@Resource(name="securityUtilities")
	private SecurityUtilities su;

	@Override
	public void export() throws Exception {
		// 获取基准路径
		String basepath = InitializeServlet.getBasepath();
		if(basepath == null) {
			return;
		}

		// 读mainContent
		String mcPath = basepath + "/models/mailcontent.txt";
		String emcPath = basepath + "/models/errormailcontent.txt";
		String mailContent = readTxtFile(mcPath);
		String errorMailContent = readTxtFile(emcPath);

		List<PMISExportUserMessage> subscribedMsgs = dao.findSubscribedPMISExportUserMessage();

		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"), new Locale("zh", "CN"));
		calendar.add(Calendar.MONTH, -1);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		String subject = year + "年" + month + "月" + "PMISIDE月工作记录自动导出通知！";
		String subjectFail = year + "年" + month + "月" + "PMISIDE月工作记录自动导出失败！！！";

		for (PMISExportUserMessage msg : subscribedMsgs) {
			// 导出文件
			String filepath = null;
			try {
				IFetchInput fi = new FetchMonthRecordInput(msg.getPmisUserid(), su.decrypt(msg.getPmisPassword(), msg.getUser().getUserId()), month, year);
				String source = dataFetcher.fetchPMISData(fi);
				List<RecordPeer> rps = ananlysiser.ananlysisData(source);
				Map<String, String> headMsg = new HashMap<String, String>();
				headMsg.put("userName", msg.getPmisName());
				headMsg.put("userId", msg.getPmisUserid());
				headMsg.put("projectName", msg.getPmisProjectName());
				headMsg.put("projectRole", msg.getPmisProjectRole());
				headMsg.put("monthYear", year + "-" + month);
				headMsg.put("place", msg.getPmisOfficePlace());
				headMsg.put("supplier", msg.getSupplier());
				reportCreater.setBasepath(basepath);
				String filename = reportCreater.createReport(headMsg, rps);
				filepath = basepath + "/output/" + filename;

				// 发送邮件
				Mail mail = new Mail();
				mail.setFrom(mailSupporter.getUsername());
				mail.setTo(msg.getPmisUserid() + "@aostaryh.com");
				mail.setSubject(subject);
				mail.setContent(mailContent.replace("${name}", msg.getPmisUserid())
						.replace("${year}", String.valueOf(year)).replace("${month}", String.valueOf(month)));
				mail.setAttachments(Arrays.asList(filepath));
				mailSupporter.sendMail(mail);
			} catch (Exception e) {
				LoggerFactory.getLogger(AutoExportServiceImpl.class).error("阅用户发送导出失败", e);
				// 向订阅用户发送导出失败邮件
				Mail mail = new Mail();
				mail.setFrom(mailSupporter.getUsername());
				mail.setTo(msg.getPmisUserid() + "@aostaryh.com");
				mail.setSubject(subjectFail);
				mail.setContent(errorMailContent.replace("${name}", msg.getPmisUserid())
						.replace("${year}", String.valueOf(year)).replace("${month}", String.valueOf(month)));
				mailSupporter.sendMail(mail);
			} finally {
				// 删除导出的临时文件
				if(filepath != null) {
					File exportedFile = new File(filepath);
					if(exportedFile.exists()) {
						exportedFile.delete();
					}
				}
			}
		}
	}

	
	private String readTxtFile(String filepath) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		try {
			FileReader fr = new FileReader(new File(filepath));
			br = new BufferedReader(fr);
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} finally {
			if(br != null) {
				br.close();
			}
		} 
		return sb.toString();
	}
}
