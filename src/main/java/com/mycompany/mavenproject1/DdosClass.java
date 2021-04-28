/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author user
 */
public class DdosClass {
    
    public int loops;
    public int millisecondsToWait;
    public int millisecondsToWaitStepIncrease;    
    public HelperClass helperClass = new HelperClass();
    public File fileToWriteLogsOfTesting;
    public String dateTimeOfSession;
    private String pathToLogFileFolder;
    private String osName;
    public URL url;
    public String urlToTest = "https://perscriptum-dev.herokuapp.com/";
    public StringBuffer strBuffer = new StringBuffer("");
    
    public DdosClass(String pathToFileFolderIn, String osNameIn) {
        this.pathToLogFileFolder = pathToFileFolderIn;
        this.osName = osNameIn;
    }
     
    public void startWork() throws IOException, InterruptedException {
        System.out.println("Welcome to ddos test"); 
        beforeFunction();
        runTest();
    }
    
    private void runTest() throws IOException, InterruptedException {  
        Date startDateTime = new java.util.Date();
        strBuffer.append("Start ddos test with loops=" + loops + "  and pauses=" + millisecondsToWait + "MS\r");
        try{
            url = new URL(urlToTest);
            for (int i = 0; i < loops; i++ ){
                try {
                    URLConnection con = url.openConnection();
                    //InputStream in = con.getInputStream();                       
                    Thread.sleep(millisecondsToWait);
                } catch (Exception ex) {
                    System.out.println("Exception! Message: " + ex.getMessage()); 
                }                
            }
        } catch(Exception ex) {
            System.out.println("Exception! Message: " + ex.getMessage()); 
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Exception! Message: " + ex.getMessage());
        } finally {
            Date endDateTime = new java.util.Date();
            strBuffer.append("Test time: " + (int)(getDateDiffSeconds(startDateTime,endDateTime,TimeUnit.MILLISECONDS)) + "seconds");
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, strBuffer.toString());
        }
        
    }
    
    public long getDateDiffSeconds(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.SECONDS);
    }
    
    private void beforeFunction() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
                
        System.out.println("Set how many loops 1 - 100");
        loops = Integer.parseInt(br.readLine());
        
        if (loops < 1 || loops > 100) {
            loops = 10;
            System.out.println("Wrong input, set to 10");
        }     
        
        System.out.println("Set milliseconds of pause 10 - 10000");
        millisecondsToWait = Integer.parseInt(br.readLine());
        if (millisecondsToWait < 1 || millisecondsToWait > 10000) {
            millisecondsToWait = 400;            
            System.out.println("Wrong input, set to 400");
        }
        
        System.out.println("Set url to test, default is " + urlToTest);
        urlToTest = br.readLine();
        if (urlToTest.length() < 5) {
            urlToTest = "https://perscriptum-dev.herokuapp.com/";
        }
        System.out.println("URL to test:" + urlToTest);
    }
}
