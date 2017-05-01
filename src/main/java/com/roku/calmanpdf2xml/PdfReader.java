package com.roku.calmanpdf2xml;
import java.io.File;
import java.io.IOException;

import java.util.Scanner;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfReader {
	
	final static int PAGENUMBER=6;
	final static int[] data_candidates = new int[] {27,28,29,33,36,37,38};
	String content=null;
	public  PdfReader(String filenameBase) {
		
		try {
			content = pdf2text(PAGENUMBER,filenameBase);	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static String pdf2text(int pageNumber,String filePath) throws IOException {
			PDDocument document = PDDocument.load(new File(filePath));
			PDFTextStripper stripper = new PDFTextStripper();
			if(document==null) {
				System.out.println("out put null");
				return "";
			}
			stripper.setStartPage(pageNumber);
			stripper.setEndPage(pageNumber);                        
			return stripper.getText(document);	
	}
	
	//parse to 10 double digits
	public double[] parseLine(String str) {
		double[] res = new double[10];
		int idx=0;
		Scanner scanner = new Scanner(str);
		while(scanner.hasNext()) {
			if(scanner.hasNextDouble()) {                            
				res[idx]=scanner.nextDouble();
                                idx++;
			}else {
				scanner.next();
			}
		}
		return res;
	}
        public  double[][] parseContent(){
            return parseContent(content);
        }
	
	public  double[][] parseContent(String content) {
		Scanner scanner = new Scanner(content);
		//base
		double[][] res =new double[7][10];

		int count=0,ptr_candi=0;
		while(scanner.hasNext()) {			
			String tmp =scanner.nextLine();
			if(ptr_candi>=data_candidates.length)
				break;
			if(count==data_candidates[ptr_candi]) {
				double[] lineDate = parseLine(tmp);
                                for(int i=0;i<lineDate.length;i++)
                                    res[ptr_candi][i]=lineDate[i];
                                System.out.println("Line: "+(count)+"  "+tmp);
                                ptr_candi++;
			}

			count++;
		}
		scanner.close();
		return res;
		
		
	}
	
	


}
