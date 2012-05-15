package utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.*;

/**
 *
 * @author
 */
public class XlsReportGenerator {

    public static byte[] getReport(String login) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet firstSheet = workbook.createSheet("TRFs report");

        HSSFRow rowName = firstSheet.createRow(0);

        HSSFCellStyle style =workbook.createCellStyle();
        style.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        
        //report row headers
        rowName.createCell(0).setCellValue("Name");
        rowName.getCell(0).setCellStyle(style);
        rowName.createCell(1).setCellValue("Office");
        rowName.getCell(1).setCellStyle(style);
        rowName.createCell(2).setCellValue("Destination");
        rowName.getCell(2).setCellStyle(style);
        rowName.createCell(3).setCellValue("Date Begin");
        rowName.getCell(3).setCellStyle(style);
        rowName.createCell(4).setCellValue("Date End");
        rowName.getCell(4).setCellStyle(style);
        rowName.createCell(5).setCellValue("Current status");
        rowName.getCell(5).setCellStyle(style);

        List reportData = database.utilities.Reports.CurrentStatSameCountry(login);
        DateFormat reportDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        for (int i = 0; i < reportData.size(); i++) {
            HSSFRow row = firstSheet.createRow(i + 1);

            Object[] report_row = (Object[]) reportData.get(i);

            String fullName = report_row[0].toString() + " " + report_row[1].toString();
            row.createCell(0).setCellValue(fullName);

            String office = report_row[2].toString() + " " + report_row[3].toString();
            row.createCell(1).setCellValue(office);

            String destination = report_row[4].toString() + ", " + report_row[5].toString();
            row.createCell(2).setCellValue(destination);

            //begin date
            row.createCell(3).setCellValue(reportDateFormat.format((Date) report_row[6]));
            //end date
            row.createCell(4).setCellValue(reportDateFormat.format((Date) report_row[7]));

            String statusName = database.mapping.Trf.getStatus(Integer.parseInt(report_row[8].toString()));
            row.createCell(5).setCellValue(statusName);
        }

        for (int i = 0; i < rowName.getLastCellNum(); i++) {
            firstSheet.autoSizeColumn(i);
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        workbook.write(baos);
        return baos.toByteArray();
    }
}