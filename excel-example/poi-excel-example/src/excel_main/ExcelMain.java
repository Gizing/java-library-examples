package excel_main;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelMain
{

    public static void main(String[] args) throws IOException
    {
        // 创建输入流
        FileInputStream fis = new FileInputStream(new File("./test.xlsx"));
        // 通过构造函数传参
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        // 得到工作表
        XSSFSheet sheet = workbook.getSheetAt(0);
        System.out.println("total row number:" + (sheet.getLastRowNum() - sheet.getFirstRowNum() + 1));
        for (int i = 0; i <= sheet.getLastRowNum(); ++i)
        {
            XSSFRow row = sheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); ++j)
            {
                XSSFCell cell = row.getCell(j);
                if (cell == null) continue;
                String cellValue = null;
                switch (cell.getCellTypeEnum())
                {
                case STRING:
                    cellValue = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                default:
                    break;
                }
                System.out.println(i + "," + j + " " + cellValue);
            }
        }
        workbook.close();
        fis.close();
        
    }

}
