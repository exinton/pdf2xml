/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roku.calmanpdf2xml;

import java.util.Arrays;
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
public class PdfReaderTest {
    
    public PdfReaderTest() {
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
     * Test of parseLine method, of class PdfReader.
     */
    @Test
    public void testParseLine() {
        System.out.println("parseLine");
        String str = "x: CIE31 0.262 0.267 0.268 0.270 0.269 0.271 0.270 0.270 0.270 0.270";
        String pdffile = "C:\\Users\\exint\\Dropbox\\ROKU\\calman\\isf_report-4_26_2017_55_rerun_7-2-5-6457.pdf";
        PdfReader instance = new PdfReader(pdffile);
        double[] expResult = {0.262, 0.267, 0.268, 0.27, 0.269, 0.271, 0.27, 0.27, 0.27, 0.27};
        double[] result = instance.parseLine(str);
        assertTrue(Arrays.equals(expResult, result));
        //assertArrayEquals(expResult,result,0.0001);
        /**
        for(int i=0;i<expResult.length;i++){
            assertEquals(expResult[i],result[i],0.01);
            System.out.println(expResult[i]+" compare to "+result[i]);
        }
        * */
    }

    /**
     * Test of parseContent method, of class PdfReader.
     */
    @Test
    public void testParseContent() {
        System.out.println("parseContent");
        String pdffile = "C:\\Users\\exint\\Dropbox\\ROKU\\calman\\isf_report-4_26_2017_55_rerun_7-2-5-6457.pdf";
	PdfReader read = new PdfReader(pdffile);
        String content = read.content;
        double[][] expResult = {
            {0.262, 0.267, 0.268, 0.27, 0.269, 0.271, 0.27, 0.27, 0.27, 0.27},
            {0.272,0.281,0.279,0.281,0.279,0.283,0.282,0.281,0.282,0.283},
            {2.149,8.989,21.443,40.309,66.047,98.598,139.016,186.82,241.047,304.75},
            {13552,11977,12020,11696,11854,11365,11528,11615,11579,11443},
            {0.269,0.623,0.29,0.146,0.199,0.262,0.421,0.27,0,0},
            {0.281,0.334,0.62,0.054,0.265,0.123,0.506,0.283,0,0},
            {162.156,50.475,165.016,16.232,162.141,66.133,153.141,304.719,0,0}};
        double[][] result = read.parseContent(content);
        for(int i=0;i<result.length;i++)
            assertTrue(Arrays.equals(expResult[i], result[i]));
 
    }
    
}
