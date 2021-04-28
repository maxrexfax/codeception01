/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author user
 */
public class LocationsClass {
    
    public HelperClass helperClass = new HelperClass();
    public CredentialsClass credentialsClass;
    
    public void createLocation() throws InterruptedException
    {
        credentialsClass = new CredentialsClass();
        HelperClass helperClass = new HelperClass();
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
            //String script00 = "(console.log('1111!!!');"; 
            webDriver.manage().window().maximize();
            webDriver.get("https://perscriptum-dev.herokuapp.com/"); 
            Thread.sleep(1500);
            helperClass.safeFindElement(webDriver, "#materialpro > div > div > div.d-flex.align-center.col-lg-5.col-xl-6.col-12 > div > div > div.v-item-group.theme--light.v-btn-toggle > button:nth-child(2)", "cssSelector").click();
            Thread.sleep(2500);
            //WebElement login = webDriver.findElement(By.id("input-11"));
            //WebElement passwd = webDriver.findElement(By.id("input-14"));
            //WebElement btnLogin = webDriver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[2]/div/form/button"));
            WebElement login = helperClass.safeFindElement(webDriver, "input-11", "id");
            WebElement passwd = helperClass.safeFindElement(webDriver, "input-14", "id");
            WebElement btnLogin = helperClass.safeFindElement(webDriver, "/html/body/div[1]/div/div/div[2]/div/div/div[2]/div/form/button", "xpath");

            //login.sendKeys("test2@pernexus.org");
            //passwd.sendKeys("testtest2");
            //btnLogin.click();
            login.sendKeys(credentialsClass.emailToLogin);
            passwd.sendKeys(credentialsClass.passwordToLogin);
            Thread.sleep(500);

            helperClass.safeClickOnElement(btnLogin);
            Thread.sleep(2500);  
            //Logger.global.log(new LogRecord(Level.INFO, "Login complete"));
            //String script02 = "console.log(\"2222!!!\");";
            //startThread(script02, js);    
            /*String urlExpected = "https://perscriptum-dev.herokuapp.com/";
            String realUrl = webDriver.getCurrentUrl();  

            if(realUrl.contains(urlExpected))
            {
                System.out.println("Test passed");
            }
            else
            {
                System.out.println("Test failed");
            }*/
            //Creating the JavascriptExecutor interface object by Type casting		
            webDriver.get("https://perscriptum-dev.herokuapp.com/locations");
            Thread.sleep(1000);
            WebElement createBtn = webDriver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div/div/header/div/button[2]"));
            createBtn.click();
            Thread.sleep(400);
            WebElement inputForLocationName = webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div > div > div.v-input__slot > div > input"));
            Thread.sleep(400);
            inputForLocationName.sendKeys("TestNameLocation");
            webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > header > div > button.v-btn.v-btn--text.theme--dark.v-size--small")).click();

            Thread.sleep(2000);
            WebElement mapInput = webDriver.findElement(By.id("map"));
            mapInput.click();
            mapInput.sendKeys(helperClass.getRandChar());
            Thread.sleep(1000);
            List<WebElement> containerOfResults = webDriver.findElements(By.xpath("//*[contains(@class,'pac-container pac-logo')]"));
            Thread.sleep(200);
            selectOneElementFromDropdownAddress(containerOfResults, webDriver);

            webDriver.findElement(By.xpath("/html/body/div[1]/div/div/div/main/div/div/div/div[2]/div/div/div[1]/div[1]/div[2]/div/div[2]")).click();
            //select tags START
            Thread.sleep(500);
            WebElement tagsContainer = webDriver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div[1]/div[2]/div/div/div/div/div/div/div[2]/div[1]"));
            tagsContainer.click();
            Thread.sleep(500);
            helperClass.selectOneElementFromDropdownInHeper(webDriver);
            Thread.sleep(200);        
            //select tags END

            //click on main text on top div
            webDriver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div[1]/div[1]/div[2]/div/div[2]")).click();
            //save button click
            webDriver.findElement(By.cssSelector("#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.v-tabs.theme--light > div.v-item-group.theme--light.v-slide-group.v-tabs-bar.primary--text > div.v-slide-group__wrapper > div > header > div > button.v-btn.v-btn--text.theme--light.v-size--small.primary--text")).click();
            Thread.sleep(5000); 
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            webDriver.close();
            webDriver.quit();
        }
        
    }
       
    public void selectOneElementFromDropdownAddress(List<WebElement> listContainerElement, WebDriver webDriver) throws InterruptedException
    {
        Thread.sleep(500);        
        List<WebElement> listElements = listContainerElement.get(1).findElements(By.className("pac-item"));
        Thread.sleep(500);
        System.out.println("listElements.size=" + listElements.size());
        Thread.sleep(500);
        int randomNumberOfElement = (int)(Math.random() * listElements.size());
        Thread.sleep(500);
        System.out.println(listElements.get(randomNumberOfElement));
        Thread.sleep(500);
        if (listElements.size() > 0) {
            listElements.get(randomNumberOfElement).click();        
        } else {
            System.out.println("Error, listElements.size() = " + listElements.size());
        }
    }
}
