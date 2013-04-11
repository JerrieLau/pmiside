/**
 * 
 */
package com.yxtec.pmiside.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.yxtec.pmiside.mode.PMISExportUserMessage;

/**
 * @author 杰
 * 
 */
@Repository("pmisExportUserMessageDao")
public class PMISExportUserMessageDaoJpa implements IPMISExportUserMessageDaoJpa {

	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yxtec.pmisaddons.dao.IPMISExportUserMessageDao#
	 * findPMISExportUserMessageByuserID(long)
	 */
	@Override
	public PMISExportUserMessage findPMISExportUserMessageByuserID(String userid) {
		TypedQuery<PMISExportUserMessage> query = entityManager.createQuery(
				"select m from PMISExportUserMessage m where m.user.userId=:userid", PMISExportUserMessage.class);
		query.setParameter("userid", userid);
		try {
			PMISExportUserMessage msg = query.getSingleResult();
			return msg;
		} catch (Exception e) {
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yxtec.pmisaddons.dao.IPMISExportUserMessageDao#save(com.yxtec.pmisaddons
	 * .mode.PMISExportUserMessage)
	 */
	@Override
	public void save(PMISExportUserMessage pum) {
		PMISExportUserMessage managedPum = findPMISExportUserMessageByuserID(pum.getUser().getUserId());
		if (managedPum != null) {
			managedPum.setPmisUserid(pum.getPmisUserid());
			managedPum.setPmisName(pum.getPmisName());
			managedPum.setPmisOfficePlace(pum.getPmisOfficePlace());
			managedPum.setPmisPassword(pum.getPmisPassword());
			managedPum.setPmisProjectName(pum.getPmisProjectName());
			managedPum.setPmisProjectRole(pum.getPmisProjectRole());
			managedPum.setSupplier(pum.getSupplier());
		} else {
			pum.setUser(entityManager.merge(pum.getUser()));
			entityManager.persist(pum);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yxtec.pmisaddons.dao.IPMISExportUserMessageDao#update(com.yxtec.
	 * pmisaddons.mode.PMISExportUserMessage)
	 */
	@Override
	public void update(PMISExportUserMessage pum) {
		entityManager.merge(pum);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yxtec.pmisaddons.dao.IPMISExportUserMessageDao#delete(com.yxtec.
	 * pmisaddons.mode.PMISExportUserMessage)
	 */
	@Override
	public void delete(PMISExportUserMessage pum) {
		PMISExportUserMessage needDelete = entityManager.merge(pum);
		entityManager.remove(needDelete);
	}

	@PersistenceContext(unitName = "pmisaddonsPU")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
