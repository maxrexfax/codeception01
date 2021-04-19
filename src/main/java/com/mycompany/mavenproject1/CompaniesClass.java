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
    
    public void createCompany() throws InterruptedException{
        //login to site START
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
        //login to site END
        
        browser.get("https://perscriptum-dev.herokuapp.com/companies");
        Thread.sleep(2000);
        
        browser.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div/div/header/div/button[2]")).click();//menu Choose person type
        Thread.sleep(500);
        
    }
    
}
