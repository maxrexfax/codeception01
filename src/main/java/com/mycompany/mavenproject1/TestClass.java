/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

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
    
    public enum Ids {
        Locations(1), 
        Schemas(2), 
        Candidates(3), 
        Company(4), 
        Testing(934);

        private int value;    

        private Ids(int value) {
          this.value = value;
        }

        public int getValue() {
          return value;
        }
    }
    
    public String[] typeNames = new String[15];
    
    public void testFunction() throws InterruptedException{
        String osName = System.getProperty("os.name");
        if (osName.contains("Linux")) {
            System.out.println("Set webdriver.chrome.driver from path /usr/bin/chromedriver");
            System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver"); 
        } else if (osName.contains("Windows 10")) {
            System.out.println("Set webdriver.chrome.driver from path C:\\chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe"); 
        }
        WebDriver browser = null;
        try {
            browser = new ChromeDriver();
            //WebDriver browser = new FirefoxDriver();
            JavascriptExecutor js = (JavascriptExecutor)browser;
            browser.manage().window().maximize();
            browser.get("https://perscriptum-dev.herokuapp.com/"); 
            Thread.sleep(1500);
            WebElement login = helperClass.safeFindElement(browser, "input-11", "id");
            WebElement passwd = helperClass.safeFindElement(browser, "input-14", "id");
            WebElement btnLogin = helperClass.safeFindElement(browser, "/html/body/div[1]/div/div/div[2]/div/div/div[2]/div/form/button", "xpath");
            helperClass.safeFillInput(login, "test2@pernexus.org");
            helperClass.safeFillInput(passwd, "testtest2");
            Thread.sleep(500);
            helperClass.safeClickOnElement(btnLogin);
            Thread.sleep(2500);  
            //login to site END
            
            
            
            Thread.sleep(5000);
            
            } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            browser.close();
            browser.quit();
        }
    }   
    
public void backupCode() throws InterruptedException{
//for(int i = 0; i < 20; i++) {
            System.out.println("Try to find inputs");
        //}
        
        WebDriver browser = new FirefoxDriver();
        browser.get("http://loc.loc/test.php"); 
        WebElement input0 = browser.findElement(By.id("test"));
        input0.sendKeys("test data 00");
        Thread.sleep(1000);
//        try {
//            System.out.println("FIRST Try to find id=test1 BEFORE CLICK ON BUTTON");
//            WebElement input1 = browser.findElement(By.id("test1"));
//            input1.sendKeys("test data 11");
//        }catch(NoSuchElementException nex) {
//            System.out.println("Input id=test1 not found BEFORE CLICK ON BUTTON");
//            System.out.println(nex.getMessage());
//        }
        
        Thread.sleep(2000);
        WebElement btn = browser.findElement(By.id("inputAdder"));
        btn.click();
        Thread.sleep(2000);
        
        try {
            System.out.println("Try to find id=test1 AFTER CLICK");
            //WebElement input01 = browser.findElement(By.id("test1"));////*/html/body/input[2]
            WebElement input01 = browser.findElement(By.xpath("/html/body/input[2]"));
            input01.sendKeys("test data 11");
        Thread.sleep(2000);
        }catch(NoSuchElementException nex) {
            System.out.println("Input id=test1 not found AFTER CLICK");
            System.out.println(nex.getMessage());
        Thread.sleep(2000);
        }
        
        Thread.sleep(10000);
//        try {
//            System.out.println("SECOND Try to find id=test1 AFTER CLICK PLUS NEW BROWSER");
//            WebDriver browser1 = new FirefoxDriver();
//            WebElement input01 = browser1.findElement(By.id("test1"));
//            input01.sendKeys("test data 11");
//        }catch(NoSuchElementException nex) {
//            System.out.println("Input id=test1 not found AFTER CLICK PLUS NEW BROWSER");
//            System.out.println(nex.getMessage());
//        }
}
    
}
