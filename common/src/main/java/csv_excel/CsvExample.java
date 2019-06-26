package csv_excel;

import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class CsvExample
{
	/**
	 * CSV文件的读取、写入案例
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
	{
		//读取
		String csv = "./xxx.csv";
		FileReader reader = new FileReader(csv);
		// 第一行为表头，自动匹配
		Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
		for (CSVRecord record : records)
		{
			String id = record.get("id");
		}
		
		// 自己制作指定表头，可能CSV自身没有表头
		FileReader reader2 = new FileReader("path/to/file.csv");
		Iterable<CSVRecord> records2 = CSVFormat.RFC4180.withHeader(Headers.class).parse(reader2);
		for (CSVRecord record : records2) {
		    String id = record.get(Headers.ID);
		    String customerNo = record.get(Headers.CustomerNo);
		    String name = record.get(Headers.Name);
		}
		
		// TODO 写入
	}

}

enum Headers {
    ID, CustomerNo, Name
}