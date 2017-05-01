package com.roku.calmanpdf2xml;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XmlReader {
	private final int titleRow=0;
	private final int titleCol=0;
	private final int baseVersionBuildRow=5;
	private final int baseVersionBuildCol=2;
	private final int newVersionBuildRow=18;
	private final int newVersionBuildCol=2;
	private final int b_x_cie31_base_row=8;
	private final int b_y_cie31_base_row=9;
	private final int b_y_base_row=10;
	private final int cct_base_row=11;
	private final int c_x_cie31_base_row=14;
	private final int c_y_cie31_base_row=15;
	private final int c_y_base_row=16;
	private final int b_x_cie31_new_row=21;
	private final int b_y_cie31_new_row=22;
	private final int b_y_new_row=23;
	private final int cct_new_row=24;
	private final int c_x_cie31_new_row=27;
	private final int c_y_cie31_new_row=28;
	private final int c_y_new_row=29;
        private Set<Integer> colorRows=new HashSet<>();
        private int[] eval = new int[]{37,38,43,44,45,46,47,48};
	
	FileInputStream excelFile = null;
	XSSFWorkbook workbook=null;
	XSSFSheet  sheet = null;
        XSSFRow row = null;
	String xmlFileName=null;
	public  XmlReader(String xmlFilePath) {
                colorRows.add(14);
                colorRows.add(15);
                colorRows.add(16);
                colorRows.add(27);
                colorRows.add(28);
                colorRows.add(29);
		xmlFileName=xmlFilePath;
                System.out.println(xmlFileName);
		try {
			excelFile = new FileInputStream(new File(xmlFileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(excelFile.toString());
		try {
			workbook = new XSSFWorkbook(excelFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 sheet = workbook.getSheet("Sheet1");
         
	}
	
	public void updateCell(int row,int col,double updatedNumber) {		
                Cell currCell = sheet.getRow(row).getCell(col);
                if(currCell==null)
                    currCell = sheet.getRow(row).createCell(col);
                currCell.setCellValue(updatedNumber);
	}
	
	public void updateCell(int row,int col,int updatedNumber) {
		Cell currCell = sheet.getRow(row).getCell(col);
                currCell.setCellValue(updatedNumber);  
	}
	
	public void updateCell(int row,int col,String updatedString) {
		Cell currCell = sheet.getRow(row).getCell(col);
        currCell.setCellValue(updatedString);  
	}
	public void save(String outputFile) throws IOException {
		excelFile.close();
		FileOutputStream output_file =new FileOutputStream(new File(outputFile));
		workbook.write(output_file);
                output_file.close();
	}
	
	public void updateTitle(String titleName) {
		updateCell(titleRow,titleCol,titleName);
	}
	
	public void updateBaseVersionBuild(String baseVersionBuild) {
		updateCell(baseVersionBuildRow,baseVersionBuildCol,baseVersionBuild);
	}
	public void updateNewVersionBuild(String newVersionBuild) {
		updateCell(newVersionBuildRow,newVersionBuildCol,newVersionBuild);
	}
	public void updateDoubleArray(int row,int col,double[] inputs) {
		for(int i=0;i<inputs.length;i++) {
                    if(colorRows.contains(row) && i>=8)
                       continue;
                    updateCell(row,col+i,inputs[i]);
		}
	}
	public void updateBaseVersionData(double[][] baseData) {
		//baseData should be 7 double arrays
		updateDoubleArray(b_x_cie31_base_row,1,baseData[0]);
		updateDoubleArray(b_y_cie31_base_row,1,baseData[1]);
		updateDoubleArray(b_y_base_row,1,baseData[2]);
		updateDoubleArray(cct_base_row,1,baseData[3]);
		updateDoubleArray(c_x_cie31_base_row,1,baseData[4]);
		updateDoubleArray(c_y_cie31_base_row,1,baseData[5]);
		updateDoubleArray(c_y_base_row,1,baseData[6]);
	}
	
	public void updateNewVersionData(double[][] baseData) {
		//baseData should be 7 double arrays
		updateDoubleArray(b_x_cie31_new_row,1,baseData[0]);
		updateDoubleArray(b_y_cie31_new_row,1,baseData[1]);
		updateDoubleArray(b_y_new_row,1,baseData[2]);
		updateDoubleArray(cct_new_row,1,baseData[3]);
		updateDoubleArray(c_x_cie31_new_row,1,baseData[4]);
		updateDoubleArray(c_y_cie31_new_row,1,baseData[5]);
		updateDoubleArray(c_y_new_row,1,baseData[6]);
	}
        
        public void updateCompleteDate(String baseFile,String newFile){
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String currDate="Completed at "+dateFormat.format(date)+" with basefile "+baseFile+" and newFile "+newFile;
            updateCell(1,0,currDate);   
        }
        
        public void updateEvaluation(){
            for(int i=0;i<eval.length;i++){
               for(int j=1;j<11;j++){
                    Cell currCell = sheet.getRow(eval[i]).getCell(j);
                    if(currCell==null)
                        break;
                    try{                      
                        String tmp =currCell.getCellFormula();           
                        currCell.setCellFormula(tmp);
                    }catch(IllegalStateException e){
                        System.out.println(eval[i]+" : "+j);
                        e.printStackTrace();
                    }
                    
               }
            }
            System.out.println(sheet.toString());
            //RECALCULATE THE RATIO MAKE SURE <= 
            XSSFRow evalRow=sheet.getRow(53);
            if(evalRow == null)
                evalRow = sheet.createRow(53);            
            Cell evalCell = sheet.getRow(53).getCell(0);
            if(evalCell==null)
                evalCell = sheet.getRow(53).createCell(0);              
            evalCell.setCellValue("PLEASE FILL");
        }
}
