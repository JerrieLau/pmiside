/**
 * 
 */
package com.yxtec.pmiside.action.observer;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.yxtec.pmiside.action.MWorkExporter.ExportServceBackgroundThread;

/**
 * @author Jerrie
 * 
 */
@Aspect
@Component("exportObserver")
public class ExportObserver {
	
	@After("execution(* com.yxtec.pmisaddons.domain.export.fetcher.PMISMonthRecordDataFetcher.fetchPMISData(..))")
	public void notificationFetchedData() {
		ExportServceBackgroundThread esbt = (ExportServceBackgroundThread) Thread.currentThread();
		esbt.getHs().setAttribute("progress", 60);
	}

	@After("execution(* com.yxtec.pmisaddons.domain.export.ananlysis.PMISMonthDataAnanlysiser.ananlysisData(..))")
	public void notificationAnanlysisedData() {
		ExportServceBackgroundThread esbt = (ExportServceBackgroundThread) Thread.currentThread();
		esbt.getHs().setAttribute("progress", 70);
	}

	@After("execution(* com.yxtec.pmisaddons.domain.export.reportor.WYMonthReportCreater.createReport(..))")
	public void notificationCreatedReport() {
		ExportServceBackgroundThread esbt = (ExportServceBackgroundThread) Thread.currentThread();
		esbt.getHs().setAttribute("progress", 95);
	}
	
	@AfterThrowing("execution(* com.yxtec.pmisaddons.domain.export.communication.PMISMonthDataFetchCommunication.sendRequest(..))")
	public void notificationNetworkError() {
		ExportServceBackgroundThread esbt = (ExportServceBackgroundThread) Thread.currentThread();
		if(esbt.getHs().getAttribute("exporterror") == null) {
			esbt.getHs().setAttribute("exporterror", "service.exportmwork.networkerror");
		}
	}
	
	@AfterThrowing("execution(* com.yxtec.pmisaddons.domain.export.fetcher.PMISMonthRecordDataFetcher.*(..))")
	public void notificationLoginError() {
		ExportServceBackgroundThread esbt = (ExportServceBackgroundThread) Thread.currentThread();
		if(esbt.getHs().getAttribute("exporterror") == null) {
			esbt.getHs().setAttribute("exporterror", "service.exportmwork.loginerror");
		}
	}
	
	@AfterThrowing("execution(* com.yxtec.pmisaddons.domain.export.ananlysis.PMISMonthDataAnanlysiser.ananlysisData(..))")
	public void notificationAnanlysisError() {
		ExportServceBackgroundThread esbt = (ExportServceBackgroundThread) Thread.currentThread();
		if(esbt.getHs().getAttribute("exporterror") == null) {
			esbt.getHs().setAttribute("exporterror", "service.exportmwork.ananlysiserror");
		}
	}
	
	@AfterThrowing("execution(* com.yxtec.pmisaddons.domain.export.reportor.WYMonthReportCreater.createReport(..))")
	public void notificationExportFileError() {
		ExportServceBackgroundThread esbt = (ExportServceBackgroundThread) Thread.currentThread();
		if(esbt.getHs().getAttribute("exporterror") == null) {
			esbt.getHs().setAttribute("exporterror", "service.exportmwork.exportfileerror");
		}
	}
}
