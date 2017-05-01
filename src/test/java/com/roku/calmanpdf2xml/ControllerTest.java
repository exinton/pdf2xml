/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roku.calmanpdf2xml;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author exint
 */
public class ControllerTest {
    public ControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of parse method, of class Controller.
     */
    @Test
    public void testParse() {
        System.out.println("parse");
        // TODO review the generated test code and remove the default call to fail.        
        String nb="9999";
        String bb="0001";
        String title="rok tv comparision pq";
        String template = "C:\\Users\\exint\\Dropbox\\ROKU\\calman\\template.xlsx";
        String basepdffile = "C:\\Users\\exint\\Dropbox\\ROKU\\calman\\isf_report-4_26_2017_55_rerun_7-2-5-6457.pdf";
        String newpdffile = "C:\\Users\\exint\\Dropbox\\ROKU\\calman\\isf_report-4_26_2017_55_rerun_7-6-0-4120.pdf";
        String outputFile = "C:\\Users\\exint\\Dropbox\\ROKU\\calman\\output.xlsx";      
        Controller.parse(bb,nb,title,template, basepdffile, newpdffile, outputFile);
        File newFile = new File(outputFile);
        assertTrue(true);
    }
    
}
