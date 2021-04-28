/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

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
public class SchemasClass {
    
    public static String SchemaName = "TestSchemaName";
    public static String SchemaSectionName = "TestSectionName";
    public static String SchemaCode = "TestCode";
    public static String SchemaNumber = "TestSectionNumber";
    public static String SchemaValidity = "TestShemaValidity";
    public static String SchemaDescription = "TestShemaDescription";
    public static String SchemaExamName = "TestShemaExamName";
    public static String SchemaTextInIframe = "Test_Text_Text_Text";
    public HelperClass helperClass = new HelperClass();
    public CredentialsClass credentialsClass;
    
    public void createSchema() throws InterruptedException
    {
        credentialsClass = new CredentialsClass();
        String osName = System.getProperty("os.name");
        if (osName.contains("Linux")) {
            System.out.println("Set webdriver.chrome.driver from path /usr/bin/chromedriver");
            System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver"); 
        } else if (osName.contains("Windows 10")) {
            System.out.println("Set webdriver.chrome.driver from path C:\\chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe"); 
        }
        WebDriver webDriver = null;
        try {
            webDriver = new ChromeDriver();
            //WebDriver webDriver = new FirefoxDriver();
            JavascriptExecutor js = (JavascriptExecutor)webDriver;
            webDriver.manage().window().maximize();
            webDriver.get("https://perscriptum-dev.herokuapp.com/");
            Thread.sleep(1500); 
            WebElement login = webDriver.findElement(By.id("input-11"));
            WebElement passwd = webDriver.findElement(By.id("input-14"));
            WebElement btnLogin = webDriver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[2]/div/form/button"));
            login.sendKeys(credentialsClass.emailToLogin);
            passwd.sendKeys(credentialsClass.passwordToLogin);
            Thread.sleep(500);
            btnLogin.click();
            Thread.sleep(1500);  
            //Logger.global.log(new LogRecord(Level.INFO, "Login complete"));

            webDriver.get("https://perscriptum-dev.herokuapp.com/schemes");
            Thread.sleep(1000);

            WebElement createBtn = webDriver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div/div/header/div/button[2]"));
            createBtn.click();
            Thread.sleep(400);
            WebElement inputForLocationName = webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div > div > div.v-input__slot > div > input"));
            Thread.sleep(400);
            inputForLocationName.sendKeys(SchemaName);
            webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > header > div > button.v-btn.v-btn--text.theme--dark.v-size--small")).click();
            Thread.sleep(2000);

            //webDriver.get("https://perscriptum-dev.herokuapp.com/schemes/20");//only testing
            //Section name
            webDriver.findElement(By.cssSelector("#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.v-tabs.theme--light > div.v-window.v-item-group.theme--light.v-tabs-items > div > div > div > div > div > div > div > div:nth-child(1) > div:nth-child(2) > div > div.v-input__slot > div > input")).sendKeys(SchemaSectionName);
            //Code
            webDriver.findElement(By.cssSelector("#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.v-tabs.theme--light > div.v-window.v-item-group.theme--light.v-tabs-items > div > div > div > div > div > div > div > div:nth-child(1) > div:nth-child(3) > div > div.v-input__slot > div > input")).sendKeys(SchemaCode);
            //Number
            webDriver.findElement(By.cssSelector("#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.v-tabs.theme--light > div.v-window.v-item-group.theme--light.v-tabs-items > div > div > div > div > div > div > div > div:nth-child(1) > div:nth-child(4) > div > div.v-input__slot > div > input")).sendKeys(SchemaNumber);
            //Validity
            webDriver.findElement(By.cssSelector("#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.v-tabs.theme--light > div.v-window.v-item-group.theme--light.v-tabs-items > div > div > div > div > div > div > div > div:nth-child(1) > div.row > div.col.col-2 > div > div > div.v-input__slot > div.v-text-field__slot > input")).sendKeys(SchemaValidity);
            //Description
            webDriver.findElement(By.cssSelector("#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.v-tabs.theme--light > div.v-window.v-item-group.theme--light.v-tabs-items > div > div > div > div > div > div > div > div:nth-child(1) > div.v-input.v-textarea.theme--light.v-text-field.v-text-field--is-booted > div > div.v-input__slot > div > textarea")).sendKeys(SchemaDescription);
            //ExamName
            webDriver.findElement(By.cssSelector("#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.v-tabs.theme--light > div.v-window.v-item-group.theme--light.v-tabs-items > div > div > div > div > div > div > div > div:nth-child(2) > div.v-input.theme--light.v-text-field.v-text-field--is-booted > div > div.v-input__slot > div > input")).sendKeys(SchemaExamName);
            //select tags START
            Thread.sleep(200);
            WebElement tagsContainer = webDriver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div[1]/div[2]/div/div/div/div/div/div/div/div[2]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]"));
            tagsContainer.click();
            WebElement tagsList = webDriver.findElement(By.xpath("//*[contains(@class,'menuable__content__active v-autocomplete__content')]"));
            Thread.sleep(200);
            List<WebElement> tagsInList = tagsList.findElements(By.className("v-list-item--link"));
            tagsInList.get(0).click();
            //select tags END


            //click to show dropdown
            webDriver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div[1]/div[2]/div/div/div/div/div/div/div/div[1]/div[5]")).click();        
            //Dropdown shemeowner
            helperClass.selectOneElementFromDropdownInHeper(webDriver);        
            webDriver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div[1]/div[1]/div[2]/div/div[2]")).click();
            Thread.sleep(500);
            System.out.println("First element clicked finally");
            //click to show dropdown
            webDriver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div[1]/div[2]/div/div/div/div/div/div/div/div[1]/div[6]")).click();        
            //Dropdown examenbureau
            helperClass.selectOneElementFromDropdownInHeper(webDriver);
            webDriver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div[1]/div[1]/div[2]/div/div[2]")).click();
            Thread.sleep(500);
            //Dropdown Validity choise
            webDriver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div[1]/div[2]/div/div/div/div/div/div/div/div[1]/div[7]")).click();
            helperClass.selectOneElementFromDropdownInHeper(webDriver);
            webDriver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div[1]/div[1]/div[2]/div/div[2]")).click();
            Thread.sleep(500);

            //end result
            webDriver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div[1]/div[2]/div/div/div/div/div/div/div/div[1]/div[8]")).click();

            helperClass.selectOneElementFromDropdownInHeper(webDriver);
            Thread.sleep(500);
            WebElement alternativeSertif = null;
            try {
                System.out.println("TRY");
                Thread.sleep(500);//
                alternativeSertif = webDriver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div[1]/div[2]/div/div/div/div/div/div/div/div[1]/div[9]"));
                if (alternativeSertif != null) {
                    alternativeSertif.click();
                    helperClass.selectOneElementFromDropdownInHeper(webDriver);  
                    System.out.println("alternativeSertif non null");
                }
            }
            catch(NoSuchElementException nex) {
                System.out.println(nex.getMessage());
            }
            catch(Exception ex) {
                System.out.println(ex.getMessage());
            }

            webDriver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div[1]/div[2]/div/div/div/div/div/div/div/div[2]/div[2]/div/div[1]/div[2]/div[1]/iframe")).sendKeys("Test text to iframe");
            
            Thread.sleep(1000);
            webDriver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div[1]/div[1]/div[2]/div/header/div/button[2]")).click();
            
            Thread.sleep(5000);
            } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            webDriver.close();
            webDriver.quit();
        }
    }
    
    
}
