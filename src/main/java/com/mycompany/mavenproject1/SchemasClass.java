/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import static com.mycompany.mavenproject1.HelperClass.selectOneElementFromDropdownInHeper;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
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
public class SchemasClass {
    
    public static String SchemaName = "TestSchemaName";
    public static String SchemaSectionName = "TestSectionName";
    public static String SchemaCode = "TestCode";
    public static String SchemaNumber = "TestSectionNumber";
    public static String SchemaValidity = "TestShemaValidity";
    public static String SchemaDescription = "TestShemaDescription";
    public static String SchemaExamName = "TestShemaExamName";
    public static String SchemaTextInIframe = "Test_Text_Text_Text";
    
    public void createSchema() throws InterruptedException
    {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver"); 
        WebDriver browser = new ChromeDriver();
        //WebDriver browser = new FirefoxDriver();
        JavascriptExecutor js = (JavascriptExecutor)browser;
        browser.manage().window().maximize();
        browser.get("https://perscriptum-dev.herokuapp.com/"); 
        WebElement login = browser.findElement(By.id("input-11"));
        WebElement passwd = browser.findElement(By.id("input-14"));
        WebElement btnLogin = browser.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[2]/div/form/button"));
        login.sendKeys("test2@pernexus.org");
        passwd.sendKeys("testtest2");
        Thread.sleep(500);
        btnLogin.click();
        Thread.sleep(1500);  
        //Logger.global.log(new LogRecord(Level.INFO, "Login complete"));
        
        browser.get("https://perscriptum-dev.herokuapp.com/schemes");
        Thread.sleep(1000);
        
        WebElement createBtn = browser.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div/div/header/div/button[2]"));
        createBtn.click();
        Thread.sleep(400);
        WebElement inputForLocationName = browser.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div > div > div.v-input__slot > div > input"));
        Thread.sleep(400);
        inputForLocationName.sendKeys(SchemaName);
        browser.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > header > div > button.v-btn.v-btn--text.theme--dark.v-size--small")).click();
        Thread.sleep(2000);
        
        //browser.get("https://perscriptum-dev.herokuapp.com/schemes/20");//only testing
        //Section name
        browser.findElement(By.cssSelector("#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.v-tabs.theme--light > div.v-window.v-item-group.theme--light.v-tabs-items > div > div > div > div > div > div > div > div:nth-child(1) > div:nth-child(2) > div > div.v-input__slot > div > input")).sendKeys(SchemaSectionName);
        //Code
        browser.findElement(By.cssSelector("#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.v-tabs.theme--light > div.v-window.v-item-group.theme--light.v-tabs-items > div > div > div > div > div > div > div > div:nth-child(1) > div:nth-child(3) > div > div.v-input__slot > div > input")).sendKeys(SchemaCode);
        //Number
        browser.findElement(By.cssSelector("#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.v-tabs.theme--light > div.v-window.v-item-group.theme--light.v-tabs-items > div > div > div > div > div > div > div > div:nth-child(1) > div:nth-child(4) > div > div.v-input__slot > div > input")).sendKeys(SchemaNumber);
        //Validity
        browser.findElement(By.cssSelector("#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.v-tabs.theme--light > div.v-window.v-item-group.theme--light.v-tabs-items > div > div > div > div > div > div > div > div:nth-child(1) > div.row > div.col.col-2 > div > div > div.v-input__slot > div.v-text-field__slot > input")).sendKeys(SchemaValidity);
        //Description
        browser.findElement(By.cssSelector("#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.v-tabs.theme--light > div.v-window.v-item-group.theme--light.v-tabs-items > div > div > div > div > div > div > div > div:nth-child(1) > div.v-input.v-textarea.theme--light.v-text-field.v-text-field--is-booted > div > div.v-input__slot > div > textarea")).sendKeys(SchemaDescription);
        //ExamName
        browser.findElement(By.cssSelector("#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.v-tabs.theme--light > div.v-window.v-item-group.theme--light.v-tabs-items > div > div > div > div > div > div > div > div:nth-child(2) > div.v-input.theme--light.v-text-field.v-text-field--is-booted > div > div.v-input__slot > div > input")).sendKeys(SchemaExamName);
        //select tags START
        Thread.sleep(200);
        WebElement tagsContainer = browser.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div[1]/div[2]/div/div/div/div/div/div/div/div[2]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]"));
        tagsContainer.click();
        WebElement tagsList = browser.findElement(By.xpath("//*[contains(@class,'menuable__content__active v-autocomplete__content')]"));
        Thread.sleep(200);
        List<WebElement> tagsInList = tagsList.findElements(By.className("v-list-item--link"));
        tagsInList.get(0).click();
        //select tags END
        
        
        //click to show dropdown
        browser.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div[1]/div[2]/div/div/div/div/div/div/div/div[1]/div[5]")).click();        
        //Dropdown shemeowner
        selectOneElementFromDropdownInHeper(browser);        
        browser.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div[1]/div[1]/div[2]/div/div[2]")).click();
        Thread.sleep(500);
        System.out.println("First element clicked finally");
        //click to show dropdown
        browser.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div[1]/div[2]/div/div/div/div/div/div/div/div[1]/div[6]")).click();        
        //Dropdown examenbureau
        selectOneElementFromDropdownInHeper(browser);
        browser.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div[1]/div[1]/div[2]/div/div[2]")).click();
        Thread.sleep(500);
        //Dropdown Validity choise
        browser.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div[1]/div[2]/div/div/div/div/div/div/div/div[1]/div[7]")).click();
        selectOneElementFromDropdownInHeper(browser);
        browser.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div[1]/div[1]/div[2]/div/div[2]")).click();
        Thread.sleep(500);
        
        //end result
        browser.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div[1]/div[2]/div/div/div/div/div/div/div/div[1]/div[8]")).click();
       
        selectOneElementFromDropdownInHeper(browser);
        Thread.sleep(500);
        WebElement alternativeSertif = null;
        try {
            System.out.println("TRY");
            Thread.sleep(500);//
            alternativeSertif = browser.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div[1]/div[2]/div/div/div/div/div/div/div/div[1]/div[9]"));
            if (alternativeSertif != null) {
                alternativeSertif.click();
                selectOneElementFromDropdownInHeper(browser);  
                System.out.println("alternativeSertif non null");
            }
        }
        catch(NoSuchElementException nex) {
            System.out.println("====================Sertificate element not found");
            System.out.println(nex.getMessage());
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        browser.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div[1]/div[2]/div/div/div/div/div/div/div/div[2]/div[2]/div/div[1]/div[2]/div[1]/iframe")).sendKeys("Test text to iframe");
         //
        
        
        
        Thread.sleep(3000);
        browser.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div[1]/div[1]/div[2]/div/header/div/button[2]")).click();
    }
    
//    public void selectOneElementFromDropdown(WebDriver browser) throws InterruptedException
//    {        
//        WebElement listContainerElement = browser.findElement(By.className("menuable__content__active"));
//        //Logger.global.log(new LogRecord(Level.INFO, "selectOneElementFromDropdown starts"));
//        Thread.sleep(1000);
//        List<WebElement> listElements = listContainerElement.findElements(By.className("v-list-item--link"));
//        int randomNumberOfElement = (int)(Math.random() * listElements.size());        
//        Thread.sleep(500);
//        if (listElements.size() > 0) {
//        //Logger.global.log(new LogRecord(Level.INFO, "selectOneElementFromDropdown click on element in list"));
//            listElements.get(randomNumberOfElement).click(); 
//            //listElements.get(1).click(); 
//            System.out.println("AFTER CLICK ON TAG"); 
//            Thread.sleep(500);
//            browser.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div[1]/div[2]/div/div/div/div/div/div")).click();
//        } else {
//            System.out.println("Error, listElements.size() = " + listElements.size());
//        }
//    }
    
    
}
