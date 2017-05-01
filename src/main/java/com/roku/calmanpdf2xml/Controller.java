package com.roku.calmanpdf2xml;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {
        final String defaultTemplate = "";
  
	public static void parse(String basebuild,String newbuild,String title,String outputTemplateFile,String baseFileName,String newFileName,String outputFile) {
            //get base input
            PdfReader baseFile = new PdfReader(baseFileName);
            double[][] baseFileContent = baseFile.parseContent();
            //get new input
            PdfReader newFile = new PdfReader(newFileName);
            double[][] newFileContent = newFile.parseContent();
                              
            //fill xls
            XmlReader xmlReader = new XmlReader(outputTemplateFile);
            
            xmlReader.updateTitle(title);
            xmlReader.updateBaseVersionBuild(basebuild);
            xmlReader.updateNewVersionBuild(newbuild);
            xmlReader.updateBaseVersionData(baseFileContent);
            xmlReader.updateNewVersionData(newFileContent);     
            xmlReader.updateCompleteDate(baseFileName, newFileName);
            xmlReader.updateEvaluation();
            try {
                xmlReader.save(outputFile);
                //output
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            
	}
}
