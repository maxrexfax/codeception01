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

/**
 *
 * @author user
 */
public class ProfileEditClass {
    
    public HelperClass helperClass = new HelperClass();
    
    public void editProfile()
    {
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
            WebElement login = browser.findElement(By.id("input-11"));
            WebElement passwd = browser.findElement(By.id("input-14"));
            WebElement btnLogin = browser.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[2]/div/form/button"));
            login.sendKeys("test2@pernexus.org");
            passwd.sendKeys("testtest2");
            Thread.sleep(500);
            btnLogin.click();
            Thread.sleep(2500);  
            //login to site END
            
            // 
            browser.findElement(By.cssSelector("#inspire > div > header > div > button.mr-1.v-btn.v-btn--icon.v-btn--round.theme--dark.v-size--default")).click();
            Thread.sleep(500);
            browser.findElement(By.cssSelector("#materialpro > div.v-menu__content.theme--light.v-menu__content--fixed.menuable__content__active > div > div")).click();
            Thread.sleep(500);
            browser.findElement(By.cssSelector("#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.row > div:nth-child(2) > div > div.v-window.v-item-group.theme--light.v-tabs-items > div > div > div > div > div > div > div:nth-child(1) > div > div.v-input__slot > div > input")).clear();
            Thread.sleep(500);
            browser.findElement(By.cssSelector("#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.row > div:nth-child(2) > div > div.v-window.v-item-group.theme--light.v-tabs-items > div > div > div > div > div > div > div:nth-child(1) > div > div.v-input__slot > div > input")).sendKeys("Test name_" + helperClass.getRandomDigit(99, 999));
            Thread.sleep(500);
            browser.findElement(By.cssSelector("#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.row > div:nth-child(2) > div > div.v-window.v-item-group.theme--light.v-tabs-items > div > div > div > div > div > div > div:nth-child(2) > div > div.v-input__slot > div > input")).clear();
            Thread.sleep(500);
            browser.findElement(By.cssSelector("#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.row > div:nth-child(2) > div > div.v-window.v-item-group.theme--light.v-tabs-items > div > div > div > div > div > div > div:nth-child(2) > div > div.v-input__slot > div > input")).sendKeys("AI_" + helperClass.getRandomDigit(99, 999));
            Thread.sleep(500);
            browser.findElement(By.cssSelector("#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.row > div:nth-child(2) > div > div.v-window.v-item-group.theme--light.v-tabs-items > div > div > div > div > div > div > div:nth-child(3) > div > div.v-input__slot > div > input")).clear();
            Thread.sleep(500);
            browser.findElement(By.cssSelector("#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.row > div:nth-child(2) > div > div.v-window.v-item-group.theme--light.v-tabs-items > div > div > div > div > div > div > div:nth-child(3) > div > div.v-input__slot > div > input")).sendKeys("Test LN_" + helperClass.getRandomDigit(99, 999));
            Thread.sleep(500);
            browser.findElement(By.cssSelector("#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.row > div:nth-child(2) > div > div.v-window.v-item-group.theme--light.v-tabs-items > div > div > div > div > div > div > div:nth-child(4) > div > div.v-input__slot > div > input")).clear();
            Thread.sleep(500);
            browser.findElement(By.cssSelector("#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.row > div:nth-child(2) > div > div.v-window.v-item-group.theme--light.v-tabs-items > div > div > div > div > div > div > div:nth-child(4) > div > div.v-input__slot > div > input")).sendKeys("Init_" + helperClass.getRandomDigit(99, 999));
            Thread.sleep(500);
            browser.findElement(By.cssSelector("#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.row > div:nth-child(2) > div > div.v-window.v-item-group.theme--light.v-tabs-items > div > div > div > div > div > div > div:nth-child(5) > div > div.v-input__slot > div > input")).clear();
            Thread.sleep(500);
            browser.findElement(By.cssSelector("#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.row > div:nth-child(2) > div > div.v-window.v-item-group.theme--light.v-tabs-items > div > div > div > div > div > div > div:nth-child(5) > div > div.v-input__slot > div > input")).sendKeys("test2@pernexus.org");
            Thread.sleep(500);
            browser.findElement(By.className("v-item--active")).click();
            Thread.sleep(1500);
            browser.findElement(By.className("v-item--active")).click();
            Thread.sleep(1500);
            browser.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/header/div/button[2]")).click();
//            try {
//                WebElement container = browser.findElement(By.cssSelector("#inspire > div > main > div > div > div > div.container > div:nth-child(1) > div > div > div > div:nth-child(5) > div.wrap-input-form.col-sm-6.col-12 > form > div.row > div.col-sm-8.col-md-10.col-12 > div > div"));
//                List<WebElement> listOfElements = container.findElements(By.tagName("button"));
//                listOfElements.get(1).click();
//            } catch (NoSuchElementException nex) {
//                System.out.println(nex.getMessage());
//            } catch (Exception ex) {
//                System.out.println(ex.getMessage());
//            }
//            Thread.sleep(500);
//            helperClass.selectOneElementFromDropdownAddressInHelper(browser);
            Thread.sleep(15000);
            
            } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            browser.close();
            browser.quit();
        }
    }
    
}
