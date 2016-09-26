package com.vipinstraders.taxi.service.print;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.springframework.stereotype.Component;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.vipinstraders.taxi.domain.Performance;
import com.vipinstraders.taxi.domain.PerformanceConsidated;
import com.vipinstraders.taxi.domain.ShiftReport;
import com.vipinstraders.taxi.domain.ShiftReportDetails;
import com.vipinstraders.taxi.object.criteria.PerformanceSearchCriteria;
import com.vipinstraders.taxi.object.criteria.ShiftReportSearchCriteria;
import com.vipinstraders.taxi.util.ReportUtils;
import com.vipinstraders.taxi.util.TaxiUtils;

@Component
public class PrintServiceImpl implements PrintService {

	private static final String COMPANY_NAME = "Amalia Operations Pty Ltd";

	private static final String COMPANY_ADDRESS = "3 Coal court, Epsom";

	private static final String ABN = "14613714159";

	private static final String EMPTY_LINE = " ";

	public PrintServiceImpl() {

	}

	@Override
	public void printShiftReport(ShiftReportSearchCriteria searchCriteria, ShiftReportDetails shiftReportDetails,
			List<ShiftReport> shiftReportList, ServletOutputStream out) {
		Document document = new Document();

		try {

			PdfWriter.getInstance(document, out);
			document.open();
			createPageHeader(document);
			addShiftReportHeader(document, searchCriteria, shiftReportList);
			document.add(getShiftReportDetails(shiftReportDetails));
			document.add(new Paragraph(EMPTY_LINE));
			document.add(getShiftReportTable(shiftReportList));
			document.close(); // no need to close PDFwriter?

			out.flush();
			out.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void printPerformanceReport(ServletOutputStream out, PerformanceSearchCriteria searchCriteria,
			List<Performance> performaceDetailsList, PerformanceConsidated performanceConsolidatedDetails) {
		Document document = new Document();

		try {
			PdfWriter.getInstance(document, out);
			document.open();
			createPageHeader(document);
			addPerformanceReportHeader(document, searchCriteria, performaceDetailsList);
			document.add(getPerformanceReportDetails(performanceConsolidatedDetails));
			document.add(new Paragraph(EMPTY_LINE));
			document.add(getPerformanceReportTable(performaceDetailsList));
			document.close(); // no need to close PDFwriter?

			out.flush();
			out.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void printShiftReportDetails(ShiftReport shiftReport, ServletOutputStream out) {
		Document document = new Document();

		try {

			PdfWriter.getInstance(document, out);
			document.open();
			createPageHeader(document);
			document.add(getShiftReportEntryDetails(shiftReport));
			document.add(new Paragraph(EMPTY_LINE));
			document.close(); // no need to close PDFwriter?

			out.flush();
			out.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createPageHeader(Document document) throws DocumentException {
		Font font1 = new Font(Font.FontFamily.HELVETICA, 25, Font.BOLD);
		// Font font2 = new Font(Font.FontFamily.COURIER , 18,
		// Font.ITALIC | Font.UNDERLINE);
		// Font font3 = new Font(Font.FontFamily.TIMES_ROMAN, 27);
		Chunk headerName = new Chunk(COMPANY_NAME, font1);
		Paragraph headerN = new Paragraph(headerName);
		headerN.setAlignment(Element.ALIGN_CENTER);
		Paragraph address = new Paragraph(COMPANY_ADDRESS);
		address.setAlignment(Element.ALIGN_CENTER);
		Paragraph abn = new Paragraph("ABN : " + ABN);
		abn.setAlignment(Element.ALIGN_CENTER);
		document.add(headerN);
		document.add(address);
		document.add(abn);
		document.add(new Paragraph(EMPTY_LINE));
	}

	private void addShiftReportHeader(Document document, ShiftReportSearchCriteria searchCriteria,
			List<ShiftReport> shiftReportList) throws DocumentException {
		Font font1 = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD);
		Chunk reportHeaderChunk = new Chunk(
				ReportUtils.getDisplayTextBasedOnShiftReport(searchCriteria, "Shift Report", shiftReportList), font1);
		Paragraph headerP = new Paragraph(reportHeaderChunk);
		headerP.setAlignment(Element.ALIGN_CENTER);
		document.add(headerP);
		document.add(new Paragraph(EMPTY_LINE));
	}

	private PdfPTable getShiftReportTable(List<ShiftReport> shiftReportList) {
		PdfPTable table = new PdfPTable(8);
		addShiftReportTableHeader(table);
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		for (ShiftReport shiftReport : shiftReportList) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(format.format(shiftReport.getDate())));
			PdfPCell cell2 = new PdfPCell(new Paragraph(format.format(shiftReport.getFinishDate())));
			PdfPCell cell3 = new PdfPCell(new Paragraph(shiftReport.getDriver().getFamilyName()));
			PdfPCell cell4 = new PdfPCell(new Paragraph(Double.toString(shiftReport.getMeterRevenue())));
			PdfPCell cell5 = new PdfPCell(new Paragraph(Double.toString(shiftReport.getOwnerRevenue())));
			PdfPCell cell6 = new PdfPCell(new Paragraph(Double.toString(shiftReport.getDriverRevenue())));
			PdfPCell cell7 = new PdfPCell(new Paragraph(Double.toString(shiftReport.getFuelReceipt())));
			PdfPCell cell8 = new PdfPCell(new Paragraph(Double.toString(shiftReport.getTotal())));
			table.addCell(cell1);
			table.addCell(cell2);
			table.addCell(cell3);
			table.addCell(cell4);
			table.addCell(cell5);
			table.addCell(cell6);
			table.addCell(cell7);
			table.addCell(cell8);
		}
		return table;
	}

	private void addShiftReportTableHeader(PdfPTable table) {
		PdfPCell cell1 = new PdfPCell(new Paragraph("Start Time"));
		PdfPCell cell2 = new PdfPCell(new Paragraph("Finish Time"));
		PdfPCell cell3 = new PdfPCell(new Paragraph("Driver"));
		PdfPCell cell4 = new PdfPCell(new Paragraph("Meter Rev"));
		PdfPCell cell5 = new PdfPCell(new Paragraph("Owner Rev"));
		PdfPCell cell6 = new PdfPCell(new Paragraph("Driver Rev"));
		PdfPCell cell7 = new PdfPCell(new Paragraph("Fuel"));
		PdfPCell cell8 = new PdfPCell(new Paragraph("Total"));

		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		table.addCell(cell4);
		table.addCell(cell5);
		table.addCell(cell6);
		table.addCell(cell7);
		table.addCell(cell8);

	}
	
	private PdfPTable getShiftReportDetails(ShiftReportDetails shiftReportDetails) {
		PdfPTable table = new PdfPTable(2);
		addShiftReportDetailsRowToTable(table, "Total Meter Revenue", Double.toString(shiftReportDetails.getTotalMeterRevenue()));
		addShiftReportDetailsRowToTable(table, "Total Owner Revenue", Double.toString(shiftReportDetails.getTotalOwnerRevenue()));
		addShiftReportDetailsRowToTable(table, "Total Driver Revenue", Double.toString(shiftReportDetails.getTotalDriverRevenue()));
		addShiftReportDetailsRowToTable(table, "Total Fuel Receipts", Double.toString(shiftReportDetails.getFuelRecipts()));
		addShiftReportDetailsRowToTable(table, "Total Online Receipts", Double.toString(shiftReportDetails.getOnlineReceipts()));
		addShiftReportDetailsRowToTable(table, "Total Paper voucher", Double.toString(shiftReportDetails.getPaperVoucher()));
		addShiftReportDetailsRowToTable(table, "Total Account Voucher", Double.toString(shiftReportDetails.getAccountVoucher()));
		return table;
	}
	
	private void addShiftReportDetailsRowToTable(PdfPTable table, String columOne, String columnTwo) {
		PdfPCell cell1 = new PdfPCell(new Paragraph(columOne));
		PdfPCell cell2 = new PdfPCell(new Paragraph(columnTwo));
		table.addCell(cell1);
		table.addCell(cell2);
	}
	
	private PdfPTable getShiftReportEntryDetails(ShiftReport shiftReport) {
		PdfPTable table = new PdfPTable(2);
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		addShiftReportDetailsRowToTable(table, "Date", format.format(shiftReport.getDate()));
		addShiftReportDetailsRowToTable(table, "Finish Date", format.format(shiftReport.getFinishDate()));
		addShiftReportDetailsRowToTable(table, "Driver", shiftReport.getDriver().getFamilyName() + " " + shiftReport.getDriver().getGivenName());
		addShiftReportDetailsRowToTable(table, "Car", shiftReport.getCar().getRego());
		addShiftReportDetailsRowToTable(table, "Start Meter Reading", Integer.toString(shiftReport.getStartMeterReading()));
		addShiftReportDetailsRowToTable(table, "End Meter Reading", Integer.toString(shiftReport.getEndMeterReading()));
		addShiftReportDetailsRowToTable(table, "Meter Revenue", Double.toString(shiftReport.getMeterRevenue()));
		addShiftReportDetailsRowToTable(table, "Driver Revenue", Double.toString(shiftReport.getDriverRevenue()));
		addShiftReportDetailsRowToTable(table, "Driver Subsidy", Double.toString(shiftReport.getDriverSubsidy()));
		addShiftReportDetailsRowToTable(table, "Owner Revenue", Double.toString(shiftReport.getOwnerRevenue()));
		addShiftReportDetailsRowToTable(table, "Owner Subsidy", Double.toString(shiftReport.getOwnerSubsidy()));
		addShiftReportDetailsRowToTable(table, "Online Receipt", Double.toString(shiftReport.getOnlineReceipt()));
		addShiftReportDetailsRowToTable(table, "Paper Voucher", Double.toString(shiftReport.getPaperVoucher()));
		addShiftReportDetailsRowToTable(table, "Account Voucher", Double.toString(shiftReport.getAccountVoucher()));
		addShiftReportDetailsRowToTable(table, "Bailment Fee", Double.toString(shiftReport.getBailmentFee()));
		addShiftReportDetailsRowToTable(table, "Fuel Receipt", Double.toString(shiftReport.getFuelReceipt()));
		addShiftReportDetailsRowToTable(table, "Total", Double.toString(shiftReport.getTotal()));
		
		return table;
	}
	
	private void addPerformanceReportHeader(Document document, PerformanceSearchCriteria searchCriteria,
			List<Performance> performaceDetailsList) throws DocumentException {
		Font font1 = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD);
		Chunk reportHeaderChunk = new Chunk(
				ReportUtils.getDisplayTextBasedOnPerformanceReport(searchCriteria, "Performance Report", performaceDetailsList), font1);
		Paragraph headerP = new Paragraph(reportHeaderChunk);
		headerP.setAlignment(Element.ALIGN_CENTER);
		document.add(headerP);
		document.add(new Paragraph(EMPTY_LINE));
	}
	
	private PdfPTable getPerformanceReportDetails(PerformanceConsidated performanceConsidated) {
		PdfPTable table = new PdfPTable(2);
		addShiftReportDetailsRowToTable(table, "Meter Revenue", Double.toString(TaxiUtils.round(performanceConsidated.getMeterRevenue(), 2)));
		addShiftReportDetailsRowToTable(table, "Owner Income", Double.toString(TaxiUtils.round(performanceConsidated.getOwnerIncome(), 2)));
		addShiftReportDetailsRowToTable(table, "Driver Fees", Double.toString(TaxiUtils.round(performanceConsidated.getDriverFees(), 2)));
		addShiftReportDetailsRowToTable(table, "Fuel Cost", Double.toString(TaxiUtils.round(performanceConsidated.getFuelCost(), 2)));
		addShiftReportDetailsRowToTable(table, "Maintenance Cost", Double.toString(TaxiUtils.round(performanceConsidated.getMaintanenceCost(), 2)));
		addShiftReportDetailsRowToTable(table, "Earnings", Double.toString(TaxiUtils.round(performanceConsidated.getTotalEarnings(), 2)));
		return table;
	}
	
	private PdfPTable getPerformanceReportTable(List<Performance> performaceDetailsList) {
		PdfPTable table = new PdfPTable(7);
		addPerformanceReportTableHeader(table);
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		for (Performance performance : performaceDetailsList) {
			PdfPCell cellDate = new PdfPCell(new Paragraph(format.format(performance.getDate())));
			PdfPCell cell2 = new PdfPCell(new Paragraph(performance.getDriverName()));
			PdfPCell cell3 = new PdfPCell(new Paragraph(Double.toString(performance.getDriverFees())));
			PdfPCell cell4 = new PdfPCell(new Paragraph(Double.toString(performance.getOwnerIncome())));
			PdfPCell cell5 = new PdfPCell(new Paragraph(Double.toString(performance.getFuelCost())));
			PdfPCell cell6 = new PdfPCell(new Paragraph(performance.getMaintanenceType()));
			PdfPCell cell7 = new PdfPCell(new Paragraph(Double.toString(performance.getMaintanenceCost())));
			table.addCell(cellDate);
			table.addCell(cell2);
			table.addCell(cell3);
			table.addCell(cell4);
			table.addCell(cell5);
			table.addCell(cell6);
			table.addCell(cell7);
		}
		return table;
	}
	
	private void addPerformanceReportTableHeader(PdfPTable table) {
		PdfPCell cellDate = new PdfPCell(new Paragraph("Date"));
		PdfPCell cellDriver = new PdfPCell(new Paragraph("Driver"));
		PdfPCell cellDriverFees = new PdfPCell(new Paragraph("Driver Fees"));
		PdfPCell cellOwnerIncome = new PdfPCell(new Paragraph("Owner Income"));
		PdfPCell cellFuel = new PdfPCell(new Paragraph("Fuel"));
		PdfPCell cellMaintenanceType = new PdfPCell(new Paragraph("Maintenance Type"));
		PdfPCell cellMaintenanceFees = new PdfPCell(new Paragraph("Maintenance Fees"));

		table.addCell(cellDate);
		table.addCell(cellDriver);
		table.addCell(cellDriverFees);
		table.addCell(cellOwnerIncome);
		table.addCell(cellFuel);
		table.addCell(cellMaintenanceType);
		table.addCell(cellMaintenanceFees);
	}

}
