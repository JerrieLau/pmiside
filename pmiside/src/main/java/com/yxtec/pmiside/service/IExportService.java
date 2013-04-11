/**
 * 
 */
package com.yxtec.pmiside.service;


import com.yxtec.pmiside.mode.PMISExportUserMessage;

/**
 * 导出服务接口
 * @author Jerrie
 *
 */
public interface IExportService {
	
	/**
	 * 导出月公作记录
	 * @param pum
	 * @return 文件名
	 */
	public String exportMonthWorkReport(PMISExportUserMessage pum) throws Exception ;
	
	/**
	 * 保存PMIS导出用户信息
	 * @param pum
	 */
	public void savePMISExportUserMessage(PMISExportUserMessage pum);
	
	/**
	 * 通过用户标识查找用户PMIS导出用户信息
	 * @param userid
	 * @return
	 */
	public PMISExportUserMessage findPMISExportUserMessageByuserID(String userid);
	
	/**
	 * 设置基准路径
	 * @param path
	 */
	public void setBasepath(String path);
	
}
