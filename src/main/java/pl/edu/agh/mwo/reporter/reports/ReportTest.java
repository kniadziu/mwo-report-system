package pl.edu.agh.mwo.reporter.reports;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pl.edu.agh.mwo.reporter.model.Company;
import pl.edu.agh.mwo.reporter.model.Person;
import pl.edu.agh.mwo.reporter.model.Task;

import java.io.*;


public class ReportTest implements Report {

    private final Company company;
    private final String filename;
    private final String sheetName;
    private  String title;
    private final String[] headers;


    public ReportTest(Company company, String filename, String sheetName, String title, String[] headers) {
        this.company = company;
        this.filename = filename;
        this.sheetName = sheetName;
        this.title = title;
        this.headers = headers;
    }

    public void printToConsole() {
    }


    public void printToExcel() throws IOException {

        InputStream ExcelFileToRead = new FileInputStream(filename);

        try {

            XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);
            XSSFSheet sheet = wb.getSheet(sheetName);
            // XSSFSheet sheet = wb.getSheet(sheetName);
            //title report

            XSSFRow titleRow = sheet.createRow(0);
          //  Cell celltitle = titleRow.createCell(title);
            titleRow.createCell(0).setCellValue(title);

            //Header
            XSSFRow headerRow = sheet.createRow(2);
            for (int i = 0; i < headers.length; i++) {
                // kazda kolumna 20 znakow
                sheet.setColumnWidth(i, 20 * 256);
//                sheet.setColumnWidth(0, 2560);
//                sheet.setColumnWidth(1, 2560);
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }
            /* Header ends*/

            int rowNum = 3;

            for (Person person : company.getPersons()) {
                int hours = 0;
                XSSFRow row = sheet.createRow(rowNum++);

                for (Task task : person.getTasks()) {
                    hours += task.getHours();
                }

                row.createCell(0).setCellValue(person.getName());
                row.createCell(1).setCellValue(hours);
            }

            FileOutputStream fileOut = new FileOutputStream(filename);
            //zapisz workbook do Outputstream.
            wb.write(fileOut);
            fileOut.flush();
            fileOut.close();

        } catch (EncryptedDocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}
