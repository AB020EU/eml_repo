package co.absa.eml.applicationcapture;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Random;


public class ReadExcel {
    static String name = "";
    static String surname = "";
    static String nameAndsurname = "";

    public void setExcel() {

        final String[] names = new String[20];
        try {


            FileInputStream file = new FileInputStream(new File("C:\\Users\\ab020eu\\OneDrive - Absa\\Automation\\eml_automation\\eml_automation\\src\\main\\resources\\data\\names.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            int i = 0;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    names[i] = cell.getStringCellValue() + "t";
                    i++;
                }
            }

            file.close();
            Random random = new Random();
            int index = random.nextInt(names.length);
            int ind = random.nextInt(names.length);

            this.name = names[index];
            this.surname = names[ind];
            this.nameAndsurname = this.name + " " + this.surname;


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    String getNameAndSurname() {
        return this.nameAndsurname;
    }
}