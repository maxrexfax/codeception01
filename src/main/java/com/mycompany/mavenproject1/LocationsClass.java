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
    
    public void createLocation() throws InterruptedException
    {
        HelperClass helperClass = new HelperClass();
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
            //String script00 = "(console.log('1111!!!');"; 
            browser.manage().window().maximize();
            browser.get("https://perscriptum-dev.herokuapp.com/"); 
            //WebElement login = browser.findElement(By.id("input-11"));
            WebElement login = helperClass.safeFindElement(browser, "input-11", "id");
            //WebElement passwd = browser.findElement(By.id("input-14"));
            WebElement passwd = helperClass.safeFindElement(browser, "input-14", "id");
            //WebElement btnLogin = browser.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[2]/div/form/button"));
            WebElement btnLogin = helperClass.safeFindElement(browser, "/html/body/div[1]/div/div/div[2]/div/div/div[2]/div/form/button", "xpath");

            login.sendKeys("test2@pernexus.org");
            passwd.sendKeys("testtest2");
            Thread.sleep(500);

            //btnLogin.click();
            helperClass.safeClickOnElement(btnLogin);
            Thread.sleep(1500);  
            //Logger.global.log(new LogRecord(Level.INFO, "Login complete"));
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
            mapInput.sendKeys(helperClass.getRandChar());
            Thread.sleep(1000);
            List<WebElement> containerOfResults = browser.findElements(By.xpath("//*[contains(@class,'pac-container pac-logo')]"));
            Thread.sleep(200);
            selectOneElementFromDropdownAddress(containerOfResults, browser);

            browser.findElement(By.xpath("/html/body/div[1]/div/div/div/main/div/div/div/div[2]/div/div/div[1]/div[1]/div[2]/div/div[2]")).click();
            //select tags START
            Thread.sleep(500);
            WebElement tagsContainer = browser.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div[1]/div[2]/div/div/div/div/div/div/div[2]/div[1]"));
            tagsContainer.click();
            Thread.sleep(500);
            helperClass.selectOneElementFromDropdownInHeper(browser);
            Thread.sleep(200);        
            //select tags END

            //click on main text on top div
            browser.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div[1]/div[1]/div[2]/div/div[2]")).click();
            //save button click
            browser.findElement(By.cssSelector("#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.v-tabs.theme--light > div.v-item-group.theme--light.v-slide-group.v-tabs-bar.primary--text > div.v-slide-group__wrapper > div > header > div > button.v-btn.v-btn--text.theme--light.v-size--small.primary--text")).click();
            Thread.sleep(5000); 
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            browser.close();
            browser.quit();
        }
        
    }
       
    public void selectOneElementFromDropdownAddress(List<WebElement> listContainerElement, WebDriver browser) throws InterruptedException
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
