package com.yxtec.pmiside.initial;

import java.io.File;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.LoggerFactory;

/**
 * 初始化Servlet
 * Servlet implementation class InitializeServlet
 */
public class InitializeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static String basepath;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitializeServlet() {
        super();
    }

	public static String getBasepath() {
		return basepath;
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		//获取导出文件夹基准路径
		basepath = config.getServletContext().getRealPath("expfolder");
		LoggerFactory.getLogger(InitializeServlet.class).info("Initialize Servlet is initialized. basepath is " + basepath);
		
		//删除泄露的导出临时文件(比如强制退出或JVM异常时)
		String outputPath = basepath + "/output/";
		File outputDir = new File(outputPath);
		if(outputDir.exists() && outputDir.isDirectory()) {
			outputDir.delete();
		}
	}
}
