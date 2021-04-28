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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author user
 */
public class TestClass {
    
    public HelperClass helperClass = new HelperClass();
    public int testValue;
    public File fileToWriteLogsOfTesting;
    public CredentialsClass credentialsClass;
    
    public TestClass(){
        System.out.println("TestClass Empty constructor ");
        this.testValue = 0;
    }
    
    public TestClass(int value1){
        this.testValue = value1;
        System.out.println("TestClass Constructor with parameters" + this.testValue + " " + value1);
    }
    
    public void testFunction() throws InterruptedException, IOException{
        //testWeb();
//       
        
    }   
    

    
//    public void testWeb()
//    {
    //credentialsClass = new CredentialsClass();
//        String osName = System.getProperty("os.name");
//        if (osName.contains("Linux")) {
//            System.out.println("Set webdriver.chrome.driver from path /usr/bin/chromedriver");
//            System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver"); 
//        } else if (osName.contains("Windows 10")) {
//            System.out.println("Set webdriver.chrome.driver from path C:\\chromedriver.exe");
//            System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe"); 
//        }
//        WebDriver webDriver = null;
//        try {
//            webDriver = new ChromeDriver();
//            //WebDriver webDriver = new FirefoxDriver();
//            JavascriptExecutor js = (JavascriptExecutor)webDriver;
//            webDriver.manage().window().maximize();
//            webDriver.get("https://perscriptum-dev.herokuapp.com/"); 
//            Thread.sleep(1500);
//            WebElement login = helperClass.safeFindElement(webDriver, "input-11", "id");
//            WebElement passwd = helperClass.safeFindElement(webDriver, "input-14", "id");
//            WebElement btnLogin = helperClass.safeFindElement(webDriver, "/html/body/div[1]/div/div/div[2]/div/div/div[2]/div/form/button", "xpath");
//            login.sendKeys(credentialsClass.emailToLogin);
//            passwd.sendKeys(credentialsClass.passwordToLogin);
//            Thread.sleep(500);
//            helperClass.safeClickOnElement(btnLogin);
//            Thread.sleep(2500);  
//            //login to site END
//            
//            
//            
//            Thread.sleep(5000);
//            
//            } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        } finally {
//            webDriver.close();
//            webDriver.quit();
//        }
//    }
    
    
}
