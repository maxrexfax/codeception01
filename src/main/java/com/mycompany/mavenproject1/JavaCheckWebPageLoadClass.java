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
import java.net.MalformedURLException;
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
public class JavaCheckWebPageLoadClass {
    
    public int loops;
    public int millisecondsToWait;
    public int millisecondsToWaitStepIncrease;  
    public int typeOfTest;
    public HelperClass helperClass = new HelperClass();
    public File fileToWriteLogsOfTesting;
    public String dateTimeOfSession;
    private String pathToLogFileFolder;
    private String osName;
    public String urlToTest = "http://172.17.0.1";
    public URL url;
    public StringBuffer strBuffer = new StringBuffer("");
    public int[] arrayOfIntervals;
    
    public JavaCheckWebPageLoadClass(String pathToFileFolderIn, String osNameIn) {
        this.pathToLogFileFolder = pathToFileFolderIn;
        this.osName = osNameIn;
    }
    
    public void checkWebPage() throws MalformedURLException, IOException, InterruptedException {
        dateTimeOfSession = helperClass.getDateInStringForWindowsLinux();  
        beforeFunction();
        String fileName = this.pathToLogFileFolder + "TerminalCheckWebPageLogFile_" + dateTimeOfSession + ".txt";
        System.out.println("Path to logfile:" + fileName);
        
        try {
            fileToWriteLogsOfTesting = new File(fileName);
        } catch (Exception exx) {
            System.out.println(exx.getMessage());
            System.out.println("Error file creation, testing log will be only in terminal");
        }        
        
        System.out.println("Terminal java NON browser web page testing starts at: " + dateTimeOfSession +" OS: " + osName);
        helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: Terminal java NON browser web page testing starts at: " + dateTimeOfSession +" OS: " + osName);
        helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: Url to test: " + urlToTest);
        
        runTest();
        int avg = getAvgTimelines();
        helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: AVG response time: " + avg);
        System.out.println("Work: AVG response time: " + avg);
        
        helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: END"); 
        System.out.println("Work: END");
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
            urlToTest = "http://172.17.0.1";
        }
        System.out.println("URL to test:" + urlToTest);
    }

    private void runTest() throws IOException, InterruptedException {//body.length()=15852
        
        arrayOfIntervals = new int[loops];
        try{
            url = new URL(urlToTest);//172.17.0.1
            for (int i = 0; i < loops; i++ ){
                //load starts datetime
                Date startDateTime = new java.util.Date();
                //System.out.println(new SimpleDateFormat("HH.mm.ss.SSS").format(new java.util.Date()));
                URLConnection con = url.openConnection();
                InputStream in = con.getInputStream();
                //load complete datetime
                Date endDateTime = new java.util.Date();
               // System.out.println(new SimpleDateFormat("HH.mm.ss.SSS").format(new java.util.Date()));
                String encoding = con.getContentEncoding();  // ** WRONG: should use "con.getContentType()" instead but it returns something like "text/html; charset=UTF-8" so this value must be parsed to extract the actual encoding
                encoding = encoding == null ? "UTF-8" : encoding;
                String body = IOUtils.toString(in, encoding);
                System.out.println("Check N " + (i+1) + " of " + loops);
                System.out.println("Work: Document length=" + body.length());
                strBuffer.append("Work: Check N ")
                        .append((i+1))
                        .append(" of ")
                        .append(loops)
                        .append(" Web document length=")
                        .append(body.length())
                        .append(" Web page load time ")
                        .append(getDateDiffMs(startDateTime,endDateTime,TimeUnit.MILLISECONDS))
                        .append(" MS")
                        .append("\r");
                //System.out.println(body);
                arrayOfIntervals[i] = (int)(getDateDiffMs(startDateTime,endDateTime,TimeUnit.MILLISECONDS));
                System.out.println("Work: Load time=" + getDateDiffMs(startDateTime,endDateTime,TimeUnit.MILLISECONDS));            
                Thread.sleep(millisecondsToWait);
            }
        } catch(Exception ex) {
            System.out.println("Exception! Message: " + ex.getMessage()); 
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Exception! Message: " + ex.getMessage());
        } finally {
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, strBuffer.toString());
        }
        
    }
    
    public long getDateDiffMs(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }

    private int getAvgTimelines() {
        int sum = 0;
        for (int i = 0; i < arrayOfIntervals.length; i++) {
            sum += arrayOfIntervals[i];
        }
        return sum/arrayOfIntervals.length;
    }
    
    
}
