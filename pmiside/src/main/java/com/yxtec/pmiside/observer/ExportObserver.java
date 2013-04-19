/**
 * 
 */
package com.yxtec.pmiside.observer;

import org.aspectj.lang.annotation.AfterReturning;
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

	@AfterReturning("execution(* com.yxtec.pmiside.domain.export.fetcher.PMISMonthRecordDataFetcher.fetchPMISData(..))")
	public void notificationFetchedData() {
		if (Thread.currentThread() instanceof ExportServceBackgroundThread) {
			ExportServceBackgroundThread esbt = (ExportServceBackgroundThread) Thread.currentThread();
			esbt.getHs().setAttribute("progress", 60);
		}
	}

	@AfterReturning("execution(* com.yxtec.pmiside.domain.export.ananlysis.PMISMonthDataAnanlysiser.ananlysisData(..))")
	public void notificationAnanlysisedData() {
		if (Thread.currentThread() instanceof ExportServceBackgroundThread) {
			ExportServceBackgroundThread esbt = (ExportServceBackgroundThread) Thread.currentThread();
			esbt.getHs().setAttribute("progress", 70);
		}
	}

	@AfterReturning("execution(* com.yxtec.pmiside.domain.export.reportor.WYMonthReportCreater.createReport(..))")
	public void notificationCreatedReport() {
		if (Thread.currentThread() instanceof ExportServceBackgroundThread) {
			ExportServceBackgroundThread esbt = (ExportServceBackgroundThread) Thread.currentThread();
			esbt.getHs().setAttribute("progress", 95);
		}
	}
	
	@AfterThrowing("execution(* com.yxtec.pmiside.domain.export.communication.PMISMonthDataFetchCommunication.sendRequest(..))")
	public void notificationNetworkError() {
		if (Thread.currentThread() instanceof ExportServceBackgroundThread) {
			ExportServceBackgroundThread esbt = (ExportServceBackgroundThread) Thread.currentThread();
			if (esbt.getHs().getAttribute("exporterror") == null) {
				esbt.getHs().setAttribute("exporterror", "service.exportmwork.networkerror");
			}
		}
	}

	@AfterThrowing("execution(* com.yxtec.pmiside.domain.export.fetcher.PMISMonthRecordDataFetcher.*(..))")
	public void notificationLoginError() {
		if (Thread.currentThread() instanceof ExportServceBackgroundThread) {
			ExportServceBackgroundThread esbt = (ExportServceBackgroundThread) Thread.currentThread();
			if (esbt.getHs().getAttribute("exporterror") == null) {
				esbt.getHs().setAttribute("exporterror", "service.exportmwork.loginerror");
			}
		}
	}

	@AfterThrowing("execution(* com.yxtec.pmiside.domain.export.ananlysis.PMISMonthDataAnanlysiser.ananlysisData(..))")
	public void notificationAnanlysisError() {
		if (Thread.currentThread() instanceof ExportServceBackgroundThread) {
			ExportServceBackgroundThread esbt = (ExportServceBackgroundThread) Thread.currentThread();
			if (esbt.getHs().getAttribute("exporterror") == null) {
				esbt.getHs().setAttribute("exporterror", "service.exportmwork.ananlysiserror");
			}
		}
	}

	@AfterThrowing("execution(* com.yxtec.pmiside.domain.export.reportor.WYMonthReportCreater.createReport(..))")
	public void notificationExportFileError() {
		if (Thread.currentThread() instanceof ExportServceBackgroundThread) {
			ExportServceBackgroundThread esbt = (ExportServceBackgroundThread) Thread.currentThread();
			if (esbt.getHs().getAttribute("exporterror") == null) {
				esbt.getHs().setAttribute("exporterror", "service.exportmwork.exportfileerror");
			}
		}
	}
}
