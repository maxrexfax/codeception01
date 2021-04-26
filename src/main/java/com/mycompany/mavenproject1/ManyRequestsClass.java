package com.mycompany.mavenproject1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.function.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class ManyRequestsClass {
    public int loops;
    public int millisecondsToWait;
    
    public HelperClass helperClass = new HelperClass();
    public String dateTimeOfSession;
    public File fileToWriteLogsOfTesting;
    public File fileToWriteErrorLogOfTesting;
    public WebDriver browser = null;
    public String mainUrl = "https://perscriptum-dev.herokuapp.com/";
    //public String mainUrl = "http://maxbarannyk.ru/laravel/public/index.php/login";
    public String urlToTest = "https://perscriptum-dev.herokuapp.com/candidates";
    //public String urlToTest = "http://maxbarannyk.ru/laravel/public/index.php/users/list";
    //public String mainUrl = "http://maxbarannyk.ru/";
    public CredentialsClass credentialsClass;
    public int typeOfTest;
    public StringBuffer strBuffer = new StringBuffer("");
    public String inputLoginData = "input-11";
    public String inputLoginData1 = "email";
    public String inputPasswordData = "input-14";
    public String inputPasswordData1 = "password";
    public String buttonToLoginPath = "/html/body/div[1]/div/div/div[2]/div/div/div[2]/div/form/button";
    public String buttonToLoginPath1 = "/html/body/div[1]/main/div/div/div/div/div[2]/form/div[4]/div/button";
    
    public void startTest() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        System.out.println("This test determines how many page elements have been loaded after a specified time when the web page is constantly refreshed.");
        System.out.println("We recommend at first to start ascending test (step 100ms) than you can use optimal time from it result to start LOOP test");
        System.out.println("Do you want to start step ascending test? (step 100ms)");
        System.out.println("Ascending time - 1");
        System.out.println("User defined - 2");
        typeOfTest = Integer.parseInt(br.readLine());
        if(typeOfTest < 1 || typeOfTest >2) {
            typeOfTest = 1;
        }
        
        if (typeOfTest != 1) {
            System.out.println("Set how many loops 1 - 100");
            loops = Integer.parseInt(br.readLine());
            if (loops < 1 || loops > 100) {
                loops = 10;
                System.out.println("Wrong input, set to 10");
            }
            System.out.println("Set milliseconds of pause 10 - 10000 - use ascending test to see best result of full page load");
            millisecondsToWait = Integer.parseInt(br.readLine());
            if (millisecondsToWait < 10 || millisecondsToWait > 10000) {
                millisecondsToWait = 100;            
                System.out.println("Wrong input, set to 100");
            }
        }
//        System.out.println("Set url, default is " + mainUrl);
//        urlToTest = br.readLine();
//        if (urlToTest.length() < 5) {
//            urlToTest = "https://perscriptum-dev.herokuapp.com/candidates";
//        }
        loginAndTestInternalUrlFunction();
        //visitOneUrl();
        
    }
    
    private void preLoader() {
        credentialsClass = new CredentialsClass();
        dateTimeOfSession = helperClass.getDateInStringForWindowsLinux();    
        String fileName = "";
        String fileNameERRORS = "";
        
        //login to site START
        String osName = System.getProperty("os.name");
        
        if (osName.contains("Linux")) {
            System.out.println("Set webdriver.chrome.driver from path /usr/bin/chromedriver");
            System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver"); 
            fileName = "./logs/testPageloadLogFile_" + dateTimeOfSession + ".txt";
            fileNameERRORS = "./logs/testPageload_ERRORS_LogFile_" + dateTimeOfSession + ".txt";
        } else if (osName.contains("Windows")) {
            System.out.println("Set webdriver.chrome.driver from path C:\\chromedriver.exe");            
            System.out.println("All logs will be stored at folder C:\\users\\public\\documents\\logs"); 
            System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
            fileName = "C:\\users\\public\\documents\\logs\\testPageloadLogFile_" + dateTimeOfSession + ".txt";
            fileNameERRORS = "C:\\users\\public\\documents\\logs\\testPageload_ERRORS_LogFile_" + dateTimeOfSession + ".txt";
        } else {
            System.out.println("ERROR checking OS type");             
            fileName = "./testPageloadLogFile_" + dateTimeOfSession + ".txt";            
        }
        
        try {
            fileToWriteLogsOfTesting = new File(fileName);
            fileToWriteErrorLogOfTesting = new File(fileNameERRORS);
        } catch (Exception exx) {
            System.out.println(exx.getMessage());
            System.out.println("Error file creation, test log will be only in terminal");
        }
        
        helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Page load testing starts at: " + dateTimeOfSession +" OS: " + osName);
    }
    
    private void loginAndTestInternalUrlFunction() {
        
        preLoader();
        try {
            browser = new ChromeDriver();
            //WebDriver browser = new FirefoxDriver();
            JavascriptExecutor js = (JavascriptExecutor)browser;
            browser.manage().window().maximize();
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: go to url " + mainUrl);
            browser.get(mainUrl);
            Thread.sleep(1500); 
            helperClass.safeFindElement(browser, "#materialpro > div > div > div.d-flex.align-center.col-lg-5.col-xl-6.col-12 > div > div > div.v-item-group.theme--light.v-btn-toggle > button:nth-child(2)", "cssSelector").click();
            Thread.sleep(2500);
            WebElement login = browser.findElement(By.id(inputLoginData));
            WebElement passwd = browser.findElement(By.id(inputPasswordData));
            WebElement btnLogin = browser.findElement(By.xpath(buttonToLoginPath));
            login.sendKeys(credentialsClass.emailToLogin);
            passwd.sendKeys(credentialsClass.passwordToLogin);
//            login.sendKeys("max@ya.ru");
//            passwd.sendKeys("1234");
            Thread.sleep(500);            
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: trying to login");
            btnLogin.click();
            Thread.sleep(2500);
            //first get to url and get it length
            browser.get(urlToTest);
            Thread.sleep(3500);
            //browser.getPageSource();
            final int normalLengthOfPage = browser.getPageSource().length();
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
            Thread.sleep(5000);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                } finally {
                    browser.close();
                    browser.quit();
                }
    }
    
    private void visitOneUrl() {
        preLoader();
        urlToTest = "http://maxbarannyk.ru/laravel/public/index.php";
        try {
            browser = new ChromeDriver();
            //WebDriver browser = new FirefoxDriver();
            JavascriptExecutor js = (JavascriptExecutor)browser;
            browser.manage().window().maximize();
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: go to url " + urlToTest);
            
            //first get to url and get it length
            browser.get(urlToTest);
            Thread.sleep(3500);
            //browser.getPageSource();
            final int normalLengthOfPage = browser.getPageSource().length();
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
            Thread.sleep(5000);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                } finally {
                    browser.close();
                    browser.quit();
                }
    }

    private void testLoopLoadFunction(int normalLengthOfPage) throws InterruptedException {
        String message = "";
        if (typeOfTest == 1) {
            message = "STARTING ASCENDING TEST WITH NUMBER OF LOOPS: 20; FIRST PAUSE: 100MS AND INCREASING STEP 100MS";  
            loops = 20;
            millisecondsToWait = 100;          
        } else if (typeOfTest == 2) {
            message = "STARTING TEST WITH NUMBER OF LOOPS:" + loops + "  AND PAUSES:" + millisecondsToWait + "MS";
        }
        System.out.println(message);
        helperClass.writeStringToFile(fileToWriteLogsOfTesting, message);
        String timeOfStart, timeAfterSleep;
        for (int i = 0; i < loops; i++ ){
                timeOfStart = new SimpleDateFormat("HH.mm.ss.SSS").format(new java.util.Date());
                browser.get(urlToTest);
                System.out.println("N " + (i+1) + " Pause=" + millisecondsToWait + "MS  ");
                strBuffer.append("N " + (i+1) + " Pause=" + millisecondsToWait + "MS  ");
                Thread.sleep(millisecondsToWait);
                if (typeOfTest == 1) {
                    millisecondsToWait += 100;
                }
                timeAfterSleep = new SimpleDateFormat("HH.mm.ss.SSS").format(new java.util.Date());
                int currentLengthOfPage = browser.getPageSource().length();
                String tmpInfo = "Start time=" + timeOfStart + " read time=" + timeAfterSleep + "  Sample length was=" + normalLengthOfPage 
                        + "  get length now=" + currentLengthOfPage +  "  difference abs=" + (normalLengthOfPage-currentLengthOfPage) + " or "+ ((normalLengthOfPage-currentLengthOfPage)*100/normalLengthOfPage) + "%";
                System.out.println(tmpInfo);
                strBuffer.append(tmpInfo);
                strBuffer.append("\r");
            }
    }

   
}
