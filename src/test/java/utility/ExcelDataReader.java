package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataReader {
	public Object[][] getExcelData(String path, String testId, List<String> keyword) throws IOException {
	
		
		ArrayList<ArrayList<String>> tempData= new ArrayList<ArrayList<String>>();
		int dataIterrator=keyword.size();
		DataFormatter formatter = new DataFormatter();
		int testIdRowVal=-1;
		FileInputStream fileStream = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fileStream);
		int tdIndex = workbook.getSheetIndex("TD");
		XSSFSheet sheet = workbook.getSheetAt(tdIndex);
		boolean flag = false;
		
		for (int i = 0; i <=sheet.getLastRowNum(); i++) {
			
			Row row = sheet.getRow(i);
			
			if(row!=null)
			for (int j = 0; j <= row.getLastCellNum(); j++) {
				Cell cell = row.getCell(j);
				
				if (flag == true) {
					for(String key:keyword) {
						
						if(formatter.formatCellValue(cell).equalsIgnoreCase(key)) {
							
							if((dataIterrator++)%keyword.size()==0)
								tempData.add(new ArrayList<String>());
							tempData.get(tempData.size()-1).add(formatter.formatCellValue(row.getCell(j+1)));
						}
					}
				}
				if((j==testIdRowVal)&&(!formatter.formatCellValue(cell).isEmpty())) {
					
					flag=false;
				}
				if (formatter.formatCellValue(cell).equalsIgnoreCase(testId)) {
					
					testIdRowVal=j;
					flag = true;
				}
				
			}
			
		}
		Object[][] data = new Object[tempData.size()][keyword.size()];	
		for(int i=0;i<tempData.size();i++) {
			for(int j=0;j<keyword.size();j++) {
				data[i][j]=tempData.get(i).get(j);
			}
		}
		workbook.close();
		return data;
	}

}
