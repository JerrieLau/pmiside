/**
 * 
 */
package com.yxtec.pmiside.observer;

import java.io.File;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.LoggerFactory;


/**
 * @author Jerrie
 *
 */
public class PmisideHttpSessionListener implements HttpSessionListener {

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		LoggerFactory.getLogger(PmisideHttpSessionListener.class).info("会话监听--[已创建会话" + arg0.getSession().getId() + "]");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		HttpSession session = (HttpSession) arg0.getSource();
		String basepath = session.getServletContext().getRealPath("expfolder");
		String filename = (String) session.getAttribute("filename");
		StringBuilder sb = new StringBuilder();
		sb.append("会话监听--");
		if(filename != null) {
			File exportedFile = new File(basepath + "/output/" + filename);
			if(exportedFile.exists()) {
				exportedFile.delete();
				sb.append("[已删除导出文件]");
			}
		}
		sb.append("[已销毁会话" + session.getId() + "]");
		LoggerFactory.getLogger(PmisideHttpSessionListener.class).info(sb.toString());
	}
}
