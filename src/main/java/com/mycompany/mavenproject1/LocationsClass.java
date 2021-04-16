/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author user
 */
public class LocationsClass {
    public void createLocation() throws InterruptedException
    {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver"); 
        //WebDriver browser = new ChromeDriver();
        WebDriver browser = new FirefoxDriver();
        JavascriptExecutor js = (JavascriptExecutor)browser;
        String script00 = "(console.log('1111!!!');"; 
        //browser.manage().window().maximize();
        browser.get("https://perscriptum-dev.herokuapp.com/"); 
        WebElement login = browser.findElement(By.id("input-11"));
        WebElement passwd = browser.findElement(By.id("input-14"));
        WebElement btnLogin = browser.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[2]/div/form/button"));
        login.sendKeys("test2@pernexus.org");
        passwd.sendKeys("testtest2");
        Thread.sleep(500);
        btnLogin.click();
        Thread.sleep(1500);  
        Logger.global.log(new LogRecord(Level.INFO, "Login complete"));
        //String script02 = "console.log(\"2222!!!\");";
        //startThread(script02, js);    
        /*String urlExpected = "https://perscriptum-dev.herokuapp.com/";
        String realUrl = browser.getCurrentUrl();  
        
        if(realUrl.contains(urlExpected))
        {
            System.out.println("Test passed");
        }
        else
        {
            System.out.println("Test failed");
        }*/
        //Creating the JavascriptExecutor interface object by Type casting		
        browser.get("https://perscriptum-dev.herokuapp.com/locations");
        Thread.sleep(1000);
        WebElement createBtn = browser.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div/div/header/div/button[2]"));
        createBtn.click();
        Thread.sleep(400);
        WebElement inputForLocationName = browser.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div > div > div.v-input__slot > div > input"));
        Thread.sleep(400);
        inputForLocationName.sendKeys("TestNameLocation");
        browser.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > header > div > button.v-btn.v-btn--text.theme--dark.v-size--small")).click();
        
        Thread.sleep(2000);
        WebElement mapInput = browser.findElement(By.id("map"));
        mapInput.click();
        mapInput.sendKeys(getRandChar());
        Thread.sleep(200);
        WebElement containerOfResults = browser.findElement(By.xpath("//*[contains(@class,'pac-container pac-logo')]"));
        Thread.sleep(200);
        List<WebElement> elements = containerOfResults.findElements(By.className("pac-item"));
        elements.get(0).click();
        browser.findElement(By.xpath("/html/body/div[1]/div/div/div/main/div/div/div/div[2]/div/div/div[1]/div[1]/div[2]/div/div[2]")).click();
        Thread.sleep(200);
        WebElement tagsContainer = browser.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div[1]/div[2]/div/div/div/div/div/div/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]"));
        tagsContainer.click();
        WebElement tagsList = browser.findElement(By.xpath("//*[contains(@class,'menuable__content__active v-autocomplete__content')]"));
        Thread.sleep(200);
        List<WebElement> tagsInList = tagsList.findElements(By.className("v-list-item--link"));
        tagsInList.get(0).click();
        browser.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div[1]/div[1]/div[2]/div/div[2]")).click();
        browser.findElement(By.cssSelector("#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.v-tabs.theme--light > div.v-item-group.theme--light.v-slide-group.v-tabs-bar.primary--text > div.v-slide-group__wrapper > div > header > div > button.v-btn.v-btn--text.theme--light.v-size--small.primary--text")).click();
        Thread.sleep(12000);
        
        
        try{
            
            Logger.global.log(new LogRecord(Level.INFO, "try to click button"));//
            //browser.findElement(By.cssSelector("#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div > div > header > div > button.v-btn.v-btn--text.theme--light.v-size--small.primary--text > span")).click();
            //browser.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div/div/header/div/button[2]")).click();
            Thread.sleep(14000);
        } catch(Exception ex) {
            Logger.global.log(new LogRecord(Level.INFO, "Exception"));
            System.out.println("----------- Exception!");
            System.out.println(ex.getMessage());
        } finally {
            Logger.global.log(new LogRecord(Level.INFO, "finally"));                         
            browser.close();
        }
    }
    
    public static void startThread(String commandJs, JavascriptExecutor js)
    {
        Thread thread = new Thread(){
            public void run(){
              System.out.println("Thread Running and sending to JS:" + commandJs);
              js.executeAsyncScript(commandJs);
            }
        };
        thread.start();
    }
    
    private static String getRandChar() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int randomNumberOfChar = (int)(Math.random() * alphabet.length());
        return String.valueOf(alphabet.charAt(randomNumberOfChar));
    }
}
