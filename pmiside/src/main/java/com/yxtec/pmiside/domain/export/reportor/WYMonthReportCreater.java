/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yxtec.pmiside.domain.export.reportor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont;

import com.yxtec.pmiside.domain.export.ananlysis.RecordPeer;

/**
 * 
 * @author 杰
 */
public class WYMonthReportCreater implements IReportCreater {

	private String basepath;
	
	/**
	 * 创建空文件
	 * @param headMsg
	 * @param rps
	 * @return 返回创建的文件路径
	 * @throws Exception
	 */
	private String createEmptyFile(Map<String, String> headMsg, List<RecordPeer> rps) throws Exception {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		//构建文件路径
		String uuid = UUID.randomUUID().toString();
		String filename = headMsg.get("userId") + "-" + headMsg.get("monthYear") + "-" + uuid + ".docx";
		String filepath = basepath + "/output/" + filename;
		
		try {
			fis = new FileInputStream(basepath + "/models/项目工作记录单模板.docx");
			XWPFDocument doc = new XWPFDocument(fis);

			XWPFTable headTable = doc.getTables().get(0);
			int dtSize = headTable.getRows().size();
			for (int i = 0; i < rps.size(); i++) {
				if ((i + 10) >= dtSize) {
					headTable.addRow(headTable.getRow(9), i + 9);
				}
			}
			// Write Empty File.
			File file = new File(filepath);
			if(! file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			fos = new FileOutputStream(file);
			doc.write(fos);
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (IOException ex) {
				Logger.getLogger(WYMonthReportCreater.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return filepath;
	}

	@Override
	public String createReport(Map<String, String> headMsg, List<RecordPeer> rps) throws Exception {
		String filepath = createEmptyFile(headMsg, rps);

		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(filepath);
			XWPFDocument doc = new XWPFDocument(fis);

			/**
			 * Fill header data..
			 */
			XWPFTable headTable = doc.getTables().get(0);

			
			// fill supplier.. 
			XWPFTableRow supplier = headTable.getRow(2);
			XWPFTableCell supplierValueCell = supplier.getCell(1);
			supplierValueCell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
			supplierValueCell.removeParagraph(0);	
			CTFont ctFont = CTFont.Factory.newInstance(); 
			XSSFFont xssfFont=new XSSFFont(ctFont, 0); 
			xssfFont.setFontHeightInPoints(XSSFFont.BOLDWEIGHT_BOLD); 
			XWPFParagraph newPara = new XWPFParagraph(supplierValueCell.getCTTc().addNewP(), supplierValueCell);	
			newPara.setAlignment(ParagraphAlignment.CENTER);
			XWPFRun run=newPara.createRun();
			run.setBold(true);
			run.setFontSize(12);
			run.setText(headMsg.get("supplier"));
			
			// fill project name..
			XWPFTableRow projectRow = headTable.getRow(3);
			XWPFTableCell projectNameValueCell = projectRow.getCell(1);
			projectNameValueCell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
			projectNameValueCell.setText(headMsg.get("projectName"));

			// fill month and year..
			XWPFTableRow monthYearRow = headTable.getRow(4);
			XWPFTableCell monthYearRowValueCell = monthYearRow.getCell(1);
			monthYearRowValueCell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
			monthYearRowValueCell.setText(headMsg.get("monthYear"));

			// fill username..
			XWPFTableRow userNameRow = headTable.getRow(5);
			XWPFTableCell userNameValueCell = userNameRow.getCell(1);
			userNameValueCell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
			userNameValueCell.setText(headMsg.get("userName"));

			// fill projectRole..
			XWPFTableRow projectRoleRow = headTable.getRow(6);
			XWPFTableCell projectRoleValueCell = projectRoleRow.getCell(1);
			projectRoleValueCell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
			projectRoleValueCell.setText(headMsg.get("projectRole"));

			/**
			 * Fill detail data..
			 */
			Locale lcl = new Locale("zh", "CN");
			SimpleDateFormat formatWeek = new SimpleDateFormat("E", lcl);
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd", lcl);
			int dtSize = headTable.getRows().size();
			for (int i = 0; i < rps.size(); i++) {
				RecordPeer rp = rps.get(i);
				XWPFTableRow dataRow = headTable.getRow(i + 9);
				// fill date.
				XWPFTableCell dataRowDateCell = dataRow.getCell(0);
				dataRowDateCell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
				dataRowDateCell.setText(rp.getDate());

				// fill week.
				XWPFTableCell dataRowWeekCell = dataRow.getCell(1);
				dataRowWeekCell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
				dataRowWeekCell.setText(formatWeek.format(formatDate.parse(rp.getDate())));

				// fill content.
				XWPFTableCell dataRowCotnentCell = dataRow.getCell(2);
				dataRowCotnentCell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
				dataRowCotnentCell.setText(rp.getContent());

				// fill interval.
				XWPFTableCell dataRowIntervalCell = dataRow.getCell(3);
				dataRowIntervalCell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
				dataRowIntervalCell.setText(Integer.parseInt(rp.getInterval()) > 4 ? "1" : "0.5");

				// fill place.
				XWPFTableCell dataRowPlaceCell = dataRow.getCell(4);
				dataRowPlaceCell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
				dataRowPlaceCell.setText(headMsg.get("place"));
			}

			// fill total
			XWPFTableRow dataRow = headTable.getRow(dtSize - 1);
			XWPFTableCell dataRowTotalCell = dataRow.getCell(1);
			dataRowTotalCell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
			dataRowTotalCell.setText(rps.size() + "天");
			// Write File.
			fos = new FileOutputStream(filepath);
			doc.write(fos);
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (IOException ex) {
				Logger.getLogger(WYMonthReportCreater.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return filepath.substring(filepath.lastIndexOf("/") + 1);
	}

	@Override
	public void setBasepath(String base) {
		basepath = base;
	}
}
