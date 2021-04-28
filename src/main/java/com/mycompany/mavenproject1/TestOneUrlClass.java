/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author user
 */
public class TestOneUrlClass {
    public int loops;
    public int millisecondsToWait;
    public int millisecondsToWaitStepIncrease;    
    public HelperClass helperClass = new HelperClass();
    public CredentialsClass credentialsClass = new CredentialsClass();
    public String dateTimeOfSession;
    public File fileToWriteLogsOfTesting;
    public File fileToWriteErrorLogOfTesting;
    public WebDriver webDriver = null;
    public int typeOfTest;
    public StringBuffer strBuffer = new StringBuffer("");
    public String urlToTest = "http://maxbarannyk.ru/laravel/public/index.php";
    private String pathToLogFileFolder;
    private String osName;
    
    public TestOneUrlClass(String pathToFileFolderIn, String osNameIn) {
        this.pathToLogFileFolder = pathToFileFolderIn;
        this.osName = osNameIn;
    }
    
    public void testUrl() throws IOException, MalformedURLException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        System.out.println("This test determines how many page elements have been loaded after a specified time when the web page is constantly refreshed.");
        System.out.println("Do you want to start step ascending test?");
        System.out.println("Ascending time of pauses - 1");
        System.out.println("Constant time of pauses - 2");
        typeOfTest = Integer.parseInt(br.readLine());
        if(typeOfTest < 1 || typeOfTest >2) {
            typeOfTest = 1;
        }
                
        System.out.println("Set how many loops 1 - 100");
        loops = Integer.parseInt(br.readLine());
        
        if (loops < 1 || loops > 100) {
            loops = 10;
            System.out.println("Wrong input, set to 10");
        }
        
        if (typeOfTest == 1) {         
            System.out.println("Set milliseconds of first pause form 10 to 10000");
        } else if(typeOfTest == 2){
            System.out.println("Set milliseconds of pause 10 - 10000");
        }        
        millisecondsToWait = Integer.parseInt(br.readLine());
        
        if (millisecondsToWait < 1 || millisecondsToWait > 10000) {
            millisecondsToWait = 100;            
            System.out.println("Wrong input, set to 100");
        }
        
        if (typeOfTest == 1) {            
            System.out.println("Set milliseconds of pause increasing 10 - 10000 - for ascending test");
            millisecondsToWaitStepIncrease = Integer.parseInt(br.readLine());
            if (millisecondsToWaitStepIncrease < 1 || millisecondsToWaitStepIncrease > 10000) {
                millisecondsToWaitStepIncrease = 100;            
                System.out.println("Wrong input, set to 100");
            }
        }
        
        System.out.println("Set url to test, default is " + urlToTest);
        urlToTest = br.readLine();
        if (urlToTest.length() < 5) {
            urlToTest = "http://maxbarannyk.ru/laravel/public/index.php";
        }
        System.out.println("URL to test:" + urlToTest);
        visitOneUrl();
    }
    
    private void visitOneUrl() {
        preLoader();
        try {
            webDriver = new ChromeDriver();
            //WebDriver webDriver = new FirefoxDriver();
            JavascriptExecutor js = (JavascriptExecutor)webDriver;
            webDriver.manage().window().maximize();
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: go to url " + urlToTest);
            
            //first get to url and get it length
            webDriver.get(urlToTest);
            Thread.sleep(3500);
            //webDriver.getPageSource();
            final int normalLengthOfPage = webDriver.getPageSource().length();
            String message = "On the url " + urlToTest + " found length " + normalLengthOfPage + " after delay 3500MS";
            System.out.println(message);
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, message);
            Thread.sleep(1500);            
            
            String message2 = "URL TO TEST " + urlToTest;
            System.out.println(message2);
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, message2);
            testLoopLoadFunction(normalLengthOfPage);            
            
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: Accumulated message: \r" + strBuffer.toString());
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: END"); 
            System.out.println("Work: END");
            Thread.sleep(5000);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                } finally {
                    webDriver.close();
                    webDriver.quit();
                }
    }
    
    private void preLoader() {
        dateTimeOfSession = helperClass.getDateInStringForWindowsLinux();    
        String fileName = "";
        //String fileNameERRORS = "";
        
        fileName = this.pathToLogFileFolder + "testOneUrlLogFile_" + dateTimeOfSession + ".txt";
        System.out.println("Path to logfile:" + fileName);
        
        try {
            fileToWriteLogsOfTesting = new File(fileName);
            //fileToWriteErrorLogOfTesting = new File(fileNameERRORS);
        } catch (Exception exx) {
            System.out.println(exx.getMessage());
            System.out.println("Error file creation, testing log will be only in terminal");
        }
        
        helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Page load testing starts at: " + dateTimeOfSession +" OS: " + osName);
    }
    
    private void testLoopLoadFunction(int normalLengthOfPage) throws InterruptedException {
        String message = "";
        if (typeOfTest == 1) {
            message = "STARTING ASCENDING TEST WITH NUMBER OF LOOPS: " + loops + " FIRST PAUSE: " + millisecondsToWait + "MS AND INCREASING STEP: " + millisecondsToWaitStepIncrease + "MS";                      
        } else if (typeOfTest == 2) {
            message = "STARTING TEST WITH NUMBER OF LOOPS:" + loops + "  AND PAUSES:" + millisecondsToWait + "MS";
        }
        System.out.println(message);
        helperClass.writeStringToFile(fileToWriteLogsOfTesting, message);
        String timeOfStart, timeAfterSleep;
        for (int i = 0; i < loops; i++ ){
                timeOfStart = new SimpleDateFormat("HH.mm.ss.SSS").format(new java.util.Date());
                webDriver.get(urlToTest);
                System.out.println("N " + (i+1) + " Pause=" + millisecondsToWait + "MS  ");
                strBuffer.append("N " + (i+1) + " Pause=" + millisecondsToWait + "MS  ");
                Thread.sleep(millisecondsToWait);
                if (typeOfTest == 1) {
                    millisecondsToWait += millisecondsToWaitStepIncrease;
                }
                timeAfterSleep = new SimpleDateFormat("HH.mm.ss.SSS").format(new java.util.Date());
                int currentLengthOfPage = webDriver.getPageSource().length();
                String tmpInfo = "Start time=" + timeOfStart + " read time=" + timeAfterSleep + "  Sample length was=" + normalLengthOfPage 
                        + "  get length now=" + currentLengthOfPage +  "  difference abs=" + (normalLengthOfPage-currentLengthOfPage) + " or "+ ((normalLengthOfPage-currentLengthOfPage)*100/normalLengthOfPage) + "%";
                System.out.println(tmpInfo);
                strBuffer.append(tmpInfo);
                strBuffer.append("\r");
            }
    }
}
