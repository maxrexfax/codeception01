/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author user
 */
public class CompaniesClass {
    
    public HelperClass helperClass = new HelperClass();
    
    public void createCompany() throws InterruptedException{
        //login to site START
        
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
            Thread.sleep(1500);  
            //login to site END

            browser.get("https://perscriptum-dev.herokuapp.com/companies");
            Thread.sleep(2000);

            browser.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div/div/header/div/button[2]")).click();//create company
            
            browser.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div > div > form:nth-child(1) > div.v-input.mt-5.theme--light.v-text-field.v-text-field--is-booted > div > div.v-input__slot > div > input")).sendKeys("TestCompanyName");
            Thread.sleep(500);
            browser.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div > div > form:nth-child(1) > div:nth-child(2) > div > div.v-input__slot > div > input")).sendKeys("TestCompanyName");
            Thread.sleep(500);
            browser.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div > div > form:nth-child(1) > div:nth-child(3) > div > div.v-input__slot > div > input")).sendKeys("1234567890");
            Thread.sleep(500);
            browser.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div > div > form:nth-child(1) > div:nth-child(4) > div > div.v-input__slot > div > input")).sendKeys("1234");
            Thread.sleep(1000);
            helperClass.selectOneElementFromDropdownAddressInHelper(browser);
            Thread.sleep(500);
            browser.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/header/div/button[2]")).click();
            Thread.sleep(5000);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            browser.close();
            browser.quit();
        }
    }
    
}
