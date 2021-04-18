/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author user
 */
public class TestClass {
    
    public void testFunction() throws InterruptedException{
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
            Logger.global.log(new LogRecord(Level.INFO, "Try to find id=test1 AFTER CLICK"));
            //WebElement input01 = browser.findElement(By.id("test1"));////*/html/body/input[2]
            WebElement input01 = browser.findElement(By.xpath("/html/body/input[2]"));
            input01.sendKeys("test data 11");
        Thread.sleep(2000);
        }catch(NoSuchElementException nex) {
            Logger.global.log(new LogRecord(Level.INFO, "Input id=test1 not found AFTER CLICK"));
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
