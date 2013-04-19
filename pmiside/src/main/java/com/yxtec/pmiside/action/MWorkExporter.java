/**
 * 
 */
package com.yxtec.pmiside.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.yxtec.pmiside.mode.PMISExportUserMessage;
import com.yxtec.pmiside.mode.User;
import com.yxtec.pmiside.service.IExportService;
import com.yxtec.pmiside.service.IUserService;

/**
 * @author Jerrie
 * 
 */
@Component("mworkexportaction")
@Scope("prototype")
public class MWorkExporter extends ActionSupport {

	private static final long serialVersionUID = -5742990623606251131L;

	private transient IExportService exportService;

	private PMISExportUserMessage message;
	
	private transient IUserService userService;

	public IUserService getUserService() {
		return userService;
	}

	@Resource(name = "userService")
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IExportService getExportService() {
		return exportService;
	}

	@Resource(name="exportService")
	public void setExportService(IExportService exportService) {
		this.exportService = exportService;
	}

	public PMISExportUserMessage getMessage() {
		return message;
	}

	public void setMessage(PMISExportUserMessage message) {
		this.message = message;
	}
	
	public String preExport() {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		PMISExportUserMessage msg = exportService.findPMISExportUserMessageByuserID(user.getUserId());
		if(msg == null) {
			msg = new PMISExportUserMessage();
		} else {
			ServletActionContext.getRequest().setAttribute("saved", "true");
		}
		ServletActionContext.getRequest().setAttribute("msg", msg);
		return SUCCESS;
	}
	
	public String execute() throws Exception {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		message.setUser(user);
		
		HttpSession hs = ServletActionContext.getRequest().getSession();

		resetSavedSessionData(hs);
		
		String needSave = ServletActionContext.getRequest().getParameter("needsave");
		if(needSave != null && needSave.equals("true")) {
			exportService.savePMISExportUserMessage(message);
			hs.setAttribute("progress", 20);
		}
		exportService.setBasepath(ServletActionContext.getServletContext().getRealPath("expfolder"));
		
		
		//process year & month.
		HttpServletResponse rsp = ServletActionContext.getResponse();
		rsp.setStatus(200);
		rsp.setContentType("text/html");
		rsp.setCharacterEncoding("utf-8");
		rsp.getWriter().write("{\"result\":\"started\"}");
		rsp.getWriter().flush();
		rsp.getWriter().close();
		new ExportServceBackgroundThread(hs).start();
		return null;
    }
	
	private void resetSavedSessionData(HttpSession hs) {
		hs.removeAttribute("progress");
		hs.removeAttribute("filename");
		hs.removeAttribute("exporterror");
	}
	
	public String getProgress() throws Exception {
		HttpServletResponse rsp = ServletActionContext.getResponse();
		rsp.setStatus(200);
		rsp.setContentType("text/html");
		rsp.setCharacterEncoding("utf-8");
		
		String exporterror = (String) ServletActionContext.getRequest().getSession().getAttribute("exporterror");
		if(exporterror != null) {
			rsp.getWriter().write(("{\"result\":\"error\", \"msg\":\"" + getText(exporterror) + "\"}"));
		} else {
			Object objPgr = ServletActionContext.getRequest().getSession().getAttribute("progress");
			int progress = objPgr == null ?  0 :(Integer) objPgr;
			if(progress == 100){
				String filename = (String) ServletActionContext.getRequest().getSession().getAttribute("filename"); 
				rsp.getWriter().write(("{\"result\":\"success\", \"progress\":" + progress + ", \"filename\":\"expfolder/output/" + filename +  "\"}"));
			} else {
				rsp.getWriter().write(("{\"result\":\"success\", \"progress\":" + progress + "}"));
			}
		}
		rsp.getWriter().flush();
		rsp.getWriter().close();
		return null;
	}
	
	private void callExportService(HttpSession hs){
		try {
			String filename = exportService.exportMonthWorkReport(message);
			hs.setAttribute("filename", filename);
			hs.setAttribute("progress", 100);
		} catch (Exception e) {
			LoggerFactory.getLogger(MWorkExporter.class).error(e.getLocalizedMessage(), e);
		}
	}
	
	/**
	 * 导出服务后台线程
	 * @author Jerrie
	 *
	 */
	public class ExportServceBackgroundThread extends Thread {
		
		HttpSession hs;
		
		ExportServceBackgroundThread(HttpSession hs){
			this.hs = hs;
		}
		@Override
		public void run() {
			callExportService(hs);
		}
		
		public HttpSession getHs(){
			return hs;
		}
		
	}
}
