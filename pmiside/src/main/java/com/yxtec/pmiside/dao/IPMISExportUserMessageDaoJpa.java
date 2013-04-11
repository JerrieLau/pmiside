/**
 * 
 */
package com.yxtec.pmiside.dao;

import com.yxtec.pmiside.mode.PMISExportUserMessage;

/**
 * @author Jerrie
 * 
 */
public interface IPMISExportUserMessageDaoJpa {

	public PMISExportUserMessage findPMISExportUserMessageByuserID(String userid);

	public void save(PMISExportUserMessage pum);

	public void update(PMISExportUserMessage pum);

	public void delete(PMISExportUserMessage pum);

}
