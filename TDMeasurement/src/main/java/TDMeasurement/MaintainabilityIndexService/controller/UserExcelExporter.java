package TDMeasurement.MaintainabilityIndexService.controller;

import TDMeasurement.MaintainabilityIndexService.entity.DirResult;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserExcelExporter {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<DirResult> listdirresults;


    public UserExcelExporter(List<DirResult> listdirresults) {
        this.listdirresults = listdirresults;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderRow() {
        sheet = workbook.createSheet("DirResults");
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("Directory ID");
    }

    private void writeDataRows() {


    }


    public void export(HttpServletResponse response) throws IOException {
        writeHeaderRow();
        writeDataRows();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();


    }


}
