package com.roku.calmanpdf2xml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLController implements Initializable {
    String currFolder=System.getProperty("user.home");
    @FXML
    Label choosedBase;
    @FXML
    TextField baseBuildVersion; 
    @FXML
    Label choosedNew; 
    @FXML
    TextField newBuildVersion; 
    @FXML
    Label templeFile; 
    @FXML
    TextField outputTitle;
    @FXML
    TextField tvMode;
            
    @FXML
    private void handleSelectSingleBaseModeFileAction(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose the base Mode pdf report");
        fileChooser.setInitialDirectory(new File(currFolder));
        fileChooser.getExtensionFilters().add(  new FileChooser.ExtensionFilter("PDF", "*.pdf"));
        File file = fileChooser.showOpenDialog(new Stage());
        System.out.println(file.getPath());
        choosedBase.setText(file.getPath());
        if(file.getParent()!=null)
            currFolder=file.getParent();
        
    }
    
    @FXML
    private void handleSelectSingleNewModeFileAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose the new Mode pdf report");
        fileChooser.setInitialDirectory(new File(currFolder));
        fileChooser.getExtensionFilters().add(  new FileChooser.ExtensionFilter("PDF", "*.pdf"));
        File file = fileChooser.showOpenDialog(new Stage());
        System.out.println(file.getPath());
        choosedNew.setText(file.getPath());
        if(file.getParent()!=null)
            currFolder=file.getParent();
    }
     @FXML
    private void handleSelectTemplateFileAction(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose the base Mode pdf report");
        fileChooser.setInitialDirectory(new File(currFolder));
        fileChooser.getExtensionFilters().add(  new FileChooser.ExtensionFilter("XLSX", "*.xlsx"));
        File file = fileChooser.showOpenDialog(new Stage());
        if(file.getParent()!=null)
            currFolder=file.getParent();
        templeFile.setText(file.getPath());
    }
    
    
    @FXML
    private void parse(ActionEvent event) {
        
        String nb=newBuildVersion.getText();
        String bb=baseBuildVersion.getText();
        
        String template = templeFile.getText();
        String basepdffile =choosedBase.getText();
        String newpdffile = choosedNew.getText(); 
        String rokuMode=tvMode.getText();
        String title=outputTitle.getText()+"_"+rokuMode+"  "+bb+"  vs  "+nb;
        String outputFileName=outputTitle.getText()+"_"+rokuMode+"_"+bb+"_"+nb+".xlsx"; 
        if(nb.length()==0 || bb.length()==0 || title.length()==0 || template.length()==0 || basepdffile==null 
                || newpdffile.length()==0 || rokuMode.length()==0 || outputFileName.length()==0){
                final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                VBox dialogVbox = new VBox(20);
                dialogVbox.getChildren().add(new Text("Please fill all text filed"));
                Scene dialogScene = new Scene(dialogVbox, 300, 200);
                dialog.setScene(dialogScene);
                dialog.show();
                return;
        }
        FileChooser fileChooser = new FileChooser();
         fileChooser.setTitle("Save output");
         fileChooser.setInitialDirectory(new File(currFolder));                
         fileChooser.setInitialFileName(outputFileName);
         fileChooser.getExtensionFilters().add(  new FileChooser.ExtensionFilter("XLSX", "*.xlsx"));
   
        
        File file = fileChooser.showSaveDialog(null);
        System.out.println(file.getPath());
        Controller.parse(bb,nb,title,template, basepdffile, newpdffile, file.getPath());

    }
    
        @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    private void initialize() {
        choosedBase.setText("Please choose the base version pdf report:");
        choosedNew.setText("Please choose the new version pdf report:");
    }    
    
    
}
